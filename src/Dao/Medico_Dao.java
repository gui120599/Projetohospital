/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.Medico;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class Medico_Dao {
    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";
//Método Salvar

    public int SalvarMedico(Medico a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Medico("
                    + "CRM_Medico,"
                    + "Nome_Medico,"
                    + "Telefone_Medico,"
                    + "Status_Medico) VALUES("
                    + "'" + a.getCRM_medico()+ "',"
                    + "'" + a.getNome_medico()+ "',"
                    + "'"+a.getTelefone_Medico()+"',"
                    + ""+a.getStatus_Medico()+");";
            JOptionPane.showMessageDialog(null, "Médico salvo com sucesso!");
            return c.queryIncluir(sql);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<Medico> BuscarMedicoAtivos() {
        Collection<Medico> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Medico WHERE Status_Medico = 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Medico m = new Medico();
                m.setCod_medico(c.getResultSet().getInt("Cod_Medico"));
                m.setCRM_medico(c.getResultSet().getString("CRM_Medico"));
                m.setNome_medico(c.getResultSet().getString("Nome_Medico"));
                m.setTelefone_Medico(c.getResultSet().getString("Telefone_Medico"));
                
                ms.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return ms;
        } finally {
            c.desconectar();
        }
        return ms;
    }
    //Método Buscar
    public Collection<Medico> BuscarUltimoMedico() {
        Collection<Medico> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT Cod_Medico FROM Medico ORDER BY Cod_Medico DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Medico m = new Medico();
                m.setCod_medico(c.getResultSet().getInt("Cod_Medico"));
                
                ms.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return ms;
        } finally {
            c.desconectar();
        }
        return ms;
    }

    //Método Atualizar
    public int AtualizarMedico(Medico a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Medico SET"
                    + "CRM_Medico =' " + a.getCRM_medico() + "',"
                    + "Nome_Medico =' "+ a.getNome_medico()+"',"
                    + "Telefone_Medico =' "+a.getTelefone_Medico()+"',"
                    + "Status_Medico= "+a.getStatus_Medico()+" "
                    + " WHERE Cod_Medico = "+a.getCod_medico()+" ;";
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }
}
