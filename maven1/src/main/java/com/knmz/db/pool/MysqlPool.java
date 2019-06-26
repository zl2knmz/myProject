package com.knmz.db.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author: zl
 * @Date: 2019/6/10 23:50
 * Description: 构建一个连接池
 */
public class MysqlPool implements IDatabaseConnection {

    /**
     * 最小连接数
     */
    private static final int MIN_COUNT = 1;

    /**
     * 最大连接数
     */
    private static final int MAX_COUNT = 10;

    /**
     * 连接池
     */
    private static final LinkedList<Connection> POOLS = new LinkedList<>();

    MysqlHandler handler = new MysqlHandler();

    @Override
    public void init() {
        Connection conn = null;
        try{
            for(int i=0; i<MIN_COUNT; i++) {
                conn = handler.buildConnection();
                POOLS.add(conn);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized Connection getConnection() {
        Connection conn = null;
        if(POOLS.size() == 0) {
            conn = handler.buildConnection();
        } else {
            conn = POOLS.remove(0);
        }
        return conn;
    }

    @Override
    public synchronized void close(Connection conn) {
        if(POOLS.size() < MAX_COUNT) {
            POOLS.add(conn);
        }
//        System.out.println(POOLS);
    }
}
