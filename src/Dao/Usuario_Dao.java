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
                    + "senha,"
                    + "Cod_Prestador,"
                    + "Cod_Perfil)"
                    + "VALUES("
                    + "'" + usuario.getLogin() + "',"
                    + "'" + usuario.getSenha() + "',"
                    + "'" + usuario.getCod_Prestador() + "',"
                    + "'" + usuario.getCod_Perfil() + "');";

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
                    + "login = '" + usuario.getLogin() + "',"
                    + "senha = '" + usuario.getSenha() + "',"
                    + "Cod_Perfil = '" + usuario.getCod_Perfil() + "' "
                    + "WHERE Cod_Usuario = " + usuario.getCod_usuario() + ";";

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

            String sql = "SELECT cod_usuario,login,desc_perfil "
                    + "FROM usuario,perfil_usuario "
                    + "WHERE usuario.cod_perfil = perfil_usuario.cod_perfil ;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();

                u.setCod_usuario(c.getResultSet().getInt("cod_usuario"));
                u.setLogin(c.getResultSet().getString("login"));
                u.setDesc_Perfil(c.getResultSet().getString("desc_perfil"));
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

    public Collection<Usuario> BuscarDadosMedico(int cod_usuario) {
        Collection<Usuario> usuarios = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT cod_colaborador,CRM_Medico,Nome_Colaborador "
                    + "FROM usuario,colaborador "
                    + "WHERE cod_usuario = "+ cod_usuario +" ;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();
                
                u.setCod_Prestador(c.getResultSet().getInt("cod_Colaborador"));
                u.setCRM_Medico(c.getResultSet().getString("CRM_Medico"));
                u.setNome_Medico(c.getResultSet().getString("Nome_Colaborador"));
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

    public Collection<Usuario> ChecarPerfil(int cod_usuario) {
        Collection<Usuario> usuarios = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT cod_usuario,cod_perfil FROM usuario WHERE cod_usuario = " + cod_usuario + ";";

            c.query(sql);
            while (c.getResultSet().next()) {
                Usuario u = new Usuario();
                u.setCod_Perfil(c.getResultSet().getInt("cod_perfil"));
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
