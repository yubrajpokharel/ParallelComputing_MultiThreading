package com.threads;

/**
 * Created by yubraj on 1/7/17.
 */

class Processor{
    public void consumer() throws InterruptedException {
        synchronized (this){
            System.out.println("Consumer started");
            wait();
            System.out.println("I am the the end of consumer");
        }
    }

    public void producer() throws InterruptedException {
        synchronized (this){
            Thread.sleep(1000);
            System.out.println("I am in producer");
            notify();
        }
    }

}

public class Locks {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
