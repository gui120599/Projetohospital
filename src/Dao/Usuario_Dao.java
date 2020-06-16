/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import Modelo.Usuario;
import java.awt.HeadlessException;

/**
 *
 * @author Guilherme
 */
public class Usuario_Dao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";

    public int SalvarUsuario(Usuario usuario) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO usuario("
                    + "login,"
                    + "senha)"
                    + "VALUES("
                    + "'" + usuario.getLogin() + "',"
                    + "'" + usuario.getSenha() + "');";

            JOptionPane.showMessageDialog(null, "Usuario salvo com sucesso!!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }
    public int AtualizarUsuario(Usuario usuario) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE usuario SET "
                    + "login = '"+ usuario.getLogin() + "',"
                    + "senha = '"+ usuario.getSenha() + "' "
                    + "WHERE Cod_Usuario = "+usuario.getCod_usuario()+";";
                   

            JOptionPane.showMessageDialog(null, "Usuario Atualizado com sucesso!!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public Collection<Usuario> ChecarLogin(String Login, String Senha) {
        Collection<Usuario> usuarios = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM usuario WHERE login = '" + Login + "' AND senha = '" + Senha + "';";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();
                u.setCod_usuario(c.getResultSet().getInt("cod_usuario"));
                u.setLogin(c.getResultSet().getString("login"));
                usuarios.add(u);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro= " + e);
            return usuarios;
        } finally {
            c.desconectar();
        }
        return usuarios;
    }

    public Collection<Usuario> MostrarUsuario() {
        Collection<Usuario> usuarios = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM usuario ;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();
                u.setCod_usuario(c.getResultSet().getInt("cod_usuario"));
                u.setLogin(c.getResultSet().getString("login"));
                usuarios.add(u);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro= " + e);
            return usuarios;
        } finally {
            c.desconectar();
        }
        return usuarios;
    }

    public boolean Checar(String Login, String Senha) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        boolean check = false;
        try {
            c.conectar();

            String sql = "SELECT cod_usuario FROM usuario WHERE login = '" + Login + "' AND senha = '" + Senha + "';";

            c.query(sql);
            if (c.getResultSet().next()) {

                check = true;
            }
        } catch (SQLException e) {

        } finally {
            c.desconectar();
        }
        return check;
    }
    
    public Collection<Usuario> MostrarUltimoUsuario() {
        Collection<Usuario> usuarios = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT cod_usuario FROM usuario ORDER BY cod_usuario DESC LIMIT 1 ;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();
                u.setCod_usuario(c.getResultSet().getInt("cod_usuario"));
                usuarios.add(u);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro= " + e);
            return usuarios;
        } finally {
            c.desconectar();
        }
        return usuarios;
    }
}
