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
public class Medico {
    private int Cod_medico;
    private String CRM_medico;
    private String Nome_medico;
    private String Telefone_Medico;
    private Boolean Status_Medico;
    
    public Medico() {
    }

    public Medico(int Cod_medico, String CRM_medico, String Nome_medico, String Telefone_Medico, Boolean Status_Medico) {
        this.Cod_medico = Cod_medico;
        this.CRM_medico = CRM_medico;
        this.Nome_medico = Nome_medico;
        this.Telefone_Medico = Telefone_Medico;
        this.Status_Medico = Status_Medico;
    }

    public int getCod_medico() {
        return Cod_medico;
    }

    public void setCod_medico(int Cod_medico) {
        this.Cod_medico = Cod_medico;
    }

    public String getCRM_medico() {
        return CRM_medico;
    }

    public void setCRM_medico(String CRM_medico) {
        this.CRM_medico = CRM_medico;
    }

    public String getNome_medico() {
        return Nome_medico;
    }

    public void setNome_medico(String Nome_medico) {
        this.Nome_medico = Nome_medico;
    }

    public String getTelefone_Medico() {
        return Telefone_Medico;
    }

    public void setTelefone_Medico(String Telefone_Medico) {
        this.Telefone_Medico = Telefone_Medico;
    }

    public Boolean getStatus_Medico() {
        return Status_Medico;
    }

    public void setStatus_Medico(Boolean Status_Medico) {
        this.Status_Medico = Status_Medico;
    }

    @Override
    public String toString() {
        return getNome_medico(); //To change body of generated methods, choose Tools | Templates.
    }

}
