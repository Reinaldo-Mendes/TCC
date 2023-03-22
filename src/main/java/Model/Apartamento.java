package Model;

import DAO.ApartamentoDAO;
import DAO.EncomendaDAO;

import java.util.ArrayList;

public class Apartamento {
    private int id;
    private String numero;

    public Apartamento(String numeroApartamento) {
        this.numero = numeroApartamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Apartamento(){

    }



    public void cadastrar() {
        new ApartamentoDAO().cadastrarApartamento(this);
    }

    public ArrayList<Apartamento> listarTodosApartamentos(){
        return new ApartamentoDAO().ListarTodosApartamentos();
    }

    public void excluirApartamento(int id){
        new ApartamentoDAO().ExcluirApartamento(id);
    }

    public Apartamento buscarApartamentoPorId(int id){
        return new ApartamentoDAO().buscarApartamentoPorId(id);
    }
}
