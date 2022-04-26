package com.cloud.test;

import com.cloud.util.phoneix.DButil;
import org.junit.Test;

/**
 * @author zl
 * @date 2020/3/20 11:42
 */
public class PhoneixTest {
    /**
     * 将提取数据名单写入hbase
     */
    @Test
    public void insertTag() {
        int count = 0;
        String sql = "upsert into tag (id,type,name) values (?,?,?)";
//        String sql = "upsert into tag (id,TYPE,NAME) values (201,2,'交友')";
        Object[] params = new Object[]{201, 2,"交友"};
        count = new DButil().executeUpdate(sql, params);
        System.out.println(count);
    }
}
