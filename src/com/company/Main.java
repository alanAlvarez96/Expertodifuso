package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Difusificador df=new Difusificador();
        Desdifuzificador desdifuzificador=new Desdifuzificador();
        Inferencia inferir=new Inferencia();
        double calificacion;
        ArrayList<Competencia>competencias;
        ArrayList<Double> maximos=new ArrayList<>();
        ArrayList<CompetenciaDifusa>cD;
        Competencia competencia;
        ArrayList<Etiqueta> etiquetas;
        Etiqueta etiqueta;
        Maestro maestro=new Maestro();
        Scanner s=new Scanner(System.in);
        int opcion,llave;
        do{
            System.out.println("Seleccion opción deseada");
            System.out.println("1.-Agregar");
            System.out.println("2.-Ver todas");
            System.out.println("3.-Ver una");
            System.out.println("4.-Difusificar");
            System.out.println("5.-Inferir");
            System.out.println("7.-Salir");

            opcion=s.nextInt();
            switch (opcion){
                case 1:
                    maestro.EscribirMaestro();
                    break;
                case 2:
                    competencias=maestro.buscaSecuencial();
                    for(int i=0;i<competencias.size();i++){
                        competencia=competencias.get(i);
                        maestro.MostrarRegla(competencia);
                    }
                    break;
                case 3:{
                    System.out.println("ingresa la llave de la regla");
                    llave=s.nextInt();
                    competencia=maestro.buscarAleatorio(llave);
                    maestro.MostrarRegla(competencia);
                }break;
                case 4:{
                    competencias=maestro.buscaSecuencial();
                    cD=df.difusificar(competencias);
                    df.imprimirDifusas(cD);
                }break;
                case 5:{
                    competencias=maestro.buscaSecuencial();
                    cD=df.difusificar(competencias);
                    maximos=inferir.inferir(cD);
                    calificacion=desdifuzificador.centroide(maximos);
                    System.out.println("la calificacion es: "+calificacion);
                }break;
            }

        }while (opcion < 7);
    }
}
