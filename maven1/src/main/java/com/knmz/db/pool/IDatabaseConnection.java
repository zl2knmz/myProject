package com.knmz.db.pool;

import java.sql.Connection;

/**
 * @Author: zl
 * @Date: 2019/6/10 23:46
 * Description: 定义一个接口，更换数据库的时候方便
 */
public interface IDatabaseConnection {
    //初始化
    public void init();
    //取得连接
    public  Connection getConnection();
    //关闭
    public void close(Connection conn);
}
