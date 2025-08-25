package com.example.ticket;

import java.util.ArrayList;
import java.util.Objects;

import java.util.List;

public class Ticket  implements Sujeto {
    private final ArrayList<Entrada> entradas;
    private String id;

    private List<Observador> observadores = new ArrayList<>();
    private String estado;

    public Ticket(Entrada ent){
        this.entradas = new ArrayList<>();
        this.id = Util.getUUid();
        this.entradas.add(ent);
    }

    public Ticket(){
        this.entradas = new ArrayList<>();
        this.id = Util.getUUid();
    }

    public String getId() {
        return this.id;
    }

    public String toString(){
        String str = "";
        str += "\nTICKET id: "+this.id;
        for( int i=0 ; i<this.entradas.size() ; i++){
            str += this.entradas.get(i).toString();
        }

        return str;
    }

    public void agregarEntrada(Entrada ent){
        this.entradas.add(ent);
        //System.out.println(" --> agregarEntrada(Entrada ent)");
        //notificarObservadores();
    }

    public boolean equals(Ticket tick){
        return Objects.equals(this.id, tick.id);
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        notificarObservadores();
    }
    public String getEstado() {
        return estado;
    }

    @Override
    public void agregarObservador(Observador obs) {
        System.out.println("--agregarObservador(Observador)-->[Ticket]");
        observadores.add(obs);
        notificarObservadores();
    }

    @Override
    public void eliminarObservador(Observador obs) {
        observadores.remove(obs);
    }

    @Override
    public void notificarObservadores() {
        System.out.println("--> notificarObservadores()");
        for (Observador obs : observadores) {
            obs.actualizar(this);
        }
    }

}
