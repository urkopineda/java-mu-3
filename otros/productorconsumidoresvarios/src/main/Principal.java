package main;

import java.util.Scanner;

import clientes.Consumidor;
import clientes.Productor;
import data.Buffer;

public class Principal {

    Scanner teclado;

    Productor productor;
    Consumidor consumidor[];

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
        for (int i = 0; i != maxHilos; i++) consumidor[i] = new Consumidor(buffer);
    }

    private void iniciarHilos() {
        productor.start();
        for (int i = 0; i != maxHilos; i++) consumidor[i].start();
    }

    private void matarHilos() {
        productor.kill();
        for (int i = 0; i != maxHilos; i++) consumidor[i].kill();
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
