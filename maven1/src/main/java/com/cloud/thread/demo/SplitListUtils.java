package com.cloud.thread.demo;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 拆分结合工具类
 *
 * @author zl
 * @date 2022/11/8 10:13
 */
@Slf4j
public class SplitListUtils {

    /**
     * 拆分集合
     *
     * @param <T>           泛型对象
     * @param resList       需要拆分的集合
     * @param subListLength 每个子集合的元素个数
     * @return 返回拆分后的各个集合组成的列表
     * 代码里面用到了guava和common的结合工具类
     **/
    public static <T> List<List<T>> split(List<T> resList, int subListLength) {
        if (CollectionUtils.isEmpty(resList) || subListLength <= 0) {
            return Lists.newArrayList();
        }
        List<List<T>> ret = Lists.newArrayList();
        int size = resList.size();
        if (size <= subListLength) {
            // 数据量不足 subListLength 指定的大小
            ret.add(resList);
        } else {
            int pre = size / subListLength;
            int last = size % subListLength;
            // 前面pre个集合，每个大小都是 subListLength 个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = Lists.newArrayList();
                for (int j = 0; j < subListLength; j++) {
                    itemList.add(resList.get(i * subListLength + j));
                }
                ret.add(itemList);
            }
            // last的进行处理
            if (last > 0) {
                List<T> itemList = Lists.newArrayList();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * subListLength + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }

    // 运行代码
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        int size = 1099;
        for (int i = 0; i < size; i++) {
            list.add("hello-" + i);
        }
        // 大集合里面包含多个小集合
        List<List<String>> temps = split(list, 100);
        int j = 0;
        // 对大集合里面的每一个小集合进行操作
        for (List<String> obj : temps) {
            System.out.println(String.format("row:%s -> size:%s,data:%s", ++j, obj.size(), obj));
        }
    }

    /**
     * 开启异步执行任务的线程池
     * <p>
     * 开多线程来执行批量任务是十分重要的一种批量操作思路，
     * 其实这种思路实现起来也十分简单，就拿批量更新的操作举例
     */
    public void threadMethod(List<Object> totalList) {
        List<Object> updateList = new ArrayList();
        // 初始化线程池, 参数一定要一定要一定要调好！！！！
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                20,
                50,
                4,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(10),
                new ThreadPoolExecutor.AbortPolicy());
        // 大集合拆分成N个小集合, 这里集合的size可以稍微小一些（这里我用100刚刚好）, 以保证多线程异步执行, 过大容易回到单线程
        List<List<Object>> splitNList = SplitListUtils.split(totalList, 100);
        // 记录单个任务的执行次数
        CountDownLatch countDownLatch = new CountDownLatch(splitNList.size());
        // 对拆分的集合进行批量处理, 先拆分的集合, 再多线程执行
        for (List<Object> singleList : splitNList) {
            // 线程池执行
            threadPool.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (Object yangshiwen : singleList) {
                        // 将每一个对象进行数据封装, 并添加到一个用于存储更新数据的list
                        // ......

                    }
                }
            }));
            // 任务个数 - 1, 直至为0时唤醒await()
            countDownLatch.countDown();
        }
        try {
            // 让当前线程处于阻塞状态，直到锁存器计数为零
            countDownLatch.await();
        } catch (InterruptedException e) {
//            throw new BusinessLogException(ResponseEnum.FAIL);
            log.info("批量更新集合数据异常.........");
        }
        // 通过mybatis的批量插入的方式来进行数据的插入, 这一步还是要做判空
        if (null != updateList && updateList.size() > 0) {
            log.info("批量更新集合数据........." + updateList);
        }
    }

}
