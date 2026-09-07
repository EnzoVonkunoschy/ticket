package com.example.ticket;

//import com.sun.tools.jconsole.JConsoleContext;

import java.util.ArrayList;

public class Seguridad {

    private static Seguridad instancia;
    private Controlador controlador = Controlador.getInstancia();
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


    public Usuario validar(String usu, String cla){
        ArrayList<Usuario> listaUsuarios = controlador.dameUsuarios();

        for(Usuario usua : listaUsuarios){
            System.out.println(usua);
            if (usua.getNombre().equals(usu) && usua.getClave().equals(cla)){
                System.out.println("Usuario encontrado");
                System.out.println(usua);
                return usua;
            }
        }
        return null;

    }

    public Usuario buscarUsuarioPorNombre(String nombre){
        ArrayList<Usuario> listaUsuarios = controlador.dameUsuarios();

        for(Usuario usuario : listaUsuarios){
            if(usuario.getNombre().equalsIgnoreCase(nombre)){
                return usuario;
            }
        }
        return null;
    }

    public String ok(){
        return "Seguridad: OK";
    }

    public  Usuario getUsuario(String usu, String cla){
        //ArrayList<Usuario> mod_usuarios = Modelo.dameUsuarios();
        if (usu.equals("Lucho") && cla.equals("2341")){
            return new Usuario("Lucho","2341","2616667777","admin");
        }else{
            return null;
        }

    }
}
