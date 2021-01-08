package com.aineurontech.basic;

public class BasicThread {

    public static void main(String[] args) {
        System.out.println("Thread Inside Main="+Thread.currentThread().getName());

        for(int i=0;i<10;i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
        System.out.println("Main Thread Ends");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {

            try{
                Thread.sleep(100);
            }catch(Exception exception){

            }
            System.out.println("Thread Inside Runnable="+Thread.currentThread().getName());

        }
    }
}
