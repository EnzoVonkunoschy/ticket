package com.example.ticket;

import java.io.Serializable;


public class Mensaje implements Serializable {

    private String destinatario;
    private String texto;

    public Mensaje(String destinatario, String texto) {
        this.destinatario = destinatario;
        this.texto = texto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Se utiliza al presionar Enviar para mostrar el mensaje por consola,
     */
    @Override
    public String toString() {
        return "Mensaje{" +
                "destinatario='" + destinatario + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }
}
