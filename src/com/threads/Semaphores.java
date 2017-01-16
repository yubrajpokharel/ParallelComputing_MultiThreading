package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by yubraj on 1/8/17.
 */

enum Downloader{
    INSTANCE;
    private Semaphore semaphores = new Semaphore(5, true);

    public void downloadData(){
        try {
            semaphores.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphores.release();
        }
    }

    private void download() {
        System.out.println("Downloading data from the web");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class Semaphores {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 12; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Downloader.INSTANCE.downloadData();
                }
            });
        }
    }
}
