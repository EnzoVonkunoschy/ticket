package com.example.ticket;

import java.util.ArrayList;

public class Modelo {

    public static ArrayList<Usuario> dameUsuarios(){
        Usuario usuario1 = new Usuario("Enzo","1234","2610123456","admin");
        Usuario usuario2 = new Usuario("Pedro","1234","2610123456","usuario");
        Usuario usuario3 = new Usuario("Carla","1234","2610123456","usuario");
        Usuario usuario4 = new Usuario("Dana","1234","2610123456","usuario");

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        usuarios.add(usuario4);

        return usuarios;
    }


    public static ArrayList<Oficina> dameOficinas(){
        ArrayList<Oficina> oficinas = new ArrayList<>();

        ArrayList<Usuario> usuarios_ = dameUsuarios();

        Ticket unTicket = new Ticket();
        Entrada unEntrada = new Entrada("Texto de la entrada 1 ticket 1",usuarios_.get(0),"mesa");
        Entrada dosEntrada = new Entrada("Texto de la entrada 2 ticket 1",usuarios_.get(1),"taller");

        Oficina administracion = new Oficina("Administracion");
        administracion.agregarUsuario(usuarios_.get(0));
        administracion.agregarUsuario(usuarios_.get(1));
        administracion.recibirTicket(unTicket);


        return null;
}


}