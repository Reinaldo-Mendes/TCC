package DAO;

import Model.Administrador;
import Model.Encomenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministradorDAO {

    public Administrador ValidarLogin(String nome, String senha){
        String sql = "SELECT * FROM administrador WHERE nome = ? AND senha = ?";
        Administrador adm = null;
        ResultSet rs;
        PreparedStatement statement = null;
        Connection conn = null;

        try{
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, senha);
            rs = statement.executeQuery();
            if(rs != null){
                while(rs.next()){
                    adm = new Administrador();
                    adm.setNome(rs.getString("nome"));
                    adm.setSenha(rs.getString("senha"));
                }
            }
        } catch (NullPointerException | SQLException e){
            System.out.println("Primeira exceção: "+e.getMessage() + "\t"+e.getCause() + "\t"+ e.getLocalizedMessage());
            e.printStackTrace();
        } finally{
            try{
                if (statement != null)
                    statement.close();
            } catch (Exception e2){
                System.out.println("Segunda exceção: "+e2.getMessage());
            }
            try{
                if (conn != null)
                    conn.close();
            } catch (Exception e3){
                System.out.println("Terceira exceção:"+e3.getMessage());
            }
        }
        return adm;

    }
}
