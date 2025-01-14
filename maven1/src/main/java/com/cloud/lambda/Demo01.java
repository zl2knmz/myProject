package com.cloud.lambda;

import com.alibaba.fastjson.JSONObject;
import com.cloud.model.MyObject;
import com.cloud.util.Base;
import com.cloud.util.IdGenerator;
import com.cloud.util.SnowflakeIdUtil;
import org.junit.Test;

import java.util.*;
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
        testRunnable2();
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

    /**
     * 当匿名内部类只有一个实现方法时可以直接简化
     */
    private static void testRunnable2() {
        new Thread(() -> System.out.println("新的线程被执行了......")).start();
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

    @Test
    public void testUtilDate() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(date.toString());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testStringEquals() {
        boolean a = "已提取".equals(null);
        boolean b = "已提取".equals("");
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void testStringReplace() {
        String a = "hdx_ticket_agent_template_personal";
        String b = "hdx_ticket_agent_template_personal_sz";
        String szCompany = "_sz";
        a = a.replace(szCompany, "");
        b = b.replace(szCompany, "");
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void testJson() {
        JSONObject jsonObject = JSONObject.parseObject("");
        System.out.println(jsonObject);
        // NullPointerException
//        System.out.println(jsonObject.containsKey("body"));
//        JSONObject body = jsonObject.getJSONObject("body");
//        System.out.println(jsonObject);
//        System.out.println(body);

        JSONObject json = new JSONObject();
        System.out.println(json.toJSONString());

    }

    @Test
    public void testId() {
        List<Long> idList = new ArrayList<>();
        Long id = SnowflakeIdUtil.getGeneratedLongKey();
        String newId = IdGenerator.Generate(2, Base.SECONDS, 2, System.currentTimeMillis());
        Long id13 = Long.parseLong(newId);
        Long id17 = IdGenerator.getKey(null);
        idList.add(id);
        idList.add(id13);
        idList.add(id17);
        System.out.println(idList);
    }

    @Test
    public void testAddId() {
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            String newId = IdGenerator.Generate(2, Base.SECONDS, 2, System.currentTimeMillis());
            Long generatedLongKey = Long.parseLong(newId);
            idList.add(generatedLongKey);

            try {
                // 暂停1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("generatedLongKey error.");
            }
        }
        System.out.println(idList);
    }

    @Test
    public void listIdSortTest() {
        List<MyObject> objects = new ArrayList<>();
        objects.add(new MyObject(3L, "C"));
        objects.add(new MyObject(1L, "A"));
        objects.add(new MyObject(2L, "B"));
        objects.add(new MyObject(4L, "D"));  // 这个 id 不在 idList 中
        objects.add(new MyObject(5L, "E"));  // 这个 id 不在 idList 中

        List<Long> idList = Arrays.asList(2L, 3L, 1L, 6L);

        System.out.println("Before sorting: " + objects);
        sortByIdList(objects, idList);
        System.out.println("After sorting: " + objects);
    }

    public void sortByIdList(List<MyObject> objects, List<Long> idList) {
        // 使用自定义的 Comparator
        objects.sort(Comparator.comparingLong(o -> {
            int index = idList.indexOf(o.getId());
            return index == -1 ? Integer.MAX_VALUE : index;
        }));
    }

}
