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
public class AlteracoesTC {
    private int Cod_TC;
    private String Desc_TC;
    private Boolean Status_TC;

    public AlteracoesTC() {
    }

    public AlteracoesTC(int Cod_TC, String Desc_TC, Boolean Status_TC) {
        this.Cod_TC = Cod_TC;
        this.Desc_TC = Desc_TC;
        this.Status_TC = Status_TC;
    }

    public int getCod_TC() {
        return Cod_TC;
    }

    public void setCod_TC(int Cod_TC) {
        this.Cod_TC = Cod_TC;
    }

    public String getDesc_TC() {
        return Desc_TC;
    }

    public void setDesc_TC(String Desc_TC) {
        this.Desc_TC = Desc_TC;
    }

    public Boolean getStatus_TC() {
        return Status_TC;
    }

    public void setStatus_TC(Boolean Status_TC) {
        this.Status_TC = Status_TC;
    }

    
}
