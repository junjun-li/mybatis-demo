package com.imooc.junit;

public class Calculator {
    // 加法运算
    public int add(int a, int b) {
        return a + b;
    }

    // 减法运算
    public int subtract(int a, int b) {
        return a - b;
    }

    // 乘法运算
    public int multiply(int a, int b) {
        return a * b;
    }

    // 除法运算
    public float divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divide by zero");
        }
        return (a * 1f) / b;
    }
}
