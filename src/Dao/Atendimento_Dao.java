/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.Atendimento;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class Atendimento_Dao {

    //Dados do banco
    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";

    //Método Salvar
    public int SalvarAtendimento(Atendimento a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Atendimento("
                    + "Prontuario,"
                    + "Cod_Colaborador,"
                    + "Cod_Alteracoes_TC,"
                    + "Cod_Alteracoes_Laboratoriais,"
                    + "Cod_Sintoma,"
                    + "Observacoes_Sintomas,"
                    + "Data_Atendimento,"
                    + "Hora_Atendimento,"
                    + "Observacoes_Atendimento) VALUES("
                    + "" + a.getProntuario() + ","
                    + "" + a.getCod_Colaborador() + ","
                    + "" + a.getCod_TC() + ","
                    + "" + a.getCod_Alteracoes_Laboratoriais() + ","
                    + "" + a.getCod_Sintomas() + ","
                    + "'"+a.getObservacoes_Sintomas()+"',"
                    + "'" + a.getData_Atendimento() + "',"
                    + "'" + a.getHora_Atendimento() + "',"
                    + "'"+a.getObservacoes_Atendimento()+"');";
            JOptionPane.showMessageDialog(null, "Atendimento efetuado com sucesso!!");
            return c.queryIncluir(sql);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<Atendimento> BuscarAtendimentos() {
        Collection<Atendimento> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Atendimento;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Atendimento m = new Atendimento();
                m.setCod_Atendimento(c.getResultSet().getInt("Cod_Atendimento"));
                m.setProntuario(c.getResultSet().getInt("Prontuario"));
                m.setCod_Colaborador(c.getResultSet().getInt("Cod_Colaborador"));
                m.setCod_TC(c.getResultSet().getInt("Cod_TC"));
                m.setCod_Alteracoes_Laboratoriais(c.getResultSet().getInt("Cod_Alteracoes_Laboratoriais"));
                m.setCod_Sintomas(c.getResultSet().getInt("Cod_Sintomas"));
                m.setObservacoes_Sintomas(c.getResultSet().getString("Observacoes_Sintomas"));
                m.setData_Atendimento(c.getResultSet().getString("Data_Atendimento"));
                m.setHora_Atendimento(c.getResultSet().getString("Hora_Atendimento"));
                m.setObservacoes_Atendimento(c.getResultSet().getString("Observacoes_Atendimento"));
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
    public Collection<Atendimento> BuscarUltmoAtendimento() {
        Collection<Atendimento> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Atendimento ORDER BY cod_Atendimento DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Atendimento m = new Atendimento();
                m.setCod_Atendimento(c.getResultSet().getInt("Cod_Atendimento"));
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
    public int AtualizarAtendimento(Atendimento a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Atendimento SET"
                    + "Prontuario = " + a.getProntuario() + ","
                    + "Cod_Colaborador = " + a.getCod_Colaborador() + ","
                    + "Cod_TC = " + a.getCod_TC() + ","
                    + "Cod_Alteracoes_Laboratoriais = " + a.getCod_Alteracoes_Laboratoriais() + ","
                    + "Cod_Sintomas = " + a.getCod_Sintomas() + ","
                    + "Observacoes_Sintomas ='"+a.getObservacoes_Sintomas()+"',"
                    + "Data_Atendimento = '" + a.getData_Atendimento() + "',"
                    + "Hora_Atendimento = '" + a.getHora_Atendimento() + "',"
                    + "Observacoes_Atendimento = '"+a.getObservacoes_Atendimento()+"',"
                    + "WHERE Cod_Atendimento = " + a.getCod_Atendimento() + " ;";
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
