/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import Modelo.MovimentacaoUsuario;

/**
 *
 * @author Guilherme
 */
public class MovimentacaoUsuario_Dao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";

    public int AbrirMovimentacao(MovimentacaoUsuario m) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "INSERT INTO movimentacao_usuario(Cod_usuario_movimentacao)Values(" + m.getCod_usuario_movimentacao() + ");";

            //JOptionPane.showMessageDialog(null, "Movimentação Aberta com sucesso");
            return c.queryIncluir(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Abrir movimentacao" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public Collection<MovimentacaoUsuario> BuscarUltimaMovimentacao() {
        Collection<MovimentacaoUsuario> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT Cod_movimentacao FROM movimentacao_usuario ORDER BY Cod_movimentacao DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                MovimentacaoUsuario m = new MovimentacaoUsuario();
                m.setCod_movimentacao(c.getResultSet().getInt("Cod_movimentacao"));
                ms.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ms;
        } finally {
            c.desconectar();
        }
        return ms;
    }

    public int SalvaMovimentacao(MovimentacaoUsuario m) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE movimentacao_usuario "
                    + "SET Tipo_movimentacao = '"+m.getTipo_movimentacao()+"',"
                    + "Tabela_alterada = '" + m.getTabela_alterada() + "',"
                    + "Cod_registro_alterado ='" + m.getCod_registro_alterado() + "',"
                    + "Data_Hora_movimentacao = '" + m.getData_Hora_movimentacao() + "',"
                    + "Cod_usuario_movimentacao = '" + m.getCod_usuario_movimentacao() + "'"
                    + "WHERE Cod_movimentacao = '" + m.getCod_movimentacao() + "' ;";

            //JOptionPane.showMessageDialog(null, "Movimentação Salva!!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!!/n" + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }
}
