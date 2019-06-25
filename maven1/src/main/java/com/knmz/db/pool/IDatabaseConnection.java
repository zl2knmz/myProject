package com.knmz.db.pool;

import java.sql.Connection;

/**
 * @Author: zl
 * @Date: 2019/6/10 23:46
 * Description: 定义一个接口，更换数据库的时候方便
 */
public interface IDatabaseConnection {
    /**
     * 初始化
     */
    void init();

    /**
     * 取得连接
     */
    Connection getConnection();

    /**
     * 关闭连接
     * @param conn
     */
    void close(Connection conn);
}
