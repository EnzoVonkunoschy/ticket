package com.example.ticket;

public class Mensaje {
    private Usuario remitente;
    private Object destinatario; // como objet para que guarde oficia y usuarios
    private String texto;

    // Constructor
    public Mensaje(Usuario remitente, Object destinatario, String texto) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.texto = texto;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    public Object getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Object destinatario) {
        this.destinatario = destinatario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        String nombreDest = "Sin destinatario";
        if (destinatario instanceof Usuario) {
            nombreDest = ((Usuario) destinatario).getNombre();
        } else if (destinatario instanceof Oficina) {
            nombreDest = ((Oficina) destinatario).getNombre();
        } else if (destinatario != null) {
            nombreDest = destinatario.toString();
        }

        return "Mensaje {" +
                "De: " + (remitente != null ? remitente.getNombre() : "Desconocido") +
                ", Para: " + nombreDest +
                ", Texto: '" + texto + '\'' +
                '}';
    }
}