package com.cloud.test;

import com.cloud.model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void listObjectTest() {
        List<Student> employeeList = new ArrayList<>();
        Student student = new Student("zl1", "RD", null);
        employeeList.add(student);
        for (Student employee: employeeList) {
            System.out.println("1 " + employee);
        }
        student.setEmpID("1001");
        for (Student employee: employeeList) {
            System.out.println("2 " + employee);
        }

        Student student1 = new Student();
        student1.setName("zhangSan");
        employeeList.add(student1);
        for (Student employee: employeeList) {
            System.out.println("3 " + employee);
        }
        student1.setDept("FD");
        for (Student employee: employeeList) {
            System.out.println("4 " + employee);
        }
    }

    @Test
    public void listContainsTest() {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        System.out.println("result1 " + a.contains("a"));

        List<String> listA = Arrays.asList("a", "c", "d");
        System.out.println("result2 " + a.containsAll(listA));
        System.out.println("result3 " + listA.containsAll(a));

        String[] array = new String[]{"a", "e", "f"};
        List<String> listB = Arrays.asList(array);

        // 两个list集合取交集
        boolean result4 = a.retainAll(listB);
        System.out.println("result4 " + result4);

        // 运行报错 UnsupportedOperationException
        boolean result5 = listB.retainAll(a);
        System.out.println("result5 " + result5);
    }

}
