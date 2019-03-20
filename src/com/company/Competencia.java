package com.company;

import java.util.ArrayList;

public class Competencia {
    String competencia;
    ArrayList<Etiqueta> etiquetas;
    int llave;
    public Competencia(){
        this.competencia = null;
        this.etiquetas = null;
        this.llave = 0;
    }
    public Competencia(String competencia, ArrayList<Etiqueta> etiquetas, int llave) {
        this.competencia = competencia;
        this.etiquetas = etiquetas;
        this.llave = llave;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }
}
