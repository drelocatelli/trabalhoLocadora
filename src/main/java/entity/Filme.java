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

    public boolean isIsLocado() {
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
