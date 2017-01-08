package com.threads;

/**
 * Created by yubraj on 1/7/17.
 */

class worker extends Thread{

    private volatile boolean terminated = false;

    public void run(){
        int i = 0;
        while(!terminated){
            System.out.println("I am worker class :: "+i);
            i++;
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}


public class SampleVolatile {
    public static void main(String[] args) throws InterruptedException {
        worker worker = new worker();
        Thread thread = new Thread(worker);
        thread.start();

        Thread.sleep(3000);

        worker.setTerminated(true);
        System.out.println("Process finished");


    }
}
