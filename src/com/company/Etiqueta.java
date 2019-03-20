package com.company;

public class Etiqueta {
    String etiqueta;
    int[]puntosCriticos;
    int[]rango;

    public Etiqueta(String etiqueta, int[] puntosCriticos, int[] rango) {
        this.etiqueta = etiqueta;
        this.puntosCriticos = puntosCriticos;
        this.rango = rango;
    }
    public String getEtiqueta() {
        return etiqueta;
    }

    public int[] getPuntosCriticos() {
        return puntosCriticos;
    }

    public int[] getRango() {
        return rango;
    }
}
