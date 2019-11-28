package cn.luyijun.fitness.luyservice.service.Impl;

import cn.luyijun.fitness.luyservice.service.Arithmetic;

public class ArithmeticImpl implements Arithmetic {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int mul(int a, int b) {
        return a * b;
    }
}
