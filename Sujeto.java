package com.example.ticketlujan3;

public interface Sujeto {
    void agregarObservador(Observador obs);
    void eliminarObservador(Observador obs);
    void notificarObservadores();
}
