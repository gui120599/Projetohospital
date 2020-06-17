/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import Modelo.PerfilUsuario;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class PerfilUsuario_Dao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";
    
    public int SalvarUsuario(PerfilUsuario pu) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Perfil_Usuario("
                    + "Desc_Perfil)"
                    + "VALUES("
                    + "'" + pu.getDesc_Perfil() + "');";

            JOptionPane.showMessageDialog(null, "Perfil Usuario Salvo com sucesso!!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }
    
    public Collection<PerfilUsuario> MostrarPerfis() {
        Collection<PerfilUsuario> perfis = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Perfil_Usuario ;";

            c.query(sql);
            while (c.getResultSet().next()) {
                PerfilUsuario pu = new PerfilUsuario();
                pu.setCod_Perfil(c.getResultSet().getInt("cod_perfil"));
                pu.setDesc_Perfil(c.getResultSet().getString("desc_perfil"));
                perfis.add(pu);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro= " + e);
            return perfis;
        } finally {
            c.desconectar();
        }
        return perfis;
    }
}
