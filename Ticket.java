package com.example.ticket;

import java.util.ArrayList;
import java.util.Objects;

public class Ticket {
    private final ArrayList<Entrada> entradas;
    private String id;

    public Ticket(Entrada ent){
        this.entradas = new ArrayList<>();
        this.id = Util.getUUid();
        this.entradas.add(ent);
    }

    public Ticket(){
        this.entradas = new ArrayList<>();
        this.id = Util.getUUid();
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
    }

    public boolean equals(Ticket tick){
        return Objects.equals(this.id, tick.id);
    }
}
