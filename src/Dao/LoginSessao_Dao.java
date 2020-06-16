/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexão.Conexao;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import Modelo.LoginSessao;

/**
 *
 * @author Guilherme
 */
public class LoginSessao_Dao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "200.168.0.200";
    String Porta_Banco = "3306";
    String Nome_Banco = "Following";
    String Usuario_Banco = "hospital";
    String Senha_Banco = "informatica";

    public int AbrirSessao(LoginSessao l) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO login_sessao("
                    + "Cod_Usuario_Sessao,"
                    + "Data_Hora,"
                    + "Ip_maquina)"
                    + "VALUES("
                    + "'" + l.getCod_usuario() + "',"
                    + "'" + l.getData_hora() + "',"
                    + "'" + l.getIp_da_maquina() + "');";
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar" + e);
            return 0;
        } finally {
            c.desconectar();
        }
    }

    public Collection<LoginSessao> BuscarSessao(String ip) {
        Collection<LoginSessao> logis = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT Cod_usuario_sessao FROM login_sessao WHERE Ip_Maquina = '" + ip + "' ORDER BY Cod_sessao DESC LIMIT 1;";

            c.query(sql);
            while (c.getResultSet().next()) {
                LoginSessao l = new LoginSessao();
                l.setCod_usuario(c.getResultSet().getInt("Cod_usuario_sessao"));
                logis.add(l);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar= " + e);
            return logis;
        } finally {
            c.desconectar();
        }
        return logis;
    }
}
