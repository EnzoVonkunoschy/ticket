package com.example.ticket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Oficina implements Observador, Serializable {
    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Ticket> tickets;

    public Oficina(String nom){
        this.nombre = nom;
        usuarios = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    public void recibirTicket(Ticket tick){
        tick.agregarObservador(this); // <-- Esta oficina se agrega como observadora del ticket que le llega
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
        /**/
        for(int i=0 ; i<this.tickets.size() ; i++){
            str += this.tickets.get(i).toString();
        }
        return str;
    }

    public String getNombre(){
        return this.nombre;
    }

    public boolean equals(Object obj){
        Oficina ofi = (Oficina) obj;
        return this.nombre.equals(ofi.nombre);
    }

    @Override
    public void actualizar(Ticket ticket) {
        System.out.println("--actualizar(Ticket)-->[Oficina]");
        if(this.tickets.contains(ticket)){
            int indice = this.tickets.indexOf(ticket);
            this.tickets.set(indice,ticket);
        }else{
            this.tickets.add(ticket);
        }
    }

}
