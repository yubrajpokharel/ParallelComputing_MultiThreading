package com.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yubraj on 1/16/17.
 */




public class latch {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++)
            executorService.execute(new Worker(i, countDownLatch));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All the prerequities are done now the application is ready to run yuppy!!!");
        executorService.shutdown();

    }

}

class Worker implements Runnable{
    private int id;
    private CountDownLatch countDownLatch;
    private Random random;

    public Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        dowork();
        countDownLatch.countDown();
    }

    private void dowork() {
        System.out.println("Thread with id "+ this.id + " is Running .....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
