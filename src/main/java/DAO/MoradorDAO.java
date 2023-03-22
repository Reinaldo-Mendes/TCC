package DAO;

import Model.Encomenda;
import Model.Morador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoradorDAO {

    public void cadastrarMorador(Morador morador){
        String sql = "INSERT INTO MORADOR VALUES (DEFAULT, ?, ?, ?)";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            System.out.println("Método para inserir moradores no banco");
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, morador.getNome());
            statement.setString(2, morador.getTelefone());
            statement.setInt(3, morador.getIdApartamento());
            statement.execute();
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
    }


    public ArrayList<Morador> ListarTodosMoradores(){
        //String sql = "SELECT * FROM MORADOR";
        String sql = "SELECT a.id, b.numero as numeroApartamento, a.nome, a.numerotelefone, a.idapartamento FROM MORADOR as a join apartamento as b on b.id = a.idapartamento";
        ResultSet rs;
        PreparedStatement statement = null;
        Connection conn = null;
        Morador morador;
        ArrayList<Morador> moradores = null;
        try{
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            if(rs != null){
                moradores = new ArrayList<>();
                while(rs.next()){
                    morador = new Morador();
                    morador.setIdMorador(rs.getInt("id"));
                    morador.setNome(rs.getString("nome"));
                    morador.setTelefone(rs.getString("numerotelefone"));
                    morador.setIdApartamento(rs.getInt("idapartamento"));
                    morador.setNumeroApartamento(rs.getString("numeroApartamento"));
                    moradores.add(morador);

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
        return moradores;
    }

    public void ExcluirMorador(int idMorador){
        String sql = "DELETE FROM MORADOR WHERE id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idMorador);
            statement.execute();


        }catch (NullPointerException | SQLException e){
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
    }

    public void alterarMorador(Morador morador){
        System.out.println("Método para alterar morador");
        String sql = "UPDATE MORADOR SET nome = ?, numerotelefone = ?, idapartamento = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, morador.getNome());
            statement.setString(2, morador.getTelefone());
            statement.setInt(3, morador.getIdApartamento());
            statement.setInt(4, morador.getIdMorador());
            statement.execute();


        }catch (NullPointerException | SQLException e){
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
    }

    public Morador buscarMoradorPorIdDoApartamento(int idApartamento){
        String sql = "SELECT a.id, a.nome, a.idapartamento, b.numero, a.numerotelefone FROM morador a JOIN apartamento as B on b.id = a.idapartamento where a.ID = ?";
        PreparedStatement statement = null;
        ResultSet rs;
        Connection conn = null;
        Morador morador = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idApartamento);
            rs = statement.executeQuery();
            if(rs != null && rs.next()){
                morador = new Morador();
                morador.setIdMorador(rs.getInt("id"));
                morador.setNome(rs.getString("nome"));
                morador.setTelefone(rs.getString("numerotelefone"));
                morador.setIdApartamento(rs.getInt("idapartamento"));
                morador.setNumeroApartamento(rs.getString("numero"));
            }

        }catch (NullPointerException | SQLException e){
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
        return morador;
    }

    public Morador BuscarMoradoresPorIdDaEncomenda(int idEncomenda){
        String sql = "SELECT nome, numerotelefone\n" +
                "FROM morador\n" +
                "LEFT JOIN encomenda ON morador.idapartamento = encomenda.idapartamento\n" +
                "LEFT JOIN apartamento ON morador.idapartamento = apartamento.id\n" +
                "WHERE encomenda.id = ?;";
        PreparedStatement statement = null;
        ResultSet rs;
        Connection conn = null;
        Morador morador = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idEncomenda);
            rs = statement.executeQuery();
            if(rs != null && rs.next()){
                morador = new Morador();
                //morador.setIdMorador(rs.getInt("id"));
                morador.setNome(rs.getString("nome"));
                morador.setTelefone(rs.getString("numerotelefone"));
                //morador.setIdApartamento(rs.getInt("idapartamento"));
                //morador.setNumeroApartamento(rs.getString("numero"));
            }

        }catch (NullPointerException | SQLException e){
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
        return morador;
    }
}
