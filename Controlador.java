package com.example.ticket;

import java.util.ArrayList;

public class Controlador {

    private static Controlador instancia;
    private Modelo modelo = Modelo.getInstancia();

    private Controlador(){

    }

    public static Controlador getInstancia(){
        if(instancia == null){
            instancia = new Controlador();
        }
        return instancia;
    }

    public void agregarOficina(Usuario user, Oficina nueOfi){
        System.out.println("--> Controlador 'agregarOficina'");

        if(user.getRol().equals("admin")) {
            modelo.agregarOficina(nueOfi);
        }
    }

    public ArrayList<Oficina> dameOficinas(Usuario user){
        if(user.getRol().equals("admin")) {
            return modelo.dameOficinas();
        }
        return null;
    }

    public void eliminarOficina(Usuario user, String refOficina){
        if(user.getRol().equals("admin")){
            modelo.eliminarOficina(refOficina);
        }
    }

    public void nuevaOficina(Oficina ofi, Usuario usu){
        Modelo modelo = Modelo.getInstancia();

    }
}
