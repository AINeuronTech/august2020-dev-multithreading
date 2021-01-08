package com.aineurontech.basic;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalDemo implements Runnable {

    /*private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };*/
    private final ThreadLocal<Integer> formatter = ThreadLocal.withInitial(() -> 1);
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    static  int test=5;

    @Override
    public void run() {

        System.out.println("Thread Name= "+Thread.currentThread().getName()+" Initial Value Thread Local = "+formatter.get());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(2);

        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get() +" Atomic "+atomicInteger.incrementAndGet() +  " static ="+ test);
        System.out.println(" Atomic ="+atomicInteger.getAndDecrement() );
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo example = new ThreadLocalDemo();
        Thread t1= new Thread(example,"T1");
        t1.start();
        Thread t2= new Thread(example,"T2");
        t2.start();
        Thread t3=new Thread(example,"T3");
        t3.start();
        t1.join();
        t2.join();
        t3.join();

    }
}
