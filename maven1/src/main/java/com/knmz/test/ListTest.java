package com.knmz.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zl
 * @Date: 2019/7/26 17:54
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");

        List<String> b = new ArrayList<String>();
        b.add("c");
        b.add("d");
        b.add("e");
        b.add("f");

        List<String> c = new ArrayList<String>(a);
        c.retainAll(b);
        System.out.println(c);

        List<String> d = new ArrayList<String>(a);
        d.removeAll(b);
        System.out.println(d);
    }
}
