package data;

import java.util.concurrent.Semaphore;

public class Buffer {

    volatile int valores [];
    Semaphore sGet;
    Semaphore sPut;

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
    }

    public void put(int newValor) throws InterruptedException {
        sPut.acquire();
        valores[i++] = newValor;
        if (i == (bufferSize - 1)) i = 0;
        sGet.release();
    }

    public int get() throws InterruptedException{
        int temp = 0;
        sGet.acquire();
        temp = valores[j++];
        if (j == (bufferSize - 1)) j = 0;
        sPut.release();
        return temp;
    }

}
