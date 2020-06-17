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
public class Permissoes {
    private int Cod_Permissao; 
    private String Descricao_Permissao;
    
    public Permissoes() {
    }

    public Permissoes(int Cod_Permissao, String Descricao_Permissao) {
        this.Cod_Permissao = Cod_Permissao;
        this.Descricao_Permissao = Descricao_Permissao;
    }

    public int getCod_Permissao() {
        return Cod_Permissao;
    }

    public void setCod_Permissao(int Cod_Permissao) {
        this.Cod_Permissao = Cod_Permissao;
    }

    public String getDescricao_Permissao() {
        return Descricao_Permissao;
    }

    public void setDescricao_Permissao(String Descricao_Permissao) {
        this.Descricao_Permissao = Descricao_Permissao;
    }
    
    
}
