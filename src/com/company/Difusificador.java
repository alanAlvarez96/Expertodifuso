package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Difusificador {
    public ArrayList<CompetenciaDifusa>difusificar(ArrayList<Competencia> Competencias){
        int calificacion,i,j;
        double gradoMembresia;
        Competencia competencia;
        CompetenciaDifusa competenciaDifusa;
        ArrayList<Etiqueta> etiquetas;
        ArrayList<EtiquetaDifusa>etiquetaDifusas;
        ArrayList<CompetenciaDifusa>competenciaDifusas=new ArrayList<>();
        EtiquetaDifusa etiquetaDifusa;
        Scanner calEntrada=new Scanner(System.in);
        Etiqueta etiqueta;
        //codigo a partir de aqui
        /*En escencia este metodo recibe una lista de competencias deterministas con sus respectivas*/
        for(i=0;i<Competencias.size();i++){//recorremos todas las competencias que nos envien
            etiquetaDifusas=new ArrayList<>();//limpiamos nuestra lista de etiquetas para dejarlas listas para usarse de nueva cuenta
            competencia=Competencias.get(i);//obtenemos la competencia segun el indice
            etiquetas=competencia.getEtiquetas();//obtenemos las etiquetas de la competencia actual
            System.out.println("ingrese la calificacion para: "+competencia.getCompetencia());//pedimos la calificiacion para la competencia
            calificacion=calEntrada.nextInt();//leemos la calificacion
            for(j=0;j<etiquetas.size();j++){//vamos a recorrer las etiquetas de la competencia actual
                 etiqueta=etiquetas.get(j);//obtenemos la etiqueda del indice correspondiente
                gradoMembresia=membresia(calificacion,etiqueta);//obtenemos el grado de membresia de la calificacion respecto a la etiqueta
                etiquetaDifusa=new EtiquetaDifusa(etiqueta.getEtiqueta(),gradoMembresia);//generamos la etiqueta difusa
                etiquetaDifusas.add(etiquetaDifusa);//añadimos a la lista de etiquetas difusas
            }
            competenciaDifusa=new CompetenciaDifusa(competencia.getCompetencia(),etiquetaDifusas);//generamos una nueva competencia difusa
            competenciaDifusas.add(competenciaDifusa);//agregamos nuesta competencia difusa a nuestra lista de competencias difusas
        }
        return competenciaDifusas;
    }
    private double membresia(int calificacion, Etiqueta etiqueta){
        double gMembresia=0;
        int[]rango,pc;
        rango=etiqueta.getRango();
        pc=etiqueta.getPuntosCriticos();
        double a,b,pc1,pc2;
        a=(double)rango[0];
        b=(double)rango[1];

        pc1=(double)pc[0];
        pc2=(double)pc[1];
        //comprobar si es triangular o trapesoidal
        if(pc2==-1){//si es triangular
            if(calificacion>a && calificacion<b){//comprobemos que la calificacion este entre el rango
                if(calificacion==pc1)//preguntemos si es el punto critico
                    return 1;
                if(calificacion<=pc1)//si el valor se encuentra antes del punto critico
                     gMembresia=((calificacion-a))/(pc1-a);
                if(calificacion>pc1)//si el valor se encuentra despues del punto critico
                     gMembresia=((b-calificacion))/(b-pc1);
            }
            else
                return 0;//esto se queda asi porque de inicio sabemos que se encuentra fuera del rango y su grado de membresia es 0
        }
        else {//si es trapesoidal
            if(calificacion>=a && calificacion<=b){//si esta dentro del rango
                if(pc1<=calificacion && calificacion<=pc2)//si esta entre el rango de los 2 puntos criticos
                    return 1;
                if(calificacion<=pc1)
                    gMembresia=((calificacion-a))/(pc1-a);
                if(calificacion>=pc2)
                    gMembresia=((b-calificacion))/(b-pc1);
            }
            else
                return 0;//si esta por debajo de a o por encima de b
        }
        return gMembresia;
    }
    public void imprimirDifusas(ArrayList<CompetenciaDifusa>cp){
        int i,j;
        CompetenciaDifusa cp1;
        ArrayList<EtiquetaDifusa>ldf;
        EtiquetaDifusa label;
        for(i=0;i<cp.size();i++){
            cp1=cp.get(i);
            ldf=cp1.getEtiquetaDifusas();
            System.out.println("---------------------------");
            System.out.println("competencia: "+cp1.getCompetencia());
            for(j=0;j<ldf.size();j++){
                label=ldf.get(j);
                System.out.println("etiqueta"+label.getNombre()+": "+label.gradoMembresia);
            }
        }
    }
}
