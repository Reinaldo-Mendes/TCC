package DAO;

import Model.Encomenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EncomendaDAO {

    public void cadastrarEncomenda(Encomenda encomenda){
        String sql = "INSERT INTO ENCOMENDA VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            System.out.println("Método para inserir no banco");
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, encomenda.getNomeFornecedor());
            statement.setString(2, encomenda.getStatus());
            statement.setString(3, encomenda.getDataRecebimento());
            statement.setString(4, encomenda.getHorarioRecebimento());
            statement.setString(5, encomenda.getDataRetirada());
            statement.setString(6, encomenda.getHorarioRetirada());
            statement.setString(7, encomenda.getDescricao());
            statement.setInt(8, encomenda.getIdApartamento());
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

    public ArrayList<Encomenda> BuscarEncomendasPorDescricao(String descricao){
        String sql = "SELECT * FROM ENCOMENDA WHERE descricao LIKE '% "+descricao+" %'";
        ResultSet rs = null;
        PreparedStatement statement = null;
        Connection conn = null;
        Encomenda encomenda;
        ArrayList<Encomenda> encomendas = null;
        try{
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            if(rs != null){
                encomendas = new ArrayList<>();
                while(rs.next()){
                    encomenda = new Encomenda();
                    encomenda.setIdEncomenda(rs.getInt("id"));
                    encomenda.setNomeFornecedor(rs.getString("nomefornecedor"));
                    encomenda.setDescricao(rs.getString("descricao"));
                    encomenda.setStatus(rs.getString("status"));
                    encomenda.setDataRecebimento(rs.getString("datarecebimento"));
                    encomenda.setHorarioRecebimento(rs.getString("horarecebimento"));
                    encomenda.setDataRetirada(rs.getString("dataretirada"));
                    encomenda.setHorarioRetirada(rs.getString("horaretirada"));
                    encomenda.setIdApartamento(rs.getInt("idapartamento"));
                    encomendas.add(encomenda);
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
        return encomendas;
    }

    public ArrayList<Encomenda> ListarTodasEncomendas(){
        //String sql = "SELECT id, nomefornecedor, status, datarecebimento, horarecebimento, dataretirada, horaretirada," +
                //"descricao, idapartamento FROM ENCOMENDA LEFT JOIN APARTAMENTO on APARTAMENTO.id = ENCOMENDA.idapartamento";
        String sql = "SELECT a.id, b.numero, a.nomefornecedor, a.status, a.datarecebimento, a.horarecebimento, a.dataretirada, a.horaretirada,a.descricao, a.idapartamento FROM ENCOMENDA \n" +
                "a JOIN APARTAMENTO as b on b.id = a.idapartamento";
        ResultSet rs;
        PreparedStatement statement = null;
        Connection conn = null;
        Encomenda encomenda;
        ArrayList<Encomenda> encomendas = null;
        try{
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            if(rs != null){
                encomendas = new ArrayList<>();
                while(rs.next()){
                    encomenda = new Encomenda();
                    encomenda.setIdEncomenda(rs.getInt("id"));
                    encomenda.setNomeFornecedor(rs.getString("nomefornecedor"));
                    encomenda.setDescricao(rs.getString("descricao"));
                    encomenda.setStatus(rs.getString("status"));
                    encomenda.setDataRecebimento(rs.getString("datarecebimento"));
                    encomenda.setHorarioRecebimento(rs.getString("horarecebimento"));
                    encomenda.setDataRetirada(rs.getString("dataretirada"));
                    encomenda.setHorarioRetirada(rs.getString("horaretirada"));
                    encomenda.setIdApartamento(rs.getInt("idapartamento"));
                    encomenda.setNumeroApartamento(rs.getString("numero"));
                    encomendas.add(encomenda);
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
        return encomendas;
    }

    public void ExcluirEncomenda(int idEncomenda){
        String sql = "DELETE FROM ENCOMENDA WHERE id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idEncomenda);
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

    public void alterarEncomenda(Encomenda encomenda){
        System.out.println("Método para alterar encomenda");
        String sql = "UPDATE ENCOMENDA SET nomefornecedor = ?, status = ?, datarecebimento = ?, horarecebimento = ?, dataRetirada = ?, horaRetirada = ?," +
                "descricao = ?, idapartamento = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, encomenda.getNomeFornecedor());
            statement.setString(2, encomenda.getStatus());
            statement.setString(3, encomenda.getDataRecebimento());
            statement.setString(4, encomenda.getHorarioRecebimento());
            statement.setString(5, encomenda.getDataRetirada());
            statement.setString(6, encomenda.getHorarioRetirada());
            statement.setString(7, encomenda.getDescricao());
            statement.setInt(8, encomenda.getIdApartamento());
            statement.setInt(9, encomenda.getIdEncomenda());
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

    public Encomenda buscarEncomendaPorId(int id){
        //String sql = "SELECT * FROM encomenda where ID = ?";
        String sql = "SELECT a.id, b.numero, a.nomefornecedor, a.status, a.datarecebimento, a.horarecebimento, a.dataretirada, a.horaretirada,a.descricao, a.idapartamento FROM ENCOMENDA \n" +
                "a JOIN APARTAMENTO as b on b.id = a.idapartamento WHERE a.id = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;
        Encomenda encomenda = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs != null && rs.next()){
                encomenda = new Encomenda();
                encomenda.setIdEncomenda(rs.getInt("id"));
                encomenda.setNomeFornecedor(rs.getString("nomefornecedor"));
                encomenda.setStatus(rs.getString("status"));
                encomenda.setDataRecebimento(rs.getString("datarecebimento"));
                encomenda.setHorarioRecebimento(rs.getString("horarecebimento"));
                encomenda.setDataRetirada(rs.getString("dataretirada"));
                encomenda.setHorarioRetirada(rs.getString("horaretirada"));
                encomenda.setDescricao(rs.getString("descricao"));
                encomenda.setIdApartamento(rs.getInt("idapartamento"));
                encomenda.setNumeroApartamento(rs.getString("numero"));
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
        return encomenda;
    }
}
