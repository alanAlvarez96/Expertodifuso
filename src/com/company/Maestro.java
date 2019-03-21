package com.company;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Maestro {
    Indice indice;
    ArrayList<int[]>ListaPC=new ArrayList<>();
    ArrayList<int[]>ListaRangos=new ArrayList<>();
    int numEtiquetas,largoEtiqueta,largoCompetencia;
    long tamanoArchivo;
    String rangoEntrada,pcEntrada;
    String[]aux;
    public Maestro(){
        this.indice=new Indice();
        this.numEtiquetas=3;
        this.largoEtiqueta=1;
        this.largoCompetencia=2;
    }
    public void EscribirMaestro()throws IOException {
        int continuar=1;
        int llave,i,j;
        int []respuesta,rango,pc;
        RandomAccessFile Maestro;
        String etiqueta="x",competencia;
        StringBuffer datos;
        Scanner entrada=new Scanner(System.in);
        do{
            Maestro= new RandomAccessFile("Maestro","rw");
            tamanoArchivo=Maestro.length();//obtenemos el tamaño del archivo
            Maestro.seek(tamanoArchivo);//movemos el apuntador al final del archivo
            System.out.println("ingrese el numero de competencia a escribir");
            llave=entrada.nextInt();
            respuesta=indice.buscarIndice(llave);
            if(respuesta[0]==1){
                System.out.println("Competencia ya existente");
                System.out.println("Presione 0 para terminar o 1 para ingresar una competencia");
                continuar=entrada.nextInt();
            }
            else {
                Maestro.writeInt(llave);
                System.out.println("Ingrese el nombre de la competencia");
                competencia=entrada.next();
                for (i = 0; i < numEtiquetas; i++) {
                    if (i == 0)
                        Llenado("insuficiente");
                    if (i == 1)
                        Llenado("bueno");
                    if (i == 2)
                        Llenado("excelente");
                }//al terminar este ciclo deberiamos tener en las listas los arreglos con los puntos criticos y los rangos de todas las etiquetas
                //pasamos a escribir
                indice.escribir_indice(llave);
                datos=new StringBuffer(competencia);//convertimos en buffer el nombre de la comptencia
                datos.setLength(largoCompetencia);//establecemos el largo de la palabra
                Maestro.writeChars(datos.toString());//escribimos la competencia en el archivo
                for(i=0;i<numEtiquetas;i++){//escribiremos el nombre de la etiqueta y los rangos
                    if(i==0 || i==1 || i==2){
                        if(i==0)
                            etiqueta="i";
                        if(i==1)
                            etiqueta="b";
                        if(i==2)
                            etiqueta="e";
                        datos=new StringBuffer(etiqueta);
                        datos.setLength(largoEtiqueta);
                        Maestro.writeChars(datos.toString());//aqui ya se escribio la etiqueta
                        rango=ListaRangos.get(i);//obtenemos los rangos 0 para insuficiente,1 para bueno 2 para excelente
                        for(j=0;j<rango.length;j++)
                            Maestro.writeInt(rango[j]);//escribimos los valores del rango
                        pc=ListaPC.get(i);
                        for(j=0;j<pc.length;j++)
                            Maestro.writeInt(pc[j]);//escribimos los puntos criticos
                    }
                    else {
                        etiqueta="x";
                        datos=new StringBuffer(etiqueta);
                        datos.setLength(largoEtiqueta);
                        Maestro.writeChars(datos.toString());//aqui ya se escribio la etiqueta
                        for(j=0;j<4;j++)
                            Maestro.writeInt(-1);// esto va a escribir un -1 cuatro veces que son el equivalente a nuestros 4 espacios 2 para rango y 2 para puntos criticos
                    }
                }//aqui ya deberiamos tener todas las etiquetas con sus rangos y puntos criticos escritos
                Maestro.close();
                ListaRangos.clear();
                ListaPC.clear();
                System.out.println("Presione 0 para terminar o 1 para ingresar una competencia");
                continuar=entrada.nextInt();
            }//fin de else
        }while (continuar!=0);
    }
    private long desplazamiento()throws IOException{
        long desplazamiento;
        int i,j;
        RandomAccessFile file=new RandomAccessFile("Maestro","rw");
        file.readInt();//lees la llave
        for(j=0;j<largoCompetencia;j++)
            file.readChar();//lee todos los caracteres del nombre de la competencia
        for(i=0;i<numEtiquetas;i++){
            for(j=0;j<largoEtiqueta;j++)
                file.readChar();//lee la etiqueta
            for(j=0;j<4;j++)
                file.readInt();//lee los cuatro enteros osea 2 de rango y 2 de puntos criticos
        }
        desplazamiento=file.getFilePointer();
        file.close();
        return  desplazamiento;
    }
    public ArrayList<Competencia> buscaSecuencial()throws IOException{
        ArrayList<Competencia> competencias=new ArrayList<>();
        ArrayList<Etiqueta> etiquetas=new ArrayList<>();
        RandomAccessFile file;
        int i,j,key,n=0;
        int[] rango,pc;
        long apActual,apFinal;
        String comp,label;
        char[] c=new char[largoCompetencia];
        char[] e=new char[largoEtiqueta];
        Etiqueta etiqueta;
        Competencia competencia;
        file=new RandomAccessFile("Maestro","rw");
        while ((apActual=file.getFilePointer())!=(apFinal=file.length())){
            n=n+1;
            etiquetas.clear();
            key=file.readInt();
            for(i=0;i<largoCompetencia;i++)
                c[i]=file.readChar();
            comp=new String(c);//aqui esto deberia de tener el nombre de la competencia
            for(j=0;j<numEtiquetas;j++){
                rango=new int[2];
                pc=new int[2];
                for(i=0;i<largoEtiqueta;i++)
                    e[i]=file.readChar();
                label=new String(e);//esto deberia tener el nombre del label
                for(i=0;i<2;i++){
                    rango[i]=file.readInt();//obtenmos los rangos
                    //System.out.println(" "+rango[i]);
                }
                for(i=0;i<2;i++) {
                    pc[i] = file.readInt();//obtenemos los puntos criticos
                }
                etiqueta=new Etiqueta(label,pc,rango);
                etiquetas.add(etiqueta);
            }
            competencia=new Competencia(comp,etiquetas,key);
            competencias.add(competencia);
            MostrarRegla(competencia);
        }//fin del while
        file.close();
        return competencias;
    }
    public Competencia buscarAleatorio(int llave)throws IOException{
        RandomAccessFile file;
        Competencia competencia1;
        ArrayList<Etiqueta> ListaEtiquetas=new ArrayList<>();
        Etiqueta label;
        int i,j,posicion,key;
        String competencia="",etiqueta="";
        char temp;
        int []respuesta;
        int []rango;
        int []pc;
        long longitud=desplazamiento();
        long desplazamiento;
        respuesta=indice.buscarIndice(llave);
        posicion=respuesta[1];
        if(respuesta[0]==-1){
            System.out.println("el registro no existe");
            competencia1=new Competencia();
        }
        else{
            file=new RandomAccessFile("Maestro","rw");
            desplazamiento=(posicion-1)*longitud;
            file.seek(desplazamiento);
            key=file.readInt();//obtenemos la llave
            for(j=0;j<largoCompetencia;j++) {
                temp=file.readChar();//lee todos los caracteres del nombre de la competencia
                competencia=competencia+temp;//concatenamos el nombre
            }
            for(i=0;i<numEtiquetas;i++){//vamos a leer las etiquetas
                rango=new int[2];
                pc=new int[2];
                for(j=0;j<largoEtiqueta;j++){
                    temp=file.readChar();//lee la etiqueta
                    etiqueta=etiqueta+temp;
                }
                for(j=0;j<2;j++)
                    rango[j]=file.readInt();
                for(j=0;j<2;j++)
                    pc[j]=file.readInt();
                label=new Etiqueta(etiqueta,pc,rango);
                ListaEtiquetas.add(label);
                etiqueta="";
            }//aqui ya leimos todas las etiquetas y tenemos una lista con ellas
            competencia1=new Competencia(competencia,ListaEtiquetas,key);
        }
        MostrarRegla(competencia1);
        return competencia1;
    }
    private void Llenado(String etiqueta){
        int i;
        int[]rango=new int[2];
        int[]puntosCriticos=new int[2];
        Scanner entrada=new Scanner(System.in);
        System.out.println("ingrese el rango para"+etiqueta+" ejemplo 0-50");
        rangoEntrada=entrada.next();//leemos la entrada
        aux=rangoEntrada.split("-");//separamos la entrada con el guion
        for (i=0;i<rango.length;i++)
            rango[i]=Integer.parseInt(aux[i]);//llenamos el rango el rango con los valores ingresados
        System.out.println("ingrese los puntos criticos separados por comas");
        System.out.println("Si solo hay uno ingresar -1 ejemplo 10,20 o 10,-1");
        pcEntrada=entrada.next();//leemos la entrada
        aux=pcEntrada.split(",");//separamos los puntos criticos
        for(i=0;i<puntosCriticos.length;i++)
            puntosCriticos[i]=Integer.parseInt(aux[i]);//llenamos los puntos criticos
        ListaPC.add(puntosCriticos);
        ListaRangos.add(rango);
    }
    public void MostrarRegla(Competencia competencia){
        ArrayList<Etiqueta> etiqueta=competencia.getEtiquetas();
        Etiqueta etiqueta1;
        int[]rango,pc;
        System.out.println("competencia: "+competencia.getCompetencia());
        for(int i=0;i<etiqueta.size();i++){
            etiqueta1=etiqueta.get(i);
            rango=etiqueta1.getRango();
            pc=etiqueta1.getPuntosCriticos();
            System.out.println("etiqueta: "+etiqueta1.etiqueta);
            System.out.println("Rango:"+rango[0]+"-"+rango[1]);
            System.out.println("Puntos critucos: "+pc[0]+","+pc[1]);
        }
    }
}
