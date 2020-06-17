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
public class PermissoesPerfil {
    private int Cod_Permissao;
    private int Cod_Perfil;

    public PermissoesPerfil() {
    }

    public PermissoesPerfil(int Cod_Permissao, int Cod_Perfil, int Status_Permissao) {
        this.Cod_Permissao = Cod_Permissao;
        this.Cod_Perfil = Cod_Perfil;
    }

    public int getCod_Permissao() {
        return Cod_Permissao;
    }

    public void setCod_Permissao(int Cod_Permissao) {
        this.Cod_Permissao = Cod_Permissao;
    }

    public int getCod_Perfil() {
        return Cod_Perfil;
    }

    public void setCod_Perfil(int Cod_Perfil) {
        this.Cod_Perfil = Cod_Perfil;
    }

}
