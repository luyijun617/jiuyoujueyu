package cn.luyijun.fitness.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {

    static{
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ExecutorService executorService02 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    public static void main(String[] args) {

        System.out.println("isDone: "+ Runtime.getRuntime());
        System.out.println("isDone: "+ Runtime.getRuntime().availableProcessors());
    }



}
