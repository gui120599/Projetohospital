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
public class LoginSessao {
    private int Cod_sessao;
    private int Cod_usuario;
    private String Data_hora;
    private String Ip_maquina;

    public LoginSessao() {
    }

    public LoginSessao(int Cod_sessao, int Cod_usuario, String Data_hora, String Ip_maquina) {
        this.Cod_sessao = Cod_sessao;
        this.Cod_usuario = Cod_usuario;
        this.Data_hora = Data_hora;
        this.Ip_maquina = Ip_maquina;
    }

    public int getCod_sessao() {
        return Cod_sessao;
    }

    public void setCod_sessao(int Cod_sessao) {
        this.Cod_sessao = Cod_sessao;
    }

    public int getCod_usuario() {
        return Cod_usuario;
    }

    public void setCod_usuario(int Cod_usuario) {
        this.Cod_usuario = Cod_usuario;
    }

    public String getData_hora() {
        return Data_hora;
    }

    public void setData_hora(String Data_hora) {
        this.Data_hora = Data_hora;
    }

    public String getIp_da_maquina() {
        return Ip_maquina;
    }

    public void setIp_da_maquina(String Ip_da_maquina) {
        this.Ip_maquina = Ip_da_maquina;
    }

    
}
