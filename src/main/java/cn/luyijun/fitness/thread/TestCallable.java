package cn.luyijun.fitness.thread;

import java.util.concurrent.Callable;

public class TestCallable implements Callable {
    @Override
    public String call() throws Exception {
        System.out.println("TestCallable 方法执行了");
        return "--------TestCallable----------";
    }
}
