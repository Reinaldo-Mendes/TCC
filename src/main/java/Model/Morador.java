package Model;

import DAO.EncomendaDAO;
import DAO.MoradorDAO;

public class Morador {
    private String nome, numeroApartamento ;

    private String telefone;
    private int quantidadeEncomendas;
    private int idApartamento, idMorador;

    public Morador(){

    }

    public Morador(String nome, String telefone, int idApartamento){
        this.nome = nome;
        this.telefone = telefone;
        this.idApartamento = idApartamento;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(int idMorador) {
        this.idMorador = idMorador;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public int getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(int idApartamento) {
        this.idApartamento = idApartamento;
    }

    public int getQuantidadeEncomendas() {
        return quantidadeEncomendas;
    }

    public void setQuantidadeEncomendas(int quantidadeEncomendas) {
        this.quantidadeEncomendas = quantidadeEncomendas;
    }

    public void cadastrar(){
        new MoradorDAO().cadastrarMorador(this);
    }

    public void excluirMorador(int id){
        new MoradorDAO().ExcluirMorador(id);
    }

    public Morador buscarMoradorPorIdDoApartamento(int id){
        return new MoradorDAO().buscarMoradorPorIdDoApartamento(id);
    }

    public Morador buscarMoradorPorIdDaEncomenda(int id){
        return new MoradorDAO().BuscarMoradoresPorIdDaEncomenda(id);
    }
}
