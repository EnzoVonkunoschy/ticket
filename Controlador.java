package com.example.ticket;

import java.util.ArrayList;

public class Controlador implements gestionarUsuarios {

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

    @Override
    public void guardarUsuarios(ArrayList<Usuario> colUsu, Usuario enSesion) {
        if (enSesion != null && enSesion.getRol().equals("admin")){
            modelo.guardarUsuarios(colUsu);
        }else{
            System.out.println("No tiene permiso");
        }
    }

    @Override
    public void agregarUsuario(Usuario usu, Usuario enSesion) {
        if (enSesion != null && enSesion.getRol().equals("admin")){
            modelo.agregarUsuario(usu);
        }else{
            System.out.println("No tiene permiso");
        }
    }

    @Override
    public void eliminarUsuario(String refUsuario, Usuario enSesion) {
        if (enSesion != null && enSesion.getRol().equals("admin")){
            modelo.eliminarUsuario(refUsuario);
        }else{
            System.out.println("No tiene permiso");
        }
    }

    @Override
    public ArrayList<Usuario> dameUsuarios(Usuario enSesion) {
        if (enSesion != null){
            return modelo.dameUsuarios();
        }
       return null;
    }
    public ArrayList<Usuario> dameUsuarios() {
        return modelo.dameUsuarios();
    }

    // Usuarios --------------------------------



}
