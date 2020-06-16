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
public class Sintomas {
    private int Cod_Sintoma;
    private String Desc_Sintoma;
    private Boolean Status_Sintoma;

    public Sintomas() {
    }

    public Sintomas(int Cod_Sintoma, String Desc_Sintoma, Boolean Status_Sintoma) {
        this.Cod_Sintoma = Cod_Sintoma;
        this.Desc_Sintoma = Desc_Sintoma;
        this.Status_Sintoma = Status_Sintoma;
    }

    public int getCod_Sintoma() {
        return Cod_Sintoma;
    }

    public void setCod_Sintoma(int Cod_Sintoma) {
        this.Cod_Sintoma = Cod_Sintoma;
    }

    public String getDesc_Sintoma() {
        return Desc_Sintoma;
    }

    public void setDesc_Sintoma(String Desc_Sintoma) {
        this.Desc_Sintoma = Desc_Sintoma;
    }

    public Boolean getStatus_Sintoma() {
        return Status_Sintoma;
    }

    public void setStatus_Sintoma(Boolean Status_Sintoma) {
        this.Status_Sintoma = Status_Sintoma;
    }

   
}
