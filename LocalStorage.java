package com.example.ticket;

import java.io.*;
import java.util.ArrayList;

class LocalStorage{
    // Serializaci�n
	public static void setItem(String nombre, Object objeto){
		try {
			FileOutputStream archivoSalida = new FileOutputStream(nombre);
			ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida);
			objetoSalida.writeObject(objeto);
			objetoSalida.close();
			archivoSalida.close();
			//System.out.println("Objeto serializado y guardado en archivo.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getItem(String nomArchivo){
		try {
			FileInputStream archivoEntrada = new FileInputStream(nomArchivo);
			ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);
			T objetoDeserializado = (T) objetoEntrada.readObject();
			objetoEntrada.close();
			archivoEntrada.close();
			return objetoDeserializado;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Track del error: ");
			return null;
		}
	}



	/*
	public static Object getItem(String nomArchivo){
	       // Deserializaci�n
		//Object objetoDeserializado = null;
		Object objetoDeserializado = new Object();
		try {
		    FileInputStream archivoEntrada = new FileInputStream(nomArchivo);
		    ObjectInputStream objetoEntrada = new ObjectInputStream(archivoEntrada);
		    objetoDeserializado = objetoEntrada.readObject();
		    objetoEntrada.close();
		    archivoEntrada.close();
		    //System.out.println("Objeto deserializado desde archivo.ser");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Track del error: ");
		    //.printStackTrace();
			return new ArrayList<>();
		}
		return objetoDeserializado;
	}*/
}
