/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.Atendimento;
import Modelo.Segmento_Atendimento;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class Segmento_Atendimento_Dao {
    //Dados do banco
    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";

    //Método Salvar
    public int SalvarAtendimento(Segmento_Atendimento a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Segmento_Atendimento("
                    + "Cod_Atendimento,"
                    + "Cod_Medico_Segmento," 
                    + "Data_Segmetno,"
                    + "Hora_Segmento,"
                    + "Cod_Sintomas,"
                    + "Observacoes_Sintomas) VALUES("
                    + "" + a.getCod_Atendimento()+ ","
                    + "" + a.getCod_Medico_Segmento() + ","
                    + "'" + a.getData_Segmento() + "',"
                    + "'" + a.getHora_Segmento() + "',"
                    + "" + a.getCod_Sintomas() + ","
                    + "'"+a.getObservacoes_Sintomas()+"');";
            JOptionPane.showMessageDialog(null, "Segmento do atendimento Salvo com sucesso!");
            return c.queryIncluir(sql);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<Segmento_Atendimento> BuscarAtendimentos() {
        Collection<Segmento_Atendimento> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Segmento_Atendimento;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Segmento_Atendimento m = new Segmento_Atendimento();
                m.setCod_Atendimento(c.getResultSet().getInt("Cod_Segmento_Atendimento"));
                m.setCod_Medico_Segmento(c.getResultSet().getInt("Cod_Medico_Segmento"));
                m.setCod_Sintomas(c.getResultSet().getInt("Cod_Sintomas"));
                m.setObservacoes_Sintomas(c.getResultSet().getString("Observacoes_Sintomas"));
                m.setData_Segmento(c.getResultSet().getString("Data_Segmento"));
                m.setHora_Segmento(c.getResultSet().getString("Hora_Segmento"));
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
    public int AtualizarAtendimento(Segmento_Atendimento a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Segmento_Atendimento SET"
                    + "Cod_Atendimento = " + a.getCod_Atendimento() + ","
                    + "Cod_Medico = " + a.getCod_Medico_Segmento()+ ","
                    + "Cod_Sintomas = " + a.getCod_Sintomas() + ","
                    + "Observacoes_Sintomas ='"+a.getObservacoes_Sintomas()+"',"
                    + "Data_Segmento = '" + a.getData_Segmento() + "',"
                    + "Hora_Segmento = '" + a.getHora_Segmento() + "',"
                    + "WHERE Cod_Segmento = " + a.getCod_Segmento_Atendimento() + " ;";
            qtdRegistrosAfetados = c.queryUpdate(sql);
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            return qtdRegistrosAfetados;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar: " + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }
}
