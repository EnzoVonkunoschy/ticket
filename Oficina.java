package com.example.ticket;

import java.util.ArrayList;

public class Oficina{
    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Ticket> tickets;

    public Oficina(String nom){
        this.nombre = nom;
        usuarios = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    public void recibirTicket(Ticket tick){
        this.tickets.add(tick);
    }

    public void agregarUsuario(Usuario usu){
        this.usuarios.add(usu);
    }

    public String toString(){
        String str = "";
        str += "\n--- Oficina ---";
        str += "\nNombre: "+this.nombre;
        for(int i=0 ; i<this.usuarios.size() ; i++){
            str += "\n"+this.usuarios.get(i).toString();
        }
        for(int i=0 ; i<this.tickets.size() ; i++){
            str += this.tickets.toString();
        }
        return str;
    }
}
