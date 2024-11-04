package com.trab.java;

import java.util.Objects;

public class Cliente {

    private int id;
    private String nome;
    private String email;  // Novo atributo
    private String telefone;  // Novo atributo
    private String endereco;  // Novo atributo
    private String cpf;  // Novo atributo
    private String servico1;  // Novo atributo para serviço
    private String servico2;  // Novo atributo para serviço
    private String servico3;  // Novo atributo para serviço

    public Cliente() {
    }

    public Cliente(int id, String nome, String email, String telefone, String endereco, String cpf,
                   String servico1, String servico2, String servico3) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.servico1 = servico1;
        this.servico2 = servico2;
        this.servico3 = servico3;
    }

    // Getters e Setters para os novos atributos
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getServico1() {
        return servico1;
    }

    public void setServico1(String servico1) {
        this.servico1 = servico1;
    }

    public String getServico2() {
        return servico2;
    }

    public void setServico2(String servico2) {
        this.servico2 = servico2;
    }

    public String getServico3() {
        return servico3;
    }

    public void setServico3(String servico3) {
        this.servico3 = servico3;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return id == cliente.id &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(email, cliente.email) &&
                Objects.equals(telefone, cliente.telefone) &&
                Objects.equals(endereco, cliente.endereco) &&
                Objects.equals(cpf, cliente.cpf) &&
                Objects.equals(servico1, cliente.servico1) &&
                Objects.equals(servico2, cliente.servico2) &&
                Objects.equals(servico3, cliente.servico3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, telefone, endereco, cpf, servico1, servico2, servico3);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nome='" + getNome() + "'" +
                ", email='" + getEmail() + "'" +
                ", telefone='" + getTelefone() + "'" +
                ", endereco='" + getEndereco() + "'" +
                ", cpf='" + getCpf() + "'" +
                ", servico1='" + getServico1() + "'" +
                ", servico2='" + getServico2() + "'" +
                ", servico3='" + getServico3() + "'" +
                "}";
    }
}
