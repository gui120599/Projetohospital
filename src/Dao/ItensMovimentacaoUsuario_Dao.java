/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import Modelo.ItensMovimentacaoUsuario;

/**
 *
 * @author Suporte T.I
 */
public class ItensMovimentacaoUsuario_Dao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";

    public int SalavarItensMovimentacao(ItensMovimentacaoUsuario m) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "INSERT INTO itens_movimentacao_usuario("
                    + "Valor_antigo,"
                    + "Valor_novo,"
                    + "Cod_movimentacao,"
                    + "Nome_coluna)"
                    + "Values('" + m.getValor_antigo() + "',"
                    + "'" + m.getValor_novo() + "',"
                    + " " + m.getCod_movimentacao() + ","
                    + "'" + m.getNome_coluna() + "');";

            JOptionPane.showMessageDialog(null, "Itens Movimentação salvo com sucesso");
            return c.queryIncluir(sql);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar/n" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }
}
