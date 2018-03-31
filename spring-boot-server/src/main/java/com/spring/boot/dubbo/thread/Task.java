package com.spring.boot.dubbo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task {


    private ExecutorService es = Executors.newFixedThreadPool(10);


    /**
     * 执行
     */
    public void executor() {
        es.submit(new Callable() {
            @Override
            public Object call() {
                try {
                    Thread.sleep(3000);
                    System.out.println("执行" + Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

}
