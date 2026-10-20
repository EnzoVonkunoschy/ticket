package com.example.ticket;

public class Entrada {
    private String texto;
    private Usuario autor;
    private Object destinatario;

    public Entrada(String txt, Usuario aut, Object destinatario){
        this.texto = txt;
        this.autor = aut;
        this.destinatario = destinatario;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String toString(){
        String str = "\n-----------------:";
        str +="\nRemitente: "+this.autor.getNombre()+"\nDestinatario: "+this.destinatario+"\nMensaje: "+this.texto;
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
