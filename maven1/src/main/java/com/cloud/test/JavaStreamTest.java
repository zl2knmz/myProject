package com.cloud.test;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        String a = String.join(",", strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList()));
        System.out.println("a=" + a);

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

        List<String> strings1 = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "com.cloud.HelloWorld", "Hollis");
        Stream s = strings1.stream()
                .filter(string1 -> string1.length() <= 6)
                .map(String::length)
                .sorted()
                .limit(3)
                .distinct();
        System.out.println("stream");
        // [5, 6]
        System.out.println(s.collect(Collectors.toList()));

        List<String> strings2 = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "com.cloud.HelloWorld", "Hollis");
        strings2 = strings2.stream()
                .filter(string2 -> string2.startsWith("Hollis"))
                .collect(Collectors.toList());
        System.out.println("stream1");
        // [Hollis, HollisChuang, Hollis666, Hollis]
        System.out.println(strings2);
        //Hollis, HollisChuang, Hollis666, Hollis

        List<String> strings3 = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hollis666", "Hello", "com.cloud.HelloWorld", "Hollis");
        System.out.println("count");
        System.out.println(strings3.stream().count());
        //7

        // 产生10个随机数
        Random random = new Random();
        System.out.println("random IntStream forEach");
        IntStream intStream = random.ints();
        intStream.limit(10).forEach(System.out::println);
    }

    /**
     * boolean anyMatch(Predicate<? super T> predicate); // 元素是否有任意一个满足条件
     * boolean allMatch(Predicate<? super T> predicate); // 元素是否都满足条件
     * boolean noneMatch(Predicate<? super T> predicate); // 元素是否都不满足条件
     */
    @Test
    public void match() {
        boolean b = Stream.of("1", "3", "3", "4", "5", "1", "7")
                .map(Integer::parseInt)
                //.allMatch(s -> s > 0)
                //.anyMatch(s -> s >4)
                .noneMatch(s -> s > 4);
        System.out.println(b);
    }

    /**
     * 如果我们需要找到某些数据，可以使用find方法来实现。
     */
    @Test
    public void find() {
        Optional<String> first = Stream.of("10", "3", "3", "4", "5", "1", "7").findFirst();
        // 10
        System.out.println(first.get());

        Optional<String> any = Stream.of("111", "3", "3", "4", "5", "1", "7").findAny();
        // 111
        System.out.println(any.get());
    }

    /**
     * 最大值、最小值
     */
    @Test
    public void max() {
        Optional<Integer> max = Stream.of("1", "3", "3", "4", "5", "1", "7")
                .map(Integer::parseInt)
                .max((o1, o2) -> o1 - o2);
        // 7
        System.out.println(max.get());

        Optional<Integer> min = Stream.of("1", "3", "3", "4", "5", "1", "7")
                .map(Integer::parseInt)
                .min((o1, o2) -> o1 - o2);
        // 1
        System.out.println(min.get());

    }

    /**
     * 如果需要将所有数据归纳得到一个数据，可以使用reduce方法。
     */
    @Test
    public void reduce() {
        Integer sum = Stream.of(4, 5, 3, 9)
                // identity默认值
                // 第一次的时候会将默认值赋值给x
                // 之后每次会将 上一次的操作结果赋值给x y就是每次从数据中获取的元素
                .reduce(0, (x, y) -> {
                    System.out.println("x=" + x + ",y=" + y);
                    return x + y;
                });
//        x=0,y=4
//        x=4,y=5
//        x=9,y=3
//        x=12,y=9
//        21
        System.out.println(sum);

        // 获取 最大值
        Integer max = Stream.of(4, 5, 3, 9)
                .reduce(0, (x, y) -> {
                    return x > y ? x : y;
                });
        // 9
        System.out.println(max);
    }

    /**
     * 在实际开发中我们经常会将map和reduce一块来使用。
     */
    @Test
    public void mapReduce() {
        // 1.求出所有年龄的总和
        Integer sumAge = Stream.of(
                        new Person("张三", 18)
                        , new Person("李四", 22)
                        , new Person("张三", 13)
                        , new Person("王五", 15)
                        , new Person("张三", 19)
                ).map(Person::getAge) // 实现数据类型的转换
                .reduce(0, Integer::sum);
        // 87
        System.out.println(sumAge);

        // 2.求出所有年龄中的最大值
        Integer maxAge = Stream.of(
                        new Person("张三", 18)
                        , new Person("李四", 22)
                        , new Person("张三", 13)
                        , new Person("王五", 15)
                        , new Person("张三", 19)
                ).map(Person::getAge) // 实现数据类型的转换，符合reduce对数据的要求
                .reduce(0, Math::max); // reduce实现数据的处理
        // 22
        System.out.println(maxAge);

        // 3.统计 字符 a 出现的次数
        Integer count = Stream.of("a", "b", "c", "d", "a", "c", "a")
                .map(ch -> "a".equals(ch) ? 1 : 0)
                .reduce(0, Integer::sum);
        // 3
        System.out.println(count);

    }

    /**
     * 如果需要将Stream中的Integer类型转换成int类型，可以使用mapToInt方法来实现。
     */
    @Test
    public void match4() {
        // Integer占用的内存比int多很多，在Stream流操作中会自动装修和拆箱操作
        Integer[] arr = {1, 2, 3, 5, 6, 8};
        Stream.of(arr)
                .filter(i -> i > 0)
                .forEach(System.out::println);
        System.out.println("---------");
        // 为了提高程序代码的效率，我们可以先将流中Integer数据转换为int数据，然后再操作
        IntStream intStream = Stream.of(arr)
                .mapToInt(Integer::intValue);
        intStream.filter(i -> i > 3)
                .forEach(System.out::println);

    }

    /**
     * 如果有两个流，希望合并成为一个流，那么可以使用Stream接口的静态方法concat。
     */
    @Test
    public void concat() {
        Stream<String> stream1 = Stream.of("a", "b", "c");
        Stream<String> stream2 = Stream.of("x", "y", "z");
        // 通过concat方法将两个流合并为一个新的流
        Stream.concat(stream1, stream2).forEach(System.out::println);
        // a b c x y z
    }

    /**
     * String类型转Long
     * List<String> -> List<Long>
     */
    @Test
    public void mapToLong() {
        String roleIds = "11,22";
        List<String> stringList = Arrays.asList(roleIds.split(","));
        System.out.println(stringList);

        // 方法一
        List<Long> roleList = new ArrayList<>();
        stringList.stream().mapToLong(Long::parseLong).forEach(roleList::add);
        System.out.println(roleList);

        // 方法二
        List<Long> longList = stringList.stream().map(Long::parseLong).collect(Collectors.toList());
        System.out.println(longList);
    }

    /**
     * 一个java 8的stream是由三部分组成的。
     * 1 数据源，
     * 2 零个或一个或多个中间操作，
     * 3 一个或零个终止操作。
     * 中间操作是对数据的加工，注意，中间操作是lazy操作，并不会立马启动，需要等待终止操作才会执行。
     */
    @Test
    public void testPeek() {
        // peek主要被用在debug用途
        IntStream.of(1, 2, 3, 4, 5)
                .filter(e -> e >= 3)
                .peek(value -> System.out.printf("filter element: %d\n", value))
                .mapToObj(String::valueOf)
                .forEach(System.out::println);

//        filter element: 3
//        3
//        filter element: 4
//        4
//        filter element: 5
//        5
    }

    @Data
    static class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

}
