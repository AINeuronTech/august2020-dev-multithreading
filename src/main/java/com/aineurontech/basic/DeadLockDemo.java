package com.aineurontech.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {

    private Lock lock1= new ReentrantLock(true);
    private Lock lock2= new ReentrantLock(true);
    public void operation1() throws InterruptedException {
        lock1.lock();
        System.out.println(" I am in operation one hold lock 1 and waiting for lock2");
        Thread.sleep(100);

        lock2.lock();
        System.out.println(" I am in operation one hold lock-2 and and lock1");
        Thread.sleep(50);
        lock2.unlock();
        lock1.unlock();
    }
    public void operation2() throws InterruptedException {
        lock1.lock();
        System.out.println(" I am in operation one hold lock 2 and waiting for lock1");
        Thread.sleep(100);

        lock2.lock();
        System.out.println(" I am in operation one hold lock-2 and and lock1");
        Thread.sleep(50);

        lock2.unlock();
        lock1.unlock();
    }
    public  static void main(String[] args){
        DeadLockDemo deadLockDemo = new DeadLockDemo();

        new Thread(()->{

            try {
                deadLockDemo.operation1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
        new Thread(()->{

            try {
                deadLockDemo.operation2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();


    }

}
