/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.Paciente;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class Pacientes_Dao {
    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";
//Método Salvar

    public int SalvarPaciente(Paciente a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Paciente("
                    + "Prontuario,"
                    + "Nome_Paciente,"
                    + "Telefone_Paciente) VALUES("
                    + "" + a.getProntuario()+ ","
                    + "'" + a.getNome_Paciente()+ "',"
                    + "'"+a.getTelefone_Paciente()+"');";
            JOptionPane.showMessageDialog(null, "Paciente salvo com sucesso!");
            return c.queryIncluir(sql);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<Paciente> BuscarPaciente() {
        Collection<Paciente> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Paciente;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Paciente m = new Paciente();
                m.setCod_Paciente(c.getResultSet().getInt("Cod_Paciente"));
                m.setProntuario(c.getResultSet().getInt("Prontuario"));
                m.setNome_Paciente(c.getResultSet().getString("Nome_Paciente"));
                m.setTelefone_Paciente(c.getResultSet().getString("Telefone_Paciente"));
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
    public Collection<Paciente> BuscarUltimoPaciente() {
        Collection<Paciente> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT cod_paciente FROM Paciente ORDER BY cod_paciente DESC LIMIT  1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Paciente m = new Paciente();
                m.setCod_Paciente(c.getResultSet().getInt("Cod_Paciente"));
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
    public int AtualizarPaciente(Paciente a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Paciente SET"
                    + "Prontuario =' " + a.getProntuario() + "',"
                    + "Nome_Paciente =' "+ a.getNome_Paciente()+"',"
                    + "Telefone_Paciente =' "+a.getTelefone_Paciente()+"'"
                    + " WHERE Cod_Paciente ="+a.getCod_Paciente()+" ;";
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
