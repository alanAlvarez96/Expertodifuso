package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args)  {
	    Maestro maestro=new Maestro();
        try {
            maestro.buscarAleatorio(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
