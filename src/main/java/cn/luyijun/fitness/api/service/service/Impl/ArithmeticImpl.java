package cn.luyijun.fitness.api.service.service.Impl;

import cn.luyijun.fitness.api.service.service.Arithmetic;

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
