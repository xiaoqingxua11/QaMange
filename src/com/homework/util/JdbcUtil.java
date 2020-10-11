package com.homework.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName:    JdbcUtil
 * Package:    com.xiaoqingxu.util
 * Description:
 * Datetime:    2020/9/22   18:53
 * Author:   ${小情绪}
 */
public class JdbcUtil {
    //创建数据源
    private static DataSource ds=null;

    //线程变量
    private  static  ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();

    static {
//加载datasource.properties文件并将其转换成Properties对象
        try {
            Properties p = new Properties();
            p.load(com.homework.util.JdbcUtil.class.getClassLoader().getResourceAsStream("datasource.properties"));
            ds= BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据源加载失败");
        }

    }
    //获得连接
    public  static  Connection getConn(){
        Connection conn=threadLocal.get();
        try {
            if(null==conn){
                conn=ds.getConnection();
                threadLocal.set(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    /**
     * 释放与当前SQL相关的状态集和结果集对象
     * @param pstmt 状态集
     * @param rs 结果集
     * @see [类、类#方法、类#成员]
     */
    public static void close(PreparedStatement pstmt, ResultSet rs){
        try
        {
            if(null != rs){
                rs.close();
            }
            if(null != pstmt){
                pstmt.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     * @see [类、类#方法、类#成员]
     */
    public static void close(){
        Connection conn = threadLocal.get();
        try
        {
            if(null != conn){
                conn.close();
                threadLocal.remove();//将连接对象与当前线程解除绑定
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
