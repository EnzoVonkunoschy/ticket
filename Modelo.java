package com.example.ticket;

import java.util.ArrayList;

public class Modelo {

    private static Modelo instancia;

    private Modelo() {};

    public static Modelo getInstancia(){
        if(instancia == null){
            instancia = new Modelo();
        }
        return instancia;
    }

    // Sección Oficinas

   public void agregarOficina(Oficina ofi){
       System.out.println("--> Modelo 'agregarOficina");

       String ruta = System.getProperty("user.dir");
       String storage = ruta+"\\src\\main\\java\\com\\example\\";
       String storageOficinas = storage + "oficinas.txt";

       ArrayList<Oficina> colOfi = dameOficinas();
        if(!colOfi.contains(ofi)){
            colOfi.add(ofi);
        }else{
            System.out.println("La oficina ya estaba agregada !");
        }


       guardarOficinas(colOfi);
   }

   public ArrayList<Oficina> dameOficinas(){
       String ruta = System.getProperty("user.dir");
       String storage = ruta+"\\src\\main\\java\\com\\example\\";
       String storageOficinas = storage + "oficinas.txt";

        ArrayList<Oficina> objectx = LocalStorage.getItem(storageOficinas);
        //agreger nueva linea de codigo
       if (objectx == null) {
           return new ArrayList<Oficina>();
       }
       return objectx;
   }

   public void guardarOficinas(ArrayList<Oficina>  colOfi){

       String ruta = System.getProperty("user.dir");
       String storage = ruta+"\\src\\main\\java\\com\\example\\";
       String storageOficinas = storage + "oficinas.txt";

       LocalStorage.setItem(storageOficinas, colOfi);
   }

   public void eliminarOficina(String refOficina){

       ArrayList<Oficina> colOfi = dameOficinas();
        int indice = -1;
       for(int i=0 ; i<colOfi.size() ; i++) {
           System.out.println(colOfi.get(i).getNombre()+"<--");
           if(colOfi.get(i).getNombre().equals(refOficina)){

               System.out.println("La coincidencia i es: "+ i);
               indice = i;
           }
       }
       if(indice != -1){
           colOfi.remove(indice);
       }
       guardarOficinas(colOfi);
   }

   // Sección Usuarios
   public void guardarUsuarios(ArrayList<Usuario> colUsu){
       String ruta = System.getProperty("user.dir");
       String storage = ruta + "\\src\\main\\java\\com\\example\\";
       String storageUsuarios = storage + "usuarios.txt";

       LocalStorage.setItem(storageUsuario

    public void guardarUsuarios(ArrayList<Usuario> colUsu){};

    public void agregarUsuario(Usuario usu){};

    public void eliminarUsuario(String refUsuario){};

    public ArrayList<Usuario> dameUsuarios(){return new ArrayList<>();}

}