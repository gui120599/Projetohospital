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
    private int Cod_Perfil;
    private int Cod_Prestador;
    private String login;
    private String senha;
    //Variáveis auxiliares
    private String Desc_Perfil;
    private String CRM_Medico;
    private String Nome_Medico;
    
    public Usuario() {
    }

    public Usuario(int Cod_usuario, int Cod_Perfil, int Cod_Prestador, String login, String senha, String Desc_Perfil, String CRM_Medico, String Nome_Medico) {
        this.Cod_usuario = Cod_usuario;
        this.Cod_Perfil = Cod_Perfil;
        this.Cod_Prestador = Cod_Prestador;
        this.login = login;
        this.senha = senha;
        this.Desc_Perfil = Desc_Perfil;
        this.CRM_Medico = CRM_Medico;
        this.Nome_Medico = Nome_Medico;
    }

    public int getCod_usuario() {
        return Cod_usuario;
    }

    public void setCod_usuario(int Cod_usuario) {
        this.Cod_usuario = Cod_usuario;
    }

    public int getCod_Perfil() {
        return Cod_Perfil;
    }

    public void setCod_Perfil(int Cod_Perfil) {
        this.Cod_Perfil = Cod_Perfil;
    }

    public int getCod_Prestador() {
        return Cod_Prestador;
    }

    public void setCod_Prestador(int Cod_Prestador) {
        this.Cod_Prestador = Cod_Prestador;
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

    public String getDesc_Perfil() {
        return Desc_Perfil;
    }

    public void setDesc_Perfil(String Desc_Perfil) {
        this.Desc_Perfil = Desc_Perfil;
    }

    public String getCRM_Medico() {
        return CRM_Medico;
    }

    public void setCRM_Medico(String CRM_Medico) {
        this.CRM_Medico = CRM_Medico;
    }

    public String getNome_Medico() {
        return Nome_Medico;
    }

    public void setNome_Medico(String Nome_Medico) {
        this.Nome_Medico = Nome_Medico;
    }

}
