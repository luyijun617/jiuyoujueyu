package cn.luyijun.fitness.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ReadFileUtils {

    public static void main(String[] args) {
//        TestThread testThread = new TestThread();
//        //单独调用run()方法，是同步执行；通过start()调用run()，是异步执行。
//        testThread.start();
//        testThread.run();
//
//        TestRunnable testRunnable = new TestRunnable();
//        testRunnable.run();
//        new Thread(testRunnable).start();
//        new Thread(testRunnable).run();


        TestCallable testCallable = new TestCallable();
        FutureTask<String> task = new FutureTask<String>(testCallable);
//        new Thread(task).start();
//        new Thread(task).run();
        task.run();

        System.out.println("isDone: "+ task.isDone());
        try {
            System.out.println("get: "+ task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
