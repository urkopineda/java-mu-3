package data;

import java.util.concurrent.Semaphore;

public class Buffer {

    volatile int valores [];
    Semaphore sGet;
    Semaphore sPut;
    Semaphore sPro;

    Object o1;
    Object o2;

    int i = 0;
    int j = 0;

    int bufferSize = 4;

    public int getJ() {
        return j;
    }

    public int[] getValores() {
        return valores;
    }

    public Buffer(int maxHilos) {
        sGet = new Semaphore(0);
        sPut = new Semaphore(maxHilos);
        valores = new int[bufferSize];
        o1 = new Object();
        o2 = new Object();
    }

    public void put(int newValor) throws InterruptedException {
        sPut.acquire();
        synchronized (o1) {
            valores[i++] = newValor;
            if (i == (bufferSize - 1)) i = 0;
        }
        sGet.release();
    }

    public int get() throws InterruptedException {
        int temp;
        sGet.acquire();
        synchronized (o2) {
            temp = 0;
            temp = valores[j++];
            if (j == (bufferSize - 1)) j = 0;
        }
        sPut.release();
        return temp;
    }

}
