package com.pangpang.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: leewake
 * @create: 2020-02-19 14:01
 **/

public class NoVisibility {
    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        Thread a=new Thread(new AThread());
        Thread b=new Thread(new BThread());

        a.start();
        b.start();
    }


    public static class AThread implements Runnable {
        @Override
        public void run() {
            while (atomicInteger.get() <= 100) {
                synchronized (atomicInteger) {
                    if (atomicInteger.intValue() % 2 != 0) {
                        System.out.println("奇数线程:" + atomicInteger.intValue());
                        atomicInteger.getAndIncrement();
                        // 奇数线程释放锁资源
                        atomicInteger.notify();
                        try {
                            atomicInteger.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class BThread implements Runnable {
        @Override
        public void run() {
            while (atomicInteger.get() <= 100){
                synchronized (atomicInteger){
                    if(atomicInteger.intValue() % 2 == 0){
                        System.out.println("偶数线程:"+ atomicInteger.intValue());
                        atomicInteger.getAndIncrement();
                        // 偶数线程释放锁资源
                        atomicInteger.notify();
                        try {
                            if (atomicInteger.get() < 100) {
                                atomicInteger.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
