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
public class AlteracoesLaboratoriais {
    private int Cod_Alteracoes_Laboratoriais;
    private String Desc_Alteracoes_Laboratoriais;
    private Boolean Status_Alteracoes_Laboratoriais;

    public AlteracoesLaboratoriais() {
    }

    public AlteracoesLaboratoriais(int Cod_Alteracoes_Laboratoriais, String Desc_Alteracoes_Laboratoriais, Boolean Status_Alteracoes_Laboratoriais) {
        this.Cod_Alteracoes_Laboratoriais = Cod_Alteracoes_Laboratoriais;
        this.Desc_Alteracoes_Laboratoriais = Desc_Alteracoes_Laboratoriais;
        this.Status_Alteracoes_Laboratoriais = Status_Alteracoes_Laboratoriais;
    }

    public int getCod_Alteracoes_Laboratoriais() {
        return Cod_Alteracoes_Laboratoriais;
    }

    public void setCod_Alteracoes_Laboratoriais(int Cod_Alteracoes_Laboratoriais) {
        this.Cod_Alteracoes_Laboratoriais = Cod_Alteracoes_Laboratoriais;
    }

    public String getDesc_Alteracoes_Laboratoriais() {
        return Desc_Alteracoes_Laboratoriais;
    }

    public void setDesc_Alteracoes_Laboratoriais(String Desc_Alteracoes_Laboratoriais) {
        this.Desc_Alteracoes_Laboratoriais = Desc_Alteracoes_Laboratoriais;
    }

    public Boolean getStatus_Alteracoes_Laboratoriais() {
        return Status_Alteracoes_Laboratoriais;
    }

    public void setStatus_Alteracoes_Laboratoriais(Boolean Status_Alteracoes_Laboratoriais) {
        this.Status_Alteracoes_Laboratoriais = Status_Alteracoes_Laboratoriais;
    }

    @Override
    public String toString() {
        return getDesc_Alteracoes_Laboratoriais(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
