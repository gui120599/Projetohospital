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
public class Colaboradores {
    private int Cod_colaborador;
    private String CRM_medico;
    private String Nome_colaborador;
    private String Telefone_colaborador;
    private String Cargo;
    private Boolean Status_colaborador;
    
    public Colaboradores() {
    }

    public Colaboradores(int Cod_colaborador, String CRM_medico, String Nome_colaborador, String Telefone_colaborador, String Cargo, Boolean Status_colaborador) {
        this.Cod_colaborador = Cod_colaborador;
        this.CRM_medico = CRM_medico;
        this.Nome_colaborador = Nome_colaborador;
        this.Telefone_colaborador = Telefone_colaborador;
        this.Cargo = Cargo;
        this.Status_colaborador = Status_colaborador;
    }

    public int getCod_colaborador() {
        return Cod_colaborador;
    }

    public void setCod_colaborador(int Cod_colaborador) {
        this.Cod_colaborador = Cod_colaborador;
    }

    public String getCRM_medico() {
        return CRM_medico;
    }

    public void setCRM_medico(String CRM_medico) {
        this.CRM_medico = CRM_medico;
    }

    public String getNome_colaborador() {
        return Nome_colaborador;
    }

    public void setNome_colaborador(String Nome_colaborador) {
        this.Nome_colaborador = Nome_colaborador;
    }

    public String getTelefone_colaborador() {
        return Telefone_colaborador;
    }

    public void setTelefone_colaborador(String Telefone_colaborador) {
        this.Telefone_colaborador = Telefone_colaborador;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public Boolean getStatus_colaborador() {
        return Status_colaborador;
    }

    public void setStatus_colaborador(Boolean Status_colaborador) {
        this.Status_colaborador = Status_colaborador;
    }

    
    @Override
    public String toString() {
        return getNome_colaborador(); //To change body of generated methods, choose Tools | Templates.
    }

}
