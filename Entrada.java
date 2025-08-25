package com.example.tickettt;

public class Entrada {
    private String texto;
    private Usuario autor;
    private String nomOficina;

    public Entrada(String txt, Usuario aut, String nomOfic){
        this.texto = txt;
        this.autor = aut;
        this.nomOficina = nomOfic;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String toString(){
        String str = "\n";
        str += this.texto +"\nAutor: "+this.autor.getNombre()+" - Oficina: "+this.nomOficina;
        return str;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
