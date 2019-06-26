package com.knmz.db.pool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zl
 * @Date: 2019/6/25 18:12
 * 功能介绍：使用连接池对mysql数据库操作：查询、更新（插入/修改/删除）、批量更新
 */
public class DataProcess {

    private static IDatabaseConnection mysqlServer = new MysqlPool();

    /**jdbc的链接*/
    private static Connection conn = null;

    /**准备sql*/
    private static PreparedStatement ps = null;

    static {
        //初始化连接池
        mysqlServer.init();
    }

    /**
     * @param sql
     * @param params 参数
     * 功能介绍：更新操作（修改，删除，插入）
     */
    public static int executeUpdate(String sql, Object[] params) {
        if(null == conn){
            initConnection();
        }
        try {
            ps = conn.prepareStatement(sql);
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            int rows = ps.executeUpdate();
            conn.commit();
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeUpdate();
        }
        return 0;
    }

    /**
     * @param sql
     * @param list
     * 功能介绍：批量更新
     */
    public static void batchUpdate(String sql, List<Object[]> list) {
        if(null == conn){
            initConnection();
        }
        try {
            ps = conn.prepareStatement(sql);
            //关闭自动提交事务
            conn.setAutoCommit(false);
            //防止内存溢出
            final int batchSize = 1000;
            //记录插入数量
            int count = 0;
            int size = list.size();
            Object[] obj = null;
            for (int i = 0; i < size; i++) {
                obj = list.get(i);
                for (int j = 0; j < obj.length; j++) {
                    ps.setObject(j + 1, obj[j]);
                }
                ps.addBatch();
                if (++count % batchSize == 0) {
                    ps.executeBatch();
                    conn.commit();
                }
            }
            ps.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            //关闭资源
            closeUpdate();
        }
    }

    /**
     * @param sql
     * @param params
     * 功能介绍：查询操作
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object[] params) {
        if(null == conn){
            initConnection();
        }
        ResultSet rs = null;
        List<Map<String, Object>> list = null;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }

            rs = ps.executeQuery();
            list = new ArrayList<>();
            //移动光标，如果新的当前行有效，则返回 true；如果不存在下一行，则返回 false
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                Map<String, Object> map = new HashMap<>(16);
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    map.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                list.add(map);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuery(rs);
        }
        return null;
    }

    /**
     * @param sql
     * @param params
     * 功能介绍：查询操作一条记录
     */
    public static Map<String, Object> query (String sql, Object[] params) {
        if(null == conn){
            initConnection();
        }
        ResultSet rs = null;
        Map<String, Object> map = null;
        try {
            ps = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
            //移动光标，如果新的当前行有效，则返回 true；如果不存在下一行，则返回 false
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                map = new HashMap<>(16);
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    map.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                //若有多条记录，取第一条。
                break;
            }
            return map;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuery(rs);
        }
        return null;
    }

    /**
     * 初始化连接
     */
    private static void initConnection() {
        try {
            //从连接池获取连接
            conn = mysqlServer.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能介绍：关闭更新资源
     */
    private static void closeUpdate() {
        try {
            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                mysqlServer.close(conn);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param rs 功能介绍：关闭查询资源
     */
    private static void closeQuery(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                mysqlServer.close(conn);
//                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
