package Conexão;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Conexao {

    private String local;
    private String usuario;
    private String senha;
    private Connection c;
    private Statement statement;
    private ResultSet resultset;
    private String strConexao;
    private String driverjdbc;

    public Conexao(String tipoSqbd, String local, String porta, String banco, String usuario, String senha) {
        if (tipoSqbd.equals("PostgreSql")) {
            setStrConexao("jdbc:postgresql://" + local + ":" + porta + "/" + banco);
            setLocal(local);
            setSenha(senha);
            setUsuario(usuario);
            setDriverjdbc("org.postgresql.Driver");

        } else if (tipoSqbd.equals("Mysql")) {
            setStrConexao("jdbc:mysql://" + local + ":" + porta + "/" + banco);
            setLocal(local);
            setSenha(senha);
            setUsuario(usuario);
            setDriverjdbc("com.mysql.jdbc.Driver");

        } else {
            JOptionPane.showMessageDialog(null, "Deu merda, informe o SGBD que deseja conectar!");
        }

    }

    public void configUsuario(String usuario, String senha) {
        setUsuario(usuario);
        setSenha(senha);
    }

    public void configLocal(String banco) {
        setLocal(banco);
    }

    //ConexÃ£o com o Banco de Dados
    public void conectar() {
        
        try {
            Class.forName(getDriverjdbc());
            setC(DriverManager.getConnection(getStrConexao(), getUsuario(), getSenha()));
            setStatement(getC().createStatement());
            //JOptionPane.showMessageDialog(null,"Conectou");
            
            

        } catch (ClassNotFoundException | SQLException e) {
            
            System.err.println(e);
            JOptionPane.showMessageDialog(null," A Conexão com o Banco de dados falhou!! /n"+ e);
            
        }
    }

    public void desconectar() {
        
        try {
            getC().close();
            //JOptionPane.showMessageDialog(null,"Desconectou");
            

        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();

        }
    }

    public ResultSet query(String sql) {
        try {
            this.setStatement(getC().createStatement());
            this.setResultset(getStatement().executeQuery(sql));
            resultset = statement.getResultSet();
            //resultset.first();
            return resultset;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList query2(String sql) {
        ArrayList dados;
        dados = new ArrayList();

        try {
            this.setStatement(getC().createStatement());
            this.setResultSet(getStatement().executeQuery(sql));
            resultset = statement.getResultSet();

            resultset.first();
            do {
                dados.add(new Object[]{resultset.getInt("cod_uf")});

            } while (resultset.next());

            return dados;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }

    public int queryIncluir(String sql) {
        int status = 0;
        try {
            this.setStatement(getC().createStatement());
            this.getStatement().executeUpdate(sql);

            //Teste de retorno: System.out.println(status);
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return status;
        }
    }

    public int queryDelete(String sql) {

        int qtdRegistrosAfetados = 0;

        try {
            this.setStatement(getC().createStatement());
            qtdRegistrosAfetados = getStatement().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return qtdRegistrosAfetados;

        }

        return qtdRegistrosAfetados;

    }

    public int queryUpdate(String sql) {

        int qtdRegistrosAfetados = 0;

        try {
            this.setStatement(getC().createStatement());
            qtdRegistrosAfetados = getStatement().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            return qtdRegistrosAfetados;

        }

        return qtdRegistrosAfetados;

    }

    //GETs AND SETs
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultset;
    }

    public void setResultSet(ResultSet resultset) {
        this.resultset = resultset;
    }

    public String getStrConexao() {
        return strConexao;
    }

    public void setStrConexao(String strConexao) {
        this.strConexao = strConexao;
    }

    public String getDriverjdbc() {
        return driverjdbc;
    }

    public void setDriverjdbc(String driverjdbc) {
        this.driverjdbc = driverjdbc;
    }

    private void setResultset(ResultSet executeQuery) {

    }

    public Object getResultset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
