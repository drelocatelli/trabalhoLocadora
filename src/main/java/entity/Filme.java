/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author andressa
 */
public class Filme {
    private String codigo;
    private String titulo;
    private String genero;
    private long quantidade = 0;
    private long qtdEstoque = 0;
    private boolean isLocado = false;

    public Filme(Filme filme) {
        this.codigo = filme.getCodigo();
        this.titulo = filme.getTitulo();
        this.genero = filme.getGenero();
        this.quantidade = filme.getQuantidade();
        this.qtdEstoque = filme.getQtdEstoque();
        this.isLocado = filme.getIsLocado();
    }

    public Filme(String codigo, String titulo, String genero, long quantidade, long qtdEstoque, boolean isLocado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.genero = genero;
        this.quantidade = quantidade;
        this.qtdEstoque = qtdEstoque;
        this.isLocado = isLocado;
    }

    public Filme() {
        super();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public long getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(long qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public boolean getIsLocado() {
        return isLocado;
    }

    public void setIsLocado(boolean isLocado) {
        this.isLocado = isLocado;
    }

    @Override
    public String toString() {
        return "Filme [codigo=" + codigo + ", titulo=" + titulo + ", genero=" + genero + ", quantidade=" + quantidade
                + ", qtdEstoque=" + qtdEstoque + ", isLocado=" + isLocado + "]";
    }


}
