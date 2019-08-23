package cn.luyijun.fitness.controller;

public class TestController {

    private static int i = 100;
    public static int j = 100;
    private int a = 100;
    public int b = 100;

    public static void main(String[] args) {
        TestController testController = new TestController();
        testController.i ++;
        System.out.println(i);
        testController = new TestController();
        testController.i ++;
        System.out.println(i);
        TestController.i--;
        System.out.println(i);
    }
}
