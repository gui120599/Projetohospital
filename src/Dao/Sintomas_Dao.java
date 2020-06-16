/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.Sintomas;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class Sintomas_Dao {
    
    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";
//Método Salvar

    public int SalvarSintoma(Sintomas a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Sintomas("
                    + "Desc_Sintoma,"
                    + "Status_Sintoma) VALUES("
                    + "'" + a.getDesc_Sintoma() + "',"
                    + ""+a.getStatus_Sintoma()+");";
            JOptionPane.showMessageDialog(null, "Sintoma Salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    //Método Buscar
    public Collection<Sintomas> BuscarSintomasAtivos() {
        Collection<Sintomas> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Sintomas WHERE Status_Sintoma = 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Sintomas a = new Sintomas();
                a.setCod_Sintoma(c.getResultSet().getInt("Cod_Sintoma"));
                a.setDesc_Sintoma(c.getResultSet().getString("Desc_Sintoma"));
                ms.add(a);
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
    public Collection<Sintomas> BuscarUltmoSintoma() {
        Collection<Sintomas> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT Cod_Sintoma FROM Sintomas ORDER BY Cod_Sintoma DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                Sintomas a = new Sintomas();
                a.setCod_Sintoma(c.getResultSet().getInt("Cod_Sintoma"));
                ms.add(a);
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
    public int AtualizarSintoma(Sintomas a) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Sintomas SET"
                    + "Desc_Sintoma = '"+ a.getDesc_Sintoma()+"'"
                    + " WHERE Cod_Sintoma = "+a.getCod_Sintoma()+";";
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
