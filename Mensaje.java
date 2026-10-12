package com.example.ticket;

public class Mensaje {

    private Object destinatario;
    private String texto;

    public Mensaje(Object destinatario, String texto) {
        this.destinatario = destinatario;
        this.texto = texto;
    }

    public Object getDestinatario() {
        return destinatario;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return "----TICKET----\n" +
                "Destinatario: " + destinatario + "\n" +
                "Texto: " + texto;
    }
}
