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
public class Paciente {
    private int Cod_Paciente;
    private int Prontuario;
    private String Nome_Paciente;
    private String Telefone_Paciente;

    public Paciente() {
    }

    public Paciente(int Cod_Paciente, int Prontuario, String Nome_Paciente, String Telefone_Paciente) {
        this.Cod_Paciente = Cod_Paciente;
        this.Prontuario = Prontuario;
        this.Nome_Paciente = Nome_Paciente;
        this.Telefone_Paciente = Telefone_Paciente;
    }

    public int getCod_Paciente() {
        return Cod_Paciente;
    }

    public void setCod_Paciente(int Cod_Paciente) {
        this.Cod_Paciente = Cod_Paciente;
    }

    public int getProntuario() {
        return Prontuario;
    }

    public void setProntuario(int Prontuario) {
        this.Prontuario = Prontuario;
    }

    public String getNome_Paciente() {
        return Nome_Paciente;
    }

    public void setNome_Paciente(String Nome_Paciente) {
        this.Nome_Paciente = Nome_Paciente;
    }

    public String getTelefone_Paciente() {
        return Telefone_Paciente;
    }

    public void setTelefone_Paciente(String Telefone_Paciente) {
        this.Telefone_Paciente = Telefone_Paciente;
    }
    
}
