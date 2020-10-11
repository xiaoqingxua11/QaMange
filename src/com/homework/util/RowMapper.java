package com.homework.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:    RowMappar
 * Package:    com.xiaoqingxu.util
 * Description: 接口 转化对象的能力
 * Datetime:    2020/9/21   16:49
 * Author:   ${小情绪}
 */
public interface RowMapper {

    /**
     * 能够将结果集中的每一行转换成对象
     * @param rs 查询出来的结果集
     * @return 转换的对象
     * @throws SQLException
     */
    public Object rowMapper(ResultSet rs) throws SQLException;
}
