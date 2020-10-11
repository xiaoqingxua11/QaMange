package com.homework.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:    JdbcTemplate
 * Package:    com.xiaoqingxu.util
 * Description:
 * Datetime:    2020/9/22   20:04
 * Author:   ${小情绪}
 */
public class JdbcTemplate {
    /**
     * 增删改操作
     * @param sql SQL语句
     * @param params SQL语句中占位符对应的值
     * @return 操作是否成功
     * @see [类、类#方法、类#成员]
     */
    public boolean update(String sql,Object... params){
        Connection conn = JdbcUtil.getConn();
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            return pstmt.executeUpdate() > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcUtil.close(pstmt, null);
        }
        return false;
    }

    /**
     * 查询操作
     * @param sql   SQL语句
     * @param rm    定义如何将结果集指向的每一条数据转换成对象
     * @param params    SQL语句占位符对应的值
     * @return  查询的对象集合
     * @see [类、类#方法、类#成员]
     */
    public List query(String sql, RowMapper rm, Object... params){
        List list = new ArrayList();
        Connection conn = JdbcUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Object obj = rm.rowMapper(rs);
                list.add(obj);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            JdbcUtil.close(pstmt, rs);
        }
        return list;
    }

    /**
     * 为占位符赋值
     * @param pstmt 当前的状态集对象
     * @param params 状态集中管理的SQL中的占位符对应的值
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    private void setParams(PreparedStatement pstmt,Object... params) throws SQLException{
        if(null != params && params.length > 0){
            for(int i = 0; i < params.length; i++){
                pstmt.setString(i + 1, params[i].toString());
            }
        }
    }
}
