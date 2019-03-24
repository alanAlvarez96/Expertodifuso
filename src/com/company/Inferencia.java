package com.company;
import java.util.ArrayList;
//alternativa un arreglo que mantenga todos los valores
public class Inferencia {
    public void inferir(ArrayList<CompetenciaDifusa> competenciaDifusas){
        System.out.println("entre al metodo inferir");
        int i,j,x,n=0,aux;//aux nos va a ayudar a mantener el control de la etiqueda que se va a leer de las competencias n+2
        double []minimo=new double[3];
        double max,min3;
        ArrayList<Double> maximosSalida=new ArrayList<>();//Lista con los maximos de cada lista
        ArrayList<Double>reprobado=new ArrayList<>();//arreglo de minimos para los reprobados
        ArrayList<Double>casiAprobado=new ArrayList<>();//arreglo de minimos para casi aprobados
        ArrayList<Double>aprobado=new ArrayList<>();//arreglo de minimos para aprobados
        ArrayList<Double>aprobadoMerito=new ArrayList<>();//arreglo de minimos para aprobados con merito
        CompetenciaDifusa competenciaDifusa,cp2,cp3;
        ArrayList<EtiquetaDifusa> etiquetaDifusas,listLabel1,listLabel2;//labelD2 nos va a tener las etiquetas de la competencia 2 que son las que se recorren
        EtiquetaDifusa labelD,labelD2,labelD3;
        ArrayList<CompetenciaDifusa> competenciaDifusas1=competenciaDifusas;
        /*En escencia lo que haremos es obtener la primer competencia de la fila y combinarla con la seguda que a su vez se combinara con la 3 etc sacando los minimos
        para cada combinacion y agregandolas 4 arreglos distintos dependiendo de a donde deba ir
         */
        //lo  primero es recorrer cada competencia
        while (n<competenciaDifusas.size()){//debe recorrer por todas las competencias
            n++;//aumentamos n para poder seguir avanzando
            competenciaDifusa=competenciaDifusas.get(0);//sacamos la primer competencia de la lista
            etiquetaDifusas=competenciaDifusa.getEtiquetaDifusas();//obtenemos las etiquetas de la competencia
            for(i=0;i<etiquetaDifusas.size();i++){//vamos a recorrer las etiquetas de la primer competencia de la lista
                labelD=etiquetaDifusas.get(i);//etiqueta de la primer competencia de la lista
                minimo[0]=labelD.getGradoMembresia();
                cp2=competenciaDifusas1.get(1);//obtenemos siempre la segunda competencia de la lista
                listLabel1=cp2.getEtiquetaDifusas();
                for(aux=0;aux<listLabel1.size();aux++){//recorremos las etiquetas  de la 2 competencia de la fila
                    labelD2=listLabel1.get(aux);//obtenemos la etiqueta y vamos a compararla conetra
                    minimo[1]=labelD2.getGradoMembresia();//obtenemos el grado de membresia de la segunda etiqueta
                    for(j=2;j<competenciaDifusas1.size();j++){//comparemos contra la tercer competencia  de la lista en adelante
                        cp3=competenciaDifusas1.get(j);//obtenemos la competencia
                        listLabel2=cp3.getEtiquetaDifusas();//obtenemos sus etiquetas
                        for(x=0;x<listLabel2.size();x++){//recorremos todas las etiquetas
                            labelD3=listLabel2.get(x);//obtenemos la primer etiqueta
                            minimo[2]=labelD3.getGradoMembresia();
                            min3=Minimo(minimo);
                            //System.out.println("minimo: "+min3);
                            if((i+aux+x)<=1)
                                reprobado.add(min3);
                            if((i+aux+x)==2)
                                casiAprobado.add(min3);
                            if((i+aux+x)>2 && (i+aux+x)<=4)
                                aprobado.add(min3);
                            if((i+aux+x)>4)
                                aprobadoMerito.add(min3);
                        }
                    }
                }
            }//aqui ya deberiamos tener todas las etiquetas de la primer competencia comparadas contra todas las de 2 en adelante
            //ahora hay que pasar al primer miembro de la lista al final para que asi durante el ciclo todas se combinen con todas
            competenciaDifusas1.add(competenciaDifusa);//agregamos la competencia 1 al final de la lista
            competenciaDifusas1.remove(0);//eliminamos la primer competencia de tal forma que la segunda se vuelve la primera
        }//fin del while
        //en este punto los arreglos de minimos deberian estar listos para sacarles los maximos
        //obtenemos el maximo de los reprobados
        max=Maximo(reprobado);
        maximosSalida.add(max);
        //obtenemos el maximo de los casiAprobados
        max=Maximo(casiAprobado);
        maximosSalida.add(max);
        //obtenemos los maximos de los aprobados
        max=Maximo(aprobado);
        maximosSalida.add(max);
        //obtenemos el maximo de los aprobados con merito
        max=Maximo(aprobadoMerito);
        maximosSalida.add(max);
        imprimeMaximo(maximosSalida);
    }
    private double Maximo(ArrayList<Double>lista){
        int i;
        double max;
        max=lista.get(0);//obtenemos el primer valor
        for(i=1;i<lista.size();i++){
            if(max<lista.get(i))
                max=lista.get(i);
        }
        return max;
    }
    private double Minimo(double[]min){
        double m;
        int i;
        m=min[0];
        for(i=1;i<min.length;i++){
            if(m>min[i])
                m=min[i];
        }
        return m;
    }
    private void imprimeMaximo(ArrayList<Double> maximos){
        int i;
        for(i=0;i<maximos.size();i++){
            if(i==0)
                System.out.println("membresia de reprobado: "+maximos.get(i));
            if(i==1)
                System.out.println("membresia de casi aprobado: "+maximos.get(i));
            if(i==2)
                System.out.println("membresia de aprobado: "+maximos.get(i));
            if(i==3)
                System.out.println("membresia de aprobado con merito: "+maximos.get(i));
        }
    }
}
