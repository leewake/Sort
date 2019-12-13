package com.pangpang.singleton;

/**
 * @description: 双重校验锁
 * @author: leewake
 * @create: 2019-12-13 14:41
 * @url: https://www.cnblogs.com/renyuanwei/p/9203088.html
 **/

public class TwoValidateLockSingleton {

    private static volatile TwoValidateLockSingleton singletonInstance = null;

    private TwoValidateLockSingleton() {
    }

    public static TwoValidateLockSingleton getInstance() {
        if (singletonInstance == null) {
            synchronized (TwoValidateLockSingleton.class) {
                if (singletonInstance == null) {
                    singletonInstance = new TwoValidateLockSingleton();
                }
            }
        }

        return singletonInstance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + TwoValidateLockSingleton.getInstance().hashCode())).start();
        }
    }
}
