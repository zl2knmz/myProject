package com.knmz.test;

import org.junit.Test;

/**
 * 封装类测试
 *
 * @author zl
 * @date 2021/5/18 10:28
 */
public class PackageClassTest {
    @Test
    public void longTest() {

        // Long Object initialization
        Long ob1 = new Long(1343434343434L);
        Long ob2 = new Long(1343434343434L);

        // Display ob1,ob2 values
        System.out.println("ob1: " + ob1);
        System.out.println("ob2: " + ob2);

        // It compare two objects of Long type
        // by calling ob1.equals(ob2)
        boolean compare = ob1.equals(ob2);

        // Display result values
        System.out.println("ob1.equals(ob2): " + compare);

        if (compare) {
            System.out.println("Both objects are equal");
        } else {
            System.out.println("Both objects are not equal");
        }
    }
}
