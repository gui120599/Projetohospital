/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.Colaboradores;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class Colaboradores_Dao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";
//Método Salvar

    public int SalvarMedico(Colaboradores a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Medico("
                    + "CRM_Medico,"
                    + "Nome_Medico,"
                    + "Telefone_Medico,"
                    + "Cargo"
                    + "Status_Medico) VALUES("
                    + "'" + a.getCRM_medico() + "',"
                    + "'" + a.getNome_colaborador() + "',"
                    + "'" + a.getTelefone_colaborador() + "',"
                    + "'" + a.getCargo() + "',"
                    + "" + a.getStatus_colaborador() + ");";
            JOptionPane.showMessageDialog(null, "Médico salvo com sucesso!");
            return c.queryIncluir(sql);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<Colaboradores> BuscarMedicoAtivos() {
        Collection<Colaboradores> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Medico WHERE Status_Medico = 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Colaboradores m = new Colaboradores();
                m.setCod_colaborador(c.getResultSet().getInt("Cod_Medico"));
                m.setCRM_medico(c.getResultSet().getString("CRM_Medico"));
                m.setNome_colaborador(c.getResultSet().getString("Nome_Medico"));
                m.setTelefone_colaborador(c.getResultSet().getString("Telefone_Medico"));

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
    public Collection<Colaboradores> BuscarUltimoMedico() {
        Collection<Colaboradores> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT Cod_Medico FROM Medico ORDER BY Cod_Medico DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Colaboradores m = new Colaboradores();
                m.setCod_colaborador(c.getResultSet().getInt("Cod_Medico"));

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
    public int AtualizarMedico(Colaboradores a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Medico SET"
                    + "CRM_Medico =' " + a.getCRM_medico() + "',"
                    + "Nome_Medico =' " + a.getNome_colaborador()+ "',"
                    + "Telefone_Medico =' " + a.getTelefone_colaborador()+ "',"
                    + "Status_Medico= " + a.getStatus_colaborador()+ " "
                    + " WHERE Cod_Medico = " + a.getCod_colaborador()+ " ;";
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
