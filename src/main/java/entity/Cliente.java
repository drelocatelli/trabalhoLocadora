/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author andressa
 */
public class Cliente {

    private String codigo;
    private String nome;
    private String genero;
    private String cpf;
    private String endereco;

    public Cliente() {
    }

    public Cliente(Cliente cliente) {
        this.codigo = cliente.getCodigo();
        this.nome = cliente.getNome();
        this.genero = cliente.getGenero();
        this.cpf = cliente.getCpf();
        this.endereco = cliente.getEndereco();
    }

    public Cliente(String codigo, String nome, String genero, String cpf, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.genero = genero;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente [codigo=" + codigo + ", nome=" + nome + ", genero=" + genero + ", cpf=" + cpf + ", endereco="
                + endereco + "]";
    }

}
