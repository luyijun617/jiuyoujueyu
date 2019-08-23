package cn.luyijun.fitness.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());

        return "call";
    }

    public static void main(String[] args) {
        TestCallable testCallable = new TestCallable();
        FutureTask futureTask = new FutureTask<>(testCallable);
        new Thread(futureTask,"futureTask123").start();
    }
}
