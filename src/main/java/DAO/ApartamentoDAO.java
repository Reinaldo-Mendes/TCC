package DAO;

import Model.Apartamento;
import Model.Encomenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApartamentoDAO {

    public void cadastrarApartamento(Apartamento apartamento){
        String sql = "INSERT INTO APARTAMENTO VALUES (DEFAULT, ?)";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            System.out.println("Método para inserir no banco");
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, apartamento.getNumero());
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

    public ArrayList<Apartamento> ListarTodosApartamentos(){
        String sql = "SELECT * FROM APARTAMENTO";
        ResultSet rs;
        PreparedStatement statement = null;
        Connection conn = null;
        Apartamento apartamento;
        ArrayList<Apartamento> apartamentos = null;
        try{
            ConexaoBanco conexao = new ConexaoBanco("localhost","5432","tcc","postgres","Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            if(rs != null){
                apartamentos = new ArrayList<>();
                while(rs.next()){
                    apartamento = new Apartamento();
                    apartamento.setId(rs.getInt("id"));
                    apartamento.setNumero(rs.getString("numero"));
                    apartamentos.add(apartamento);
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
        return apartamentos;
    }

    public void ExcluirApartamento(int idApartamento){
        String sql = "DELETE FROM APARTAMENTO WHERE id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idApartamento);
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

    public void alterarApartamento(Apartamento apartamento){
        System.out.println("Método para alterar apartamento");
        String sql = "UPDATE APARTAMENTO SET numero = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, apartamento.getNumero());
            statement.setInt(2, apartamento.getId());
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

    public Apartamento buscarApartamentoPorId(int id){
        String sql = "SELECT * FROM APARTAMENTO WHERE ID = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;
        Apartamento apartamento = null;
        try {
            ConexaoBanco conexao = new ConexaoBanco("localhost", "5432", "tcc", "postgres", "Fenixbr999");
            conn = conexao.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if(rs != null && rs.next()){
                apartamento = new Apartamento();
                apartamento.setId(rs.getInt("id"));
                apartamento.setNumero(rs.getString("numero"));
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
        return apartamento;
    }
}
