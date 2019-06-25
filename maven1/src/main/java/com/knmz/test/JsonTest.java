package com.knmz.test;

import com.knmz.db.pool.DataProcess;

import java.util.List;
import java.util.Map;

/**
 * @Author: zl
 * @Date: 2019/6/25 18:41
 */
public class JsonTest {
    public static void main(String args[]) {
        test();
    }

    public static void test() {
        String sql = "select * from job_data WHERE LEVEL = 1";
        List<Map<String, Object>> list = DataProcess.executeQuery(sql, null);
    }
}
