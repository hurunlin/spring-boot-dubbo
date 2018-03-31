package com.spring.boot.dubbo.thread;

public class Test {

    public static void main(String[] args) {
        Task task = new Task();
        for (int i = 0; i < 1000000; i++) {
            task.executor();
        }
    }
}
