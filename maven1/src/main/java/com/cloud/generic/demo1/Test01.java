package com.cloud.generic.demo1;

import java.util.ArrayList;

/**
 * 泛型：1.编译时类型检测
 *  2.避免类型转换
 * @author zl
 * @date 2022/5/3 15:40
 */
public class Test01 {
    public static void main(String[] args) {
        /*ArrayList list = new ArrayList();
        list.add("java");
        list.add(100);
        list.add(true);

        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            // ClassCastException
            String str = (String) o;
            System.out.println(str);
        }*/

        // 泛型：编译时类型检测 避免类型转换
        ArrayList<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");
        for (String s : strList) {
            System.out.println(s);
        }



    }


}
