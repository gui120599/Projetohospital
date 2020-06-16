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
public class MovimentacaoUsuario {
    private int Cod_movimentacao;
    private String Tipo_movimentacao;
    private String Tabela_alterada;
    private int Cod_registro_alterado;
    private String Data_Hora_movimentacao;
    private int Cod_usuario_movimentacao;

    public MovimentacaoUsuario() {
    }

    public MovimentacaoUsuario(int Cod_movimentacao, String Tipo_movimentacao, String Tabela_alterada, int Cod_registro_alterado, String Data_Hora_movimentacao, int Cod_usuario_movimentacao) {
        this.Cod_movimentacao = Cod_movimentacao;
        this.Tipo_movimentacao = Tipo_movimentacao;
        this.Tabela_alterada = Tabela_alterada;
        this.Cod_registro_alterado = Cod_registro_alterado;
        this.Data_Hora_movimentacao = Data_Hora_movimentacao;
        this.Cod_usuario_movimentacao = Cod_usuario_movimentacao;
    }

    public int getCod_movimentacao() {
        return Cod_movimentacao;
    }

    public void setCod_movimentacao(int Cod_movimentacao) {
        this.Cod_movimentacao = Cod_movimentacao;
    }

    public String getTipo_movimentacao() {
        return Tipo_movimentacao;
    }

    public void setTipo_movimentacao(String Tipo_movimentacao) {
        this.Tipo_movimentacao = Tipo_movimentacao;
    }

    public String getTabela_alterada() {
        return Tabela_alterada;
    }

    public void setTabela_alterada(String Tabela_alterada) {
        this.Tabela_alterada = Tabela_alterada;
    }

    public int getCod_registro_alterado() {
        return Cod_registro_alterado;
    }

    public void setCod_registro_alterado(int Cod_registro_alterado) {
        this.Cod_registro_alterado = Cod_registro_alterado;
    }

    public String getData_Hora_movimentacao() {
        return Data_Hora_movimentacao;
    }

    public void setData_Hora_movimentacao(String Data_Hora_movimentacao) {
        this.Data_Hora_movimentacao = Data_Hora_movimentacao;
    }

    public int getCod_usuario_movimentacao() {
        return Cod_usuario_movimentacao;
    }

    public void setCod_usuario_movimentacao(int Cod_usuario_movimentacao) {
        this.Cod_usuario_movimentacao = Cod_usuario_movimentacao;
    }

    
}
