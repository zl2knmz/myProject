package com.cloud.lambda;

import org.junit.Test;

import java.util.function.IntBinaryOperator;

/**
 * lambda表达式：关注方法和方法体，不关注对象 关注操作
 * 基本格式：(参数列表) -> {代码}
 * <p>
 * idea快捷键： Alt + Enter 进行lambda表达式的切换 Replace
 *
 * @author zl
 * @date 2022/11/15 17:34
 */
public class Demo01 {
    public static void main(String[] args) {
        testRunnable();
        testRunnable1();
    }

    private static void testRunnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新的线程被执行了......");
            }
        }).start();
    }

    /**
     * 当匿名内部类只有一个实现方法时可以直接简化
     */
    private static void testRunnable1() {
        new Thread(() -> {
            System.out.println("新的线程被执行了......");
        }).start();
    }


    public int testIntBinaryOperator(IntBinaryOperator intBinaryOperator) {
        int a = 10;
        int b = 20;
        return intBinaryOperator.applyAsInt(a, b);
    }

    @Test
    public void testIntBinaryOperator1() {
        int i = testIntBinaryOperator(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(i);
    }

    @Test
    public void testIntBinaryOperator2() {
        int i = testIntBinaryOperator((int left, int right) -> {
            return left + right;
        });
        System.out.println(i);
    }

    @Test
    public void testIntBinaryOperator3() {
        int i = testIntBinaryOperator((left, right) -> left + right);
        System.out.println(i);
    }

    @Test
    public void testIntBinaryOperator4() {
        int i = testIntBinaryOperator(Integer::sum);
        System.out.println(i);
    }

}
