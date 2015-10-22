package main;

import java.util.Scanner;

import data.Buffer;
import thread.Consumidor;
import thread.Productor;

public class Principal {

    Scanner teclado;

    Productor productor;
    Consumidor consumidor;

    Buffer buffer;

    static int maxHilos = 4;

    public Principal() {
        teclado = new Scanner(System.in);
    }

    private void crearBuffer() {
        buffer = new Buffer(maxHilos);
    }

    private void crearHilos() {
        productor = new Productor(buffer);
        consumidor = new Consumidor(buffer);
    }

    private void iniciarHilos() {
        productor.start();
        consumidor.start();
    }

    private void matarHilos() {
        productor.kill();
        consumidor.kill();
    }

    private void iniciar() {
        crearBuffer();
        crearHilos();
        iniciarHilos();
        teclado.nextLine();
        matarHilos();
    }

    public static void main(String [] args) {
        Principal programa = new Principal();
        programa.iniciar();
    }

}
