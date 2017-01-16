package com.threads;

/**
 * Created by yubraj on 1/7/17.
 */

class Sample extends Thread{

    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println("The number is "+ i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Example {
    public static void main(String[] args) throws InterruptedException {
        Sample sample = new Sample();
        Thread thread = new Thread(sample);
        Thread thread2 = new Thread(sample);
        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        System.out.println("I am main class");


    }
}
