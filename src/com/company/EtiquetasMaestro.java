package com.company;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EtiquetasMaestro {
    int largoEtiqueta=20;
    public int[]buscarEtiqueta(int id)throws IOException {
        RandomAccessFile file=new RandomAccessFile("Etiqueta","rw");
        int idLeido,existe=0,posicion=0,i;
        long tamano;
        int []respuesta=new int[2];
        tamano=file.length();
        if(tamano==0){
            existe=-1;
            posicion=1;
        }
        else{
        }
        respuesta[0]=existe;
        respuesta[1]=posicion;
        return  respuesta;
    }
}
