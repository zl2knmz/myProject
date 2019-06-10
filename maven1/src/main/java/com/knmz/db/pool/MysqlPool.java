package com.knmz.db.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author: zl
 * @Date: 2019/6/10 23:50
 * Description: 构建一个连接池
 */
public class MysqlPool implements IDatabaseConnection {

    //最小连接数
    private static final int minCount = 1;
    //最大连接数
    private static final int maxCount = 10;
    //连接池
    private static final LinkedList<Connection> pools = new LinkedList<Connection>();
    MysqlHandler handler = new MysqlHandler();

    @Override
    public void init() {
        Connection conn = null;
        try{
            for(int i=0; i<minCount; i++) {
                conn = handler.buildConnection();
                pools.add(conn);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized Connection getConnection() {
        Connection conn = null;
        if(pools.size() == 0) {
            conn = handler.buildConnection();
        } else {
            conn = pools.remove(0);
        }
        return conn;
    }

    @Override
    public synchronized void close(Connection conn) {
        if(pools.size() < maxCount) {
            pools.add(conn);
        }
        System.out.println(pools);
    }
}
