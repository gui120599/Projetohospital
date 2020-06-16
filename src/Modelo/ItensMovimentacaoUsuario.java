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
public class ItensMovimentacaoUsuario {
    private int Cod_movimentacao;
    private String Valor_antigo;
    private String Valor_novo;
    private String Nome_coluna;
    public ItensMovimentacaoUsuario() {
    }

    public ItensMovimentacaoUsuario(int Cod_movimentacao, String Valor_antigo, String Valor_novo, String Nome_coluna) {
        this.Cod_movimentacao = Cod_movimentacao;
        this.Valor_antigo = Valor_antigo;
        this.Valor_novo = Valor_novo;
        this.Nome_coluna = Nome_coluna;
    }

    public int getCod_movimentacao() {
        return Cod_movimentacao;
    }

    public void setCod_movimentacao(int Cod_movimentacao) {
        this.Cod_movimentacao = Cod_movimentacao;
    }

    public String getValor_antigo() {
        return Valor_antigo;
    }

    public void setValor_antigo(String Valor_antigo) {
        this.Valor_antigo = Valor_antigo;
    }

    public String getValor_novo() {
        return Valor_novo;
    }

    public void setValor_novo(String Valor_novo) {
        this.Valor_novo = Valor_novo;
    }

    public String getNome_coluna() {
        return Nome_coluna;
    }

    public void setNome_coluna(String Nome_coluna) {
        this.Nome_coluna = Nome_coluna;
    }

    
}
