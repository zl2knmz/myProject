package com.knmz.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 17:54
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add(null);
        System.out.println(a.get(4));
        System.out.println(a);

        List<String> b = new ArrayList<>();
        b.add("c");
        b.add("d");
        b.add("e");
        b.add("f");

        List<String> c = new ArrayList<>(a);
        // 两个list集合取交集
        c.retainAll(b);
        System.out.println(c);

        List<String> d = new ArrayList<>(a);
        // 两个list集合取差集
        d.removeAll(b);
        System.out.println(d);

        List<String> e = new ArrayList<>();
        e.add("a");
        e.add("e");

        List<String> f = new ArrayList<>(a);
        // 差集
        e.removeAll(f);
        System.out.println(e);

        List<String> list = new ArrayList<>();
        String str1 = "123";
        String str2 = "456";
        list.add(str1);
        list.add(str2);
        System.out.println(list);
        /*str1 = "789";
        list.add(str1);
        System.out.println(list);*/

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("123")) {
                list.set(i, "789");
                break;
            }
        }
        System.out.println(list);
    }
}
