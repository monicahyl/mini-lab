package com.minilab.concurrent;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            int value = (int) (Math.random() * 100);
            threadLocal.set(value);
            System.out.println(Thread.currentThread().getName() + " set value: " + value);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " get value: " + threadLocal.get());
        };

        Thread t1 = new Thread(task, "Thread-A");
        Thread t2 = new Thread(task, "Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(threadLocal.get());
    }
}