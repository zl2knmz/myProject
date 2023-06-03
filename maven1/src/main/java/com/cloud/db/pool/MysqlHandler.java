package com.cloud.db.pool;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author zl
 * @date 2019/6/10 23:48
 */
public class MysqlHandler {
    /**
     * mysql驱动
     */
    private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * 数据库连接
     * jdbc:mysql://localhost:3306/maven?useUnicode=true&autoReconnect=true
     */
    private static final String DBURL = "jdbc:mysql://192.168.16.231:3306/big_data?useUnicode=true&autoReconnect=true";

    /**
     * mysql用户名
     * root
     */
    private static final String USERNAME = "root";

    /**
     * mysql密码
     * root
     */
    private static final String PASSWORD = "root";
    private Connection conn = null;

    public Connection buildConnection() {
        try {
            Class.forName(DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
