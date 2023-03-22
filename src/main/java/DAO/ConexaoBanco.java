package DAO;

import java.sql.*;
import java.util.Properties;


public class ConexaoBanco {
    private String local;
    private String user;
    private String senha;
    private Connection conn;
    private Statement statment;
    private String str_conexao;
    private String driverjdbc;

    public ConexaoBanco(String local, String porta,
                        String banco, String user, String senha) {
        setStr_conexao("jdbc:postgresql://" + local + ":" + porta + "/" + banco);
        setLocal(local);
        setSenha(senha);
        setUser(user);
        setDriverjdbc("org.postgresql.Driver");

    }
    //Conexão com o Banco de Dados
    public void connect() {
        try {
            Class.forName(getDriverjdbc());
            setC(DriverManager.getConnection(getStr_conexao(), getUser(), getSenha()));
            setStatment(getConn().createStatement());
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            getConn().close();
        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }

    public void configUser(String user, String senha) {
        setUser(user);
        setSenha(senha);
    }

    public void configLocal(String banco) {
        setLocal(banco);
    }


    public ResultSet query(String query) {
        try {
            return getStatment().executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // GETs AND SETs
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Connection getConn() {
        return conn;
    }

    public void setC(Connection c) {
        this.conn = c;
    }

    public Statement getStatment() {
        return statment;
    }

    public void setStatment(Statement statment) {
        this.statment = statment;
    }

    public String getStr_conexao() {
        return str_conexao;
    }

    public void setStr_conexao(String str_conexao) {
        this.str_conexao = str_conexao;
    }

    public String getDriverjdbc() {
        return driverjdbc;
    }

    public void setDriverjdbc(String driverjdbc) {
        this.driverjdbc = driverjdbc;
    }

    public Connection getConnection(){
        Connection conn = null;

        try{
            Class.forName("org.postgresql.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            String url = "jdbc:postgresql://localhost:5432/tcc";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password","Fenixbr999");

           conn = DriverManager.getConnection(url, props);

        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

}