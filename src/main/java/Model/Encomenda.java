package Model;

import DAO.EncomendaDAO;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Encomenda {
    private String nomeFornecedor;
    private String nomeTransportadora;
    private String status;
    private String dataRecebimento;
    private String horarioRecebimento;
    private String dataRetirada;
    private String horarioRetirada;
    private String descricao;

    private String numeroApartamento;
    private int idApartamento;

    private int idEncomenda;


    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }


    public Encomenda(){

    }
    public Encomenda(String nomeFornecedor, String status, String dataRecebimento, String horarioRecebimento, String descricao, int idApartamento) {
        this.nomeFornecedor = nomeFornecedor;
        this.status = status;
        this.dataRecebimento = dataRecebimento;
        this.horarioRecebimento = horarioRecebimento;
        this.descricao = descricao;
        this.idApartamento = idApartamento;
    }

    public void cadastrar() {
        new EncomendaDAO().cadastrarEncomenda(this);
    }

    public ArrayList<Encomenda> buscarEncomendasPorDescricao(String descricao){
        return new EncomendaDAO().BuscarEncomendasPorDescricao(descricao);
    }

    public ArrayList<Encomenda> listarTodasEncomendas(){
        return new EncomendaDAO().ListarTodasEncomendas();
    }

    public Encomenda buscarEncomendaPorId(int id){
        return new EncomendaDAO().buscarEncomendaPorId(id);
    }



    public void excluirEncomenda(int id){
        new EncomendaDAO().ExcluirEncomenda(id);
    }
    public int getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(int idApartamento) {
        this.idApartamento = idApartamento;
    }


    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getHorarioRetirada() {
        return horarioRetirada;
    }

    public void setHorarioRetirada(String horarioRetirada) {
        this.horarioRetirada = horarioRetirada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeTransportadora() {
        return nomeTransportadora;
    }

    public void setNomeTransportadora(String nomeTransportadora) {
        this.nomeTransportadora = nomeTransportadora;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getHorarioRecebimento() {
        return horarioRecebimento;
    }

    public void setHorarioRecebimento(String horarioRecebimento) {
        this.horarioRecebimento = horarioRecebimento;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

}
