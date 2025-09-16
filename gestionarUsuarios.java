package com.example.ticket;

import java.util.ArrayList;

public interface gestionarUsuarios {

    public void guardarUsuarios(ArrayList<Usuario> colUsu, Usuario enSesion);

    public void agregarUsuario(Usuario usu, Usuario enSesion);

    public void eliminarUsuario(String refUsuario, Usuario enSesion);

    public ArrayList<Usuario> dameUsuarios(Usuario enSesion);
}
