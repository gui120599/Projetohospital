/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Guilherme
 */
public class Usuario {
    private int Cod_usuario;
    private String login;
    private String senha;

    public Usuario() {
    }

    public Usuario(int Cod_usuario, String login, String senha) {
        this.Cod_usuario = Cod_usuario;
        this.login = login;
        this.senha = senha;
    }

    public int getCod_usuario() {
        return Cod_usuario;
    }

    public void setCod_usuario(int Cod_usuario) {
        this.Cod_usuario = Cod_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
