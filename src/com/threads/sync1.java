package com.threads;

/**
 * Created by yubraj on 1/7/17.
 */
public class sync1 {

    public static volatile int counter = 0;

    public synchronized static void increment(){
        counter++;
    }

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread 1 :: I am getting value of counter as "+counter);
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread 2 :: I am getting value of counter as "+counter);
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {
        process();
        System.out.println("Counter "+ counter);
    }

}
