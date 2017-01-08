package com.threads;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by yubraj on 1/7/17.
 */

class Processor2{

    private List<Integer> list = new ArrayList<>();
    private final int MAX = 5;
    private final int BASE = 0;
    private Object object = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
      synchronized (object){
          while (true){
              if(list.size() == MAX){
                  System.out.println("Waiting for empty item to list");
                  object.wait();
              }else {
                  System.out.println("Adding Value to list "+value);
                  list.add(value);
                  value++;
                  object.notify();
              }
              Thread.sleep(500);
          }
      }
    }

    public void consumer() throws InterruptedException {
        synchronized (object){
            while (true){
                if(list.size() == BASE){
                    System.out.println("Waiting for adding item to list");
                    object.wait();
                }else {
                    System.out.println("Removing Value to list "+ list.remove(--value));
                    object.notify();
                }
                Thread.sleep(500);
            }
        }
    }

}

public class Locks2 {
    public static void main(String[] args) throws InterruptedException {
        Processor2 processor = new Processor2();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
