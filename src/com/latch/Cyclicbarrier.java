package com.latch;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by yubraj on 1/16/17.
 */
public class Cyclicbarrier {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier countDownLatch = new CyclicBarrier(5, new Runnable(){
            public void run(){
                System.out.println("all the tasks finised");
            }
        });

        for (int i = 0; i < 5; i++)
            executorService.execute(new Worker2(i, countDownLatch));

        executorService.shutdown();

    }
}


class Worker2 implements Runnable{
    private int id;
    private CyclicBarrier latch;
    private Random random;

    public Worker2(int id, CyclicBarrier latch) {
        this.id = id;
        this.latch = latch;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            doTask();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doTask() throws BrokenBarrierException, InterruptedException {
        System.out.println("thread with id "+this.id+" starting working .......");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread with id "+this.id+" stopped working !!!");

        latch.await();
        System.out.println("I am await for" + this.id);
    }
}