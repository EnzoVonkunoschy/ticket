package com.example.ticket;

import javafx.collections.ArrayChangeListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    // Atributos privados
    private String nombre;
    private String clave;
    private String mobil;
    private String rol;
    private ArrayList<Usuario> usuarios;

    // Constructor
    public Usuario(String nombre, String clave, String mobil, String rol) {
        this.nombre = nombre;
        this.clave = clave;
        this.mobil = mobil;
        this.rol = rol;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getMobil() {
        return mobil;
    }

    public String getRol() {
        return rol;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Sobrescritura de equals para comparar por contenido
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Usuario usuario = (Usuario) obj;
        return nombre.equals(usuario.nombre)
                && clave.equals(usuario.clave)
                && mobil.equals(usuario.mobil)
                && rol.equals(usuario.rol);
    }

    // Opcional: método toString para visualización
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    public void guardarUsuarios(ArrayList<Usuario>colUsu){
         this.usuarios = colUsu;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}