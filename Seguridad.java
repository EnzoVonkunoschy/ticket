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

    public boolean validar(String usu, String cla){
        Usuario adminTemp = new Usuario("", "", "", "");
        ArrayList<Usuario> listaUsuarios = controlador.dameUsuarios(adminTemp);

        listaUsuarios.add(new Usuario("Lucho","2341","2616667777","admin"));

        for(Usuario usua : listaUsuarios){
            if (usua.getNombre().equals(usu) && usua.getClave().equals(cla)){
                return true;
            }
        }
        return false;

    }

    public String ok(){
        return "Seguridad: OK";
    }

    public  Usuario getUsuario(String usu, String cla){
        //ArrayList<Usuario> mod_usuarios = Modelo.dameUsuarios();
        //Retornar el usuario que coincide
        if (usu.equals("Lucho") && cla.equals("2341")){
            return new Usuario("Lucho","2341","2616667777","admin");
        }else{
            return null;
        }

    }
}
