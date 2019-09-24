package cn.luyijun.fitness.interceptor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "call";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallable = new TestCallable();
        FutureTask futureTask = new FutureTask<>(testCallable);
        new Thread(futureTask,"futureTask123").start();
        Object o = futureTask.get();
        boolean done = futureTask.isDone();
        System.out.println(done);
        System.out.println(o);
        if(done){
            System.out.println("success");
        }
    }
}
