package com.cloud.test;

import com.cloud.model.Student;
import com.google.common.collect.ArrayListMultimap;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        for (Student employee : employeeList) {
            System.out.println("1 " + employee);
        }
        student.setEmpID("1001");
        for (Student employee : employeeList) {
            System.out.println("2 " + employee);
        }

        Student student1 = new Student();
        student1.setName("zhangSan");
        employeeList.add(student1);
        for (Student employee : employeeList) {
            System.out.println("3 " + employee);
        }
        student1.setDept("FD");
        for (Student employee : employeeList) {
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

    @Test
    public void listAddAllTest() {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        System.out.println(a);

        List<String> b = new ArrayList<>();
        b.add("x");
        b.add("y");
        b.add("z");
        System.out.println(b);

        a.addAll(b);
        System.out.println(a);

        List<String> c = new ArrayList<>();
//        List<String> c = null;
        b.addAll(c);
        System.out.println(b);
    }

    @Test
    public void listContains() {
        List<String> a = new ArrayList<>();
        a.add("4231414135134");
        a.add("5867847554345");
        a.add("4352353554545");
        a.add("5336756768545");
        System.out.println(a.contains("4231414135134"));

        String b = "4231414135134";
        System.out.println(b.contains("4135134"));
    }

    /**
     * list删除元素
     */
    @Test
    public void listRemove() {
        List<String> a = new ArrayList<>();
        a.add("张三");
        a.add("李四");
        a.add("王五");
        a.add("刘备");
        a.add("张飞");
        a.add("关羽");

        // 1、倒删元素
        for (int i = a.size() - 1; i > 0; i--) {
            String str = "刘备";
            if (str.startsWith(a.get(i))) {
                a.remove(str);
            }
        }

        // 2、迭代器删除元素
//        a.removeIf(str -> str.contains("关"));
        for (Iterator<String> iterator = a.iterator(); iterator.hasNext(); ) {
            String str = iterator.next();
            if (str.contains("关")) {
                iterator.remove();
            }
        }

        // 3、stream过滤元素
        for (int i = a.size() - 1; i > 0; i--) {
            String str = "张飞";
            a = a.stream().filter(s -> !str.startsWith(s)).collect(Collectors.toList());
        }
        a.forEach(System.out::println);

        // 4、过滤元素
        List<String> b = new ArrayList<>();
        for (String s : a) {
            if (!s.contains("张三")) {
                b.add(s);
            }
        }
        System.out.println("--------b------");
        b.forEach(System.out::println);

    }

    /**
     * list删除元素
     */
    @Test
    public void listSubDistinct() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(1001L);
        list.add(1002L);
        list.add(1003L);
        list.add(1004L);
        list.add(1005L);
        list.add(1006L);
        list.add(1001L);
        list.add(1002L);
        list.add(1003L);

        System.out.println(list);

//        HashSet<Long> set = new HashSet<>();
//        set.addAll(list);
//        System.out.println(set);

        List<Long> list1 = list.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(list1);

        list.removeAll(list1);
        System.out.println(list);
    }

    /**
     * list元素分组求和
     */
    @Test
    public void listSubDistinct1() {
        LinkedList<Long> list = new LinkedList<>();
        list.add(1001L);
        list.add(1002L);
        list.add(1003L);
        list.add(1004L);
        list.add(1005L);
        list.add(1006L);
        list.add(1001L);
        list.add(1002L);
        list.add(1003L);
        System.out.println(list);

        Map<Long, Integer> map = list.stream().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        System.out.println(map);
    }

    @Test
    public void listSort() {
        List<String> list = new ArrayList<>();
        list.add("1001");
        list.add("1022");
        list.add("1013");
        list.add("1004");
        list.add("1055");
        list.add("1006");
        System.out.println(list);

        list.sort(String::compareTo);
        String joinKey = StringUtils.join(list, "_");
        System.out.println(joinKey);
    }

    @Test
    public void listNull() {
        List<String> logoList = new ArrayList<>();
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setName("zl");

        User user2 = new User();
        user2.setName(null);

        list.add(user1);
        list.add(user2);
//        list.add(null);
        System.out.println(list);

        for (User user : list) {
            System.out.println(user);
            if (StringUtils.isNotBlank(user.getName())) {
                logoList.add(user.getName());
            }
        }

        list.forEach(user -> {
            if (StringUtils.isBlank(user.getName())) {
                user.setName("zl1");
            }
        });
        System.out.println(list);

        System.out.println(logoList);
    }

    @Test
    public void testArrayListMultimap() {
        ArrayListMultimap<String, String> eventAttendeeMap = ArrayListMultimap.create();
        String eid = "123";
        String ownerAccount = "000";
        if (!eventAttendeeMap.get(eid).contains(ownerAccount)) {
            eventAttendeeMap.put(eid, ownerAccount);
        }
        System.out.println("map-" + eventAttendeeMap);

        eid = "123";
        ownerAccount = "111";
        if (!eventAttendeeMap.get(eid).contains(ownerAccount)) {
            eventAttendeeMap.put(eid, ownerAccount);
        }
        System.out.println("map-" + eventAttendeeMap);

        eid = "123";
        ownerAccount = "222";
        if (!eventAttendeeMap.get(eid).contains(ownerAccount)) {
            eventAttendeeMap.put(eid, ownerAccount);
        }
        System.out.println("map-" + eventAttendeeMap);

        eid = "123";
        ownerAccount = "111";
        if (!eventAttendeeMap.get(eid).contains(ownerAccount)) {
            eventAttendeeMap.put(eid, ownerAccount);
        }
        System.out.println("map-" + eventAttendeeMap);

        List<String> eidList = new ArrayList<>();
        int num = 0;
        for (String key : eventAttendeeMap.keys()) {
            System.out.println("---------" + key);
            System.out.println("---------" + eventAttendeeMap.get(key));

            if (eidList.contains(key)) {
                continue;
            } else {
                eidList.add(key);
            }

            List<String> values = eventAttendeeMap.get(key);
            if (values.size() > 1) {
                num++;
            }
        }
        System.out.println(num);
    }

    @Test
    public void testArrayListMultimap1() {
        int num = 0;
        List<String> eidList = new ArrayList<>();
        String eid = "123";
        eidList.add(eid);

        eid = "123";
        if (!eidList.contains(eid)) {
            eidList.add(eid);
        } else {
            num++;
        }
        System.out.println(num);
    }


    @Test
    public void randomTest() {
        List<String> firstTierCity = new ArrayList<>();
        firstTierCity.add("北京");
        firstTierCity.add("上海");
        firstTierCity.add("广州");
        firstTierCity.add("深圳");
        int random = (int) (Math.random() * firstTierCity.size());
        String additionalCity = firstTierCity.get(random);
        System.out.println(additionalCity);

    }

    @Test
    public void distinctListTest() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setName("zl");
        user1.setAge(18);
        user1.setBirthday(LocalDateTime.of(1996, 8, 12, 13, 15, 10));
        list.add(user1);

        User user2 = new User();
        user2.setName("za");
        user2.setAge(19);
        user1.setBirthday(LocalDateTime.of(1994, 8, 12, 13, 15, 10));
        list.add(user2);

        User user3 = new User();
        user3.setName("zl");
        user3.setAge(20);
        user3.setBirthday(LocalDateTime.of(1995, 8, 12, 13, 15, 10));
        list.add(user3);

        User user4 = new User();
        user4.setName("za");
        user4.setAge(21);
        user4.setBirthday(LocalDateTime.of(1991, 8, 12, 13, 15, 10));
        list.add(user4);

        User user5 = new User();
        user5.setName("wq");
        user5.setAge(25);
        user5.setBirthday(LocalDateTime.of(1990, 8, 12, 13, 15, 10));
        list.add(user5);

        // list 对象 属性去重
        List<User> distinctList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(User::getName))), ArrayList::new));

        List<User> sortCollect = distinctList.stream().sorted(Comparator.comparing(User::getAge, Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println(sortCollect);
    }

    @Test
    public void testSameEventNum() {
        int num = 0;
        Map<String, Set<String>> map = new HashMap<>(16);
        for (int i = 0; i < 10; i++) {
            this.addMapData(map, "key" + i, "value" + i);
        }

        this.addMapData(map, "key" + 1, "value" + 2);
        this.addMapData(map, "key" + 1, "value" + 2);
        this.addMapData(map, "key" + 2, "value" + 3);
        this.addMapData(map, "key" + 2, "value" + 3);

        for (Map.Entry<String, Set<String>> stringSetEntry : map.entrySet()) {
            if (null != stringSetEntry.getValue() && stringSetEntry.getValue().size() > 1) {
                num++;
            }
        }

        System.out.println(map);

        System.out.println(num);
    }

    private void addMapData(Map<String, Set<String>> map, String key, String value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            HashSet<String> set = new HashSet<>();
            set.add(value);
            map.put(key, set);
        }
    }

}