package com.pangpang.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: leewake
 * @create: 2020-02-19 14:01
 **/

public class PrintOddEven {
    public static volatile boolean flag = false;
    public static int TOTAL = 100;
    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread odd = new Thread(new OddThread());
        odd.setName("奇数线程");
        Thread even = new Thread(new EvenThread());
        even.setName("偶数线程");

        odd.start();
        even.start();
    }


    public static class OddThread implements Runnable {
        @Override
        public void run() {
            while (atomicInteger.get() <= TOTAL - 1) {
                if (!flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.getAndIncrement());
                    flag = true;
                }
            }
        }
    }

    public static class EvenThread implements Runnable {
        @Override
        public void run() {
            while (atomicInteger.get() <= TOTAL) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + ":" + atomicInteger.getAndIncrement());
                    flag = false;
                }
            }
        }
    }

}
