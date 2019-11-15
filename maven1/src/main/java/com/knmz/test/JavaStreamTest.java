package com.knmz.test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zl
 * @date 2019/3/17 21:49
 * java 8 list集合Stream应用测试
 */
public class JavaStreamTest {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("Hollis", "", "HollisChuang", "H", "hollis");
        System.out.println("filter");
        strings.stream().filter(string -> !string.isEmpty()).forEach(System.out::println);
        //Hollis, , HollisChuang, H, hollis

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("map");
        numbers.stream().map(i -> i * i).forEach(System.out::println);
        //9,4,4,9,49,9,25

        List<Integer> numbers1 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("limit");
        numbers1.stream().limit(4).forEach(System.out::println);
        //3,2,2,3

        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("skip");
        numbers2.stream().skip(4).forEach(System.out::println);
        //7,3,5

        List<Integer> numbers3 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("sorted");
        numbers3.stream().sorted().forEach(System.out::println);
        //2,2,3,3,3,5,7

        List<Integer> numbers4 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        System.out.println("distinct");
        numbers4.stream().distinct().forEach(System.out::println);
        //3,2,7,5

        List<String> strings1 = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "com.knmz.HelloWorld", "Hollis");
        Stream s = strings1.stream()
                .filter(string1 -> string1.length() <= 6)
                .map(String::length)
                .sorted()
                .limit(3)
                .distinct();
        System.out.println("stream");
        System.out.println(s.collect(Collectors.toList()));

        List<String> strings2 = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "com.knmz.HelloWorld", "Hollis");
        strings2 = strings2.stream()
                .filter(string2 -> string2.startsWith("Hollis"))
                .collect(Collectors.toList());
        System.out.println("stream1");
        System.out.println(strings2);
        //Hollis, HollisChuang, Hollis666, Hollis

        List<String> strings3 = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "com.knmz.HelloWorld", "Hollis");
        System.out.println("count");
        System.out.println(strings3.stream().count());
        //7

        Random random = new Random();
        System.out.println("random forEach");
        random.ints().limit(10).forEach(System.out::println);
    }
}
