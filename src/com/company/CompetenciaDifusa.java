package com.company;

import java.util.ArrayList;

public class CompetenciaDifusa {
    String competencia;
    ArrayList<EtiquetaDifusa> etiquetaDifusas;

    public CompetenciaDifusa(String competencia, ArrayList<EtiquetaDifusa> etiquetaDifusas) {
        this.competencia = competencia;
        this.etiquetaDifusas = etiquetaDifusas;
    }

    public String getCompetencia() {
        return competencia;
    }

    public ArrayList<EtiquetaDifusa> getEtiquetaDifusas() {
        return etiquetaDifusas;
    }
}
