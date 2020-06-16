/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.AlteracoesTC;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class AlteracoesTC_Dao {
    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";
    
    //Método Salvar
    public int SalvarAlteracoesTC(AlteracoesTC a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Alteracoes_Tc("
                    + "Desc_Alteracoes_TC,"
                    + "Status_Alteracoes_TC) VALUES("
                    + "'" + a.getDesc_TC()+ "',"
                    + "" + a.getStatus_TC()+ ");";
            JOptionPane.showMessageDialog(null, "Alteração TC salva com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<AlteracoesTC> BuscarAlteracoesTCAtivos() {
        Collection<AlteracoesTC> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Alteracoes_TC WHERE Status_Alteracoes_TC = 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                AlteracoesTC m = new AlteracoesTC();
                m.setCod_TC(c.getResultSet().getInt("Cod_Alteracoes_TC"));
                m.setDesc_TC(c.getResultSet().getString("Desc_Alteracoes_TC"));
                ms.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return ms;
        } finally {
            c.desconectar();
        }
        return ms;
    }
    
    //Método Buscar
    public Collection<AlteracoesTC> BuscarUltimoAlteracoesTC() {
        Collection<AlteracoesTC> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT Cod_Alteracoes_TC FROM Alteracoes_TC ORDER BY Cod_Alteracoes_TC DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                AlteracoesTC m = new AlteracoesTC();
                m.setCod_TC(c.getResultSet().getInt("Cod_Alteracoes_TC"));
                ms.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return ms;
        } finally {
            c.desconectar();
        }
        return ms;
    }

    //Método Atualizar
    public int AtualizarAlteracoesTC(AlteracoesTC a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Alteracoes_TC SET"
                    + "Desc_Alteracoes_TC =' " + a.getDesc_TC() + "',"
                    + "Status_Alteracoes_TC = "+ a.getStatus_TC()+""
                    + " WHERE Cod_Alteraceos_TC = "+a.getCod_TC()+";";
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }
}
