package thread;

import java.util.concurrent.BrokenBarrierException;

import data.Buffer;

public class Consumidor extends Thread {

    Buffer buffer;
    int id;    
    volatile boolean endThread;

    public Consumidor(int id, Buffer buffer) {
        this.id = id;
    	this.buffer = buffer;
        endThread = false;
    }

    @Override
    public void run() {
        while (!endThread) {
        	int valor = 0;
            try {
            	valor = buffer.get(id);
            } catch (InterruptedException e) {
                return;
            } catch (BrokenBarrierException e) {
                return;
            }
        }
    }

    public void kill() {
        endThread = true;
    }

}