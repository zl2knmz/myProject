package com.cloud.test;

import com.cloud.db.pool.IDatabaseConnection;
import com.cloud.db.pool.MysqlPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author zl
 * @date 2019/6/10 23:52
 */
public class DatabaseTest {
    private static IDatabaseConnection mysqlServer = new MysqlPool();

    public static void main(String[] args) {
        mysqlServer.init();
        long start = System.currentTimeMillis();
        for(int i=0; i<300; i++) {
            test();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    private static void test() {
        try{
            Connection conn = mysqlServer.getConnection();
            String sql = "select * from user_t";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            pst.close();
            mysqlServer.close(conn);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
