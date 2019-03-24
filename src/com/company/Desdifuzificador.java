package com.company;

import java.util.ArrayList;

public class Desdifuzificador {
    private final int[]r={0,45},ca={35,65},a={60,90},am={85,100};
    private final int[]rpc={20,-1},capc={45,55},apc={70,80},ampc={95,100};
    private final Etiqueta re=new Etiqueta("r",r,rpc);
    private final Etiqueta cap=new Etiqueta("ca",ca,capc);
    private final Etiqueta ap=new Etiqueta("ap",a,apc);
    private final Etiqueta apm=new Etiqueta("am",am,ampc);
    public double centroide(ArrayList<Double> inferencias) {
        double membresia = 0;
        double caliMembresia = 0;
        for (double i = 0.0; i < 100.0; i+=1) {
            caliMembresia += i * membresia(i, re, inferencias.get(0));
            caliMembresia += i * membresia(i, cap,inferencias.get(1));
            caliMembresia += i * membresia(i, ap, inferencias.get(2));
            caliMembresia += i * membresia(i, apm,inferencias.get(3));
            membresia += membresia(i, re, inferencias.get(0));
            membresia += membresia(i, cap, inferencias.get(1));
            membresia += membresia(i, ap, inferencias.get(2));
            membresia += membresia(i, apm, inferencias.get(3));
        }
        if(membresia==0)
            System.out.println("division 0");
        return caliMembresia / membresia;
    }
    private double membresia(double calificacion, Etiqueta etiqueta,double maximo){
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
        if(maximo>0) {
            if (pc2 == -1) {//si es triangular
                if (calificacion > a && calificacion < b) {//comprobemos que la calificacion este entre el rango
                    if (calificacion == pc1)//preguntemos si es el punto critico
                        return maximo;
                    if (calificacion <= pc1)//si el valor se encuentra antes del punto critico
                        gMembresia = ((calificacion - a)) / (pc1 - a);
                    if (calificacion > pc1)//si el valor se encuentra despues del punto critico
                        gMembresia = ((b - calificacion)) / (b - pc1);
                } else
                    return 0;//esto se queda asi porque de inicio sabemos que se encuentra fuera del rango y su grado de membresia es 0
            } else {//si es trapesoidal
                if (calificacion >= a && calificacion <= b) {//si esta dentro del rango
                    if (pc1 <= calificacion && calificacion <= pc2)//si esta entre el rango de los 2 puntos criticos
                        return maximo;
                    if (calificacion <= pc1)
                        gMembresia = ((calificacion - a)) / (pc1 - a);
                    if (calificacion >= pc2)
                        gMembresia = ((b - calificacion)) / (b - pc1);
                } else
                    return 0;//si esta por debajo de a o por encima de b
            }
        }
        else
            return 0;
        return gMembresia;
    }
}
