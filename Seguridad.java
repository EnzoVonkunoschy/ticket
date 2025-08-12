package com.example.ticket;

import com.sun.tools.jconsole.JConsoleContext;

import java.util.ArrayList;

public class Seguridad {

    private static Seguridad instancia;
    //private Controlador controlador = Controlador.getInstancia();
    private Seguridad(){

    }

    public static Seguridad getInstancia(){
        if(instancia == null){
            instancia = new Seguridad();
        }
        return instancia;
    }

    //------------------------------
    public void nuevaOficina(Oficina ofi, Usuario usu){
        System.out.println("--> Seguridadd 'nuevaOficina'");
        System.out.println(ofi);
        System.out.println(usu);
        Controlador controlador = Controlador.getInstancia();
        controlador.agregarOficina(usu, ofi);
    }

    public boolean validar(String usu, String cla){
        return usu.equals("Enzo") && cla.equals("1234");

    }

    public String ok(){
        return "Seguridad: OK";
    }

    public  Usuario getUsuario(String usu, String cla){
        //ArrayList<Usuario> mod_usuarios = Modelo.dameUsuarios();
        //Retornar el usuario que coincide
        return new Usuario("Enzo","1234","2610123456","admin");
    }
}
