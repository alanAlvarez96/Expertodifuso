package com.company;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Indice {
    public int[] buscarIndice(int llave)throws IOException{
        //El metodo regresa un arreglo de 2 posicion en la posicion 0 regresa un 1 si existe y un -1 si no
        //En la posicion 1 regresa la posicion en la que se encontro el  registro o la posicion siguiente a escribir
        RandomAccessFile file;
        int llaveLeida,posicion=-1,i,existe=-1;
        long tamaño;
        int []respuesta = new int[2];
        file=new RandomAccessFile("indice","rw");
        tamaño=file.length();
        if(tamaño==0){
            existe=-1;
            posicion=1;
        }
        else {
            for (i=0;i<tamaño/8;i++){
                llaveLeida=file.readInt();
                if(llave==llaveLeida){
                    existe=1;
                    posicion=i+1;
                    file.readInt();
                    break;
                }
                else{
                    existe=-1;
                    posicion=i+1;
                    file.readInt();
                }
            }//fin del for
        }//fin else
        respuesta[0]=existe;
        respuesta[1]=posicion;
        file.close();
        return respuesta;
    }
    public boolean escribir_indice(int llave)throws IOException{
        boolean escrito;
        long longitud;
        int [] respuesta;
        RandomAccessFile file;
        respuesta=buscarIndice(llave);//busca si la regla ya existe  o si no existe en que posicion debemos escribirla
        if(respuesta[0]==-1){
            file=new RandomAccessFile("indice","rw");
            longitud=file.length();//obtiene la longitud del archivo
            file.seek(longitud);//mueve el apuntador al final del archivo
            file.writeInt(llave);// escribe la nueva regla
            if(respuesta[1]==1)//si es la primer regla entonces escribe la posicion 1
                file.writeInt(1);
            else
                file.writeInt(respuesta[1]+1);//caso donde no es la primer regla y ña escribe en la ultima posicion mas uno
            file.close();
            escrito=true;
        }
        else
            escrito=false;

        return escrito;
    }
    public void borrarIndice(int llave) throws IOException {
        int[] posicion;
        int pos;
        long longitud,desplazamiento;
        RandomAccessFile file;
        posicion=buscarIndice(llave);
        if(posicion[0]==1){
            file=new RandomAccessFile("indice","rw");
            longitud=desplazamiento();
            pos=posicion[1];
            desplazamiento=(pos-1)*longitud;
            file.seek(desplazamiento);
            file.writeInt(-1);
        }
        else
            System.out.println("intento borrar un registro que no existe");
    }
    public long desplazamiento() throws IOException {
        long desp;
        RandomAccessFile file=new RandomAccessFile("indice","rw");
        file.readInt();
        file.readInt();
        desp=file.getFilePointer();
        file.close();
        return desp;
    }
}
