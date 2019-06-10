package com.knmz.db.pool;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: zl
 * @Date: 2019/6/10 23:48
 */
public class MysqlHandler {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/maven?useUnicode=true&autoReconnect=true";
    private static final String USERNAME = "root";
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
