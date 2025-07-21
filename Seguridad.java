package com.example.ticket;

import java.util.ArrayList;

public class Seguridad {

    public static boolean validar(String usu, String cla){
        ArrayList<Usuario> mod_usuarios = Modelo.dameUsuarios();
        //Si usu (usuario) y cla (clave) coinciden con cualquier usuario
        // coloca a ese usuario como usuario en sesión y retorna true
        //sinó retorna false
        return true;
    }

    public static Usuario dameUsuario(String usu, String cla){
        ArrayList<Usuario> mod_usuarios = Modelo.dameUsuarios();
        //Retornar el usuario que coincide
        return new Usuario("Enzo","1234","2610123456","admin");
    }
}
