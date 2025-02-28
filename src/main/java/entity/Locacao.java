/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author andressa
 */
public class Locacao {
    private Cliente cliente;
    private Filme filme;
    
    public Locacao() {
        super();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    @Override
    public String toString() {
        String clienteClass = String.format("Cliente [ codigo=%s, nome=%s, cpf=%s ] ", cliente.getCodigo(), cliente.getNome(), cliente.getCpf());
        String filmeClass = String.format("Filme [ codigo=%s, titulo=%s, genero=%s ]", filme.getCodigo(), filme.getTitulo(), filme.getGenero());
        
        return String.format( "Locacao [ %s, %s ]", clienteClass, filmeClass);
    }
    

}
