package com.company;

public class Difusificador {

    public void difusificar(){

    }
    private double membresia(int calificacion, Etiqueta etiqueta){
        int gMembresia=0;
        int[]rango,pc;
        rango=etiqueta.getRango();
        pc=etiqueta.getPuntosCriticos();
        int a,b,pc1,pc2;
        a=rango[0];
        b=rango[1];
        pc1=pc[0];
        pc2=pc[1];
        //comprobar si es triangular o trapesoidal
        if(pc2==-1){//si es triangular
            if(calificacion>a && calificacion<b){//comprobemos que la calificacion este entre el rango
                if(calificacion==pc1)//preguntemos si es el punto critico
                    return 1;
                if(calificacion<=pc1)//si el valor se encuentra antes del punto critico
                     gMembresia=(calificacion-a)/(pc1-a);
                if(calificacion>pc1)//si el valor se encuentra despues del punto critico
                     gMembresia=(b-calificacion)/(b-pc1);
            }
            else
                return 0;//esto se queda asi porque de inicio sabemos que se encuentra fuera del rango y su grado de membresia es 0
        }
        else {//si es trapesoidal
            if(calificacion>a && calificacion<b){//si esta dentro del rango
                if(pc1<=calificacion && calificacion<=pc2)//si esta entre el rango de los 2 puntos criticos
                    return 1;
                if(calificacion<=pc1)
                    gMembresia=(calificacion-a)/(pc1-a);
                if(calificacion>=pc2)
                    gMembresia=(b-calificacion)/(b-pc1);
            }
            else
                return 0;//si esta por debajo de a o por encima de b
        }
        return gMembresia;
    }
}
