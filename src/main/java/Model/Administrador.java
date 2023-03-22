package Model;

import DAO.AdministradorDAO;

public class Administrador {

    public Administrador(){

    }
    int id;
    String nome, senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Administrador validarLogin(String nome, String senha){
        return new AdministradorDAO().ValidarLogin(nome, senha);
    }
}
