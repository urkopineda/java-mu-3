package clientes;

import java.util.Random;

import data.Buffer;

public class Productor extends Thread {

    Random rand;
    Buffer buffer;

    volatile boolean endThread;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
        rand = new Random();
        endThread = false;
    }

    @Override
    public void run() {
        while (!endThread) {
            int temp = rand.nextInt(10);
            try {
                buffer.put(temp);
                System.out.println("PRODUCTOR: "+temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill() {
        endThread = true;
    }

}
