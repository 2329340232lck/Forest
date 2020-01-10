package com.demo.forest.Test;

/**
 * threadLocal类测试
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        /*ThreadLocal类用于在不同线程保存不同的局部变量，当各个线程运行时需要各个不同的变量时，就可以考虑
        使用ThreadLocal类*/

        //变量1
        final ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        //变量2
        final ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        //线程1
        new Thread(() -> {
            threadLocal1.set("A");
            threadLocal2.set("1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        }).start();
        //线程2
        new Thread(() -> {
            threadLocal1.set("B");
            threadLocal2.set("2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        }).start();
        //线程3
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        }).start();
    }
}
