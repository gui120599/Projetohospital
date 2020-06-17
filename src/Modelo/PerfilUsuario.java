/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Suporte T.I 2
 */
public class PerfilUsuario {
    private int Cod_Perfil;
    private String Desc_Perfil;

    public PerfilUsuario() {
    }

    public PerfilUsuario(int Cod_Perfil, String Desc_Perfil) {
        this.Cod_Perfil = Cod_Perfil;
        this.Desc_Perfil = Desc_Perfil;
    }

    public int getCod_Perfil() {
        return Cod_Perfil;
    }

    public void setCod_Perfil(int Cod_Perfil) {
        this.Cod_Perfil = Cod_Perfil;
    }

    public String getDesc_Perfil() {
        return Desc_Perfil;
    }

    public void setDesc_Perfil(String Desc_Perfil) {
        this.Desc_Perfil = Desc_Perfil;
    }

    @Override
    public String toString() {
        return getDesc_Perfil(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
