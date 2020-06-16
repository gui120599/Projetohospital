/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.AlteracoesLaboratoriais;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class AlteracoesLaboratoriais_Dao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";
//Método Salvar

    public int SalvarAlteracoesLaboratoriais(AlteracoesLaboratoriais a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Alteracoes_Laboratoriais("
                    + "desc_Alteracoes_Laboratoriais,"
                    + "Status_Alteracoes_Laboratoriais) VALUES("
                    + "'" + a.getDesc_Alteracoes_Laboratoriais() + "',"
                    + "" + a.getStatus_Alteracoes_Laboratoriais() + ");";
            JOptionPane.showMessageDialog(null, "Alteração Laboratorial salva com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<AlteracoesLaboratoriais> BuscarAlteracoesLaboratoriaisAtivos() {
        Collection<AlteracoesLaboratoriais> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * "
                    + "FROM Alteracoes_Laboratoriais "
                    + "WHERE Status_Alteracoes_Laboratoriais = 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                AlteracoesLaboratoriais m = new AlteracoesLaboratoriais();
                m.setCod_Alteracoes_Laboratoriais(c.getResultSet().getInt("Cod_Alteracoes_Laboratoriais"));
                m.setDesc_Alteracoes_Laboratoriais(c.getResultSet().getString("Desc_Alteracoes_Laboratoriais"));
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
    public int AtualizarAlteracoesLaboratoriais(AlteracoesLaboratoriais a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Alteracoes_Laboratoriais SET"
                    + "Desc_Alteracoes_Laboratoriais =' " + a.getDesc_Alteracoes_Laboratoriais() + "',"
                    + "Status_Alteracoes_Laboratoriais = "+ a.getStatus_Alteracoes_Laboratoriais()+""
                    + " WHERE Cod_Alteracoes_Laboratoriais = "+a.getCod_Alteracoes_Laboratoriais()+";";
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }
    //Mostrar ultimo codigo inserido
    public Collection<AlteracoesLaboratoriais> MostrarUltmoCodigo() {
        Collection<AlteracoesLaboratoriais> ufs = new ArrayList<>();
       Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT Cod_Alteracoes_Laboratoriais "
                    + "FROM Alteracoes_Laboratoriais "
                    + "ORDER BY Cod_Alteracoes_Laboratoriais DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                AlteracoesLaboratoriais a = new AlteracoesLaboratoriais();
                a.setCod_Alteracoes_Laboratoriais(c.getResultSet().getInt("Cod_Alteracoes_Laboratoriais"));
                ufs.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ufs;
        } finally {
            c.desconectar();
        }
        return ufs;
    }
}
