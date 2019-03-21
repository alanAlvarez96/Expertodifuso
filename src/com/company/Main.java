package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)  {
        Difusificador df=new Difusificador();
        ArrayList<Competencia>competencias;
        ArrayList<CompetenciaDifusa>cD;
        Competencia competencia;
        ArrayList<Etiqueta> etiquetas;
        Etiqueta etiqueta;
	    Maestro maestro=new Maestro();
        try {
            //maestro.EscribirMaestro();
            competencias=maestro.buscaSecuencial();
            /*for(int i=0;i<competencias.size();i++){
                competencia=competencias.get(i);
                maestro.MostrarRegla(competencia);
            }*/
            cD=df.difusificar(competencias);
            df.imprimirDifusas(cD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
