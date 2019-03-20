package com.company;

public class EtiquetaDifusa {
    String nombre;
    double gradoMembresia;

    public EtiquetaDifusa(String nombre, double gradoMembresia) {
        this.nombre = nombre;
        this.gradoMembresia = gradoMembresia;
    }

    public String getNombre() {
        return nombre;
    }

    public double getGradoMembresia() {
        return gradoMembresia;
    }
}
