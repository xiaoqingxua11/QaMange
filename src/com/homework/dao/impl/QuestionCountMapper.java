package com.homework.dao.impl;

import com.homework.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:    QuestionCountMapper
 * Package:    com.homework.dao.impl
 * Description:
 * Datetime:    2020/9/25   10:07
 * Author:   ${小情绪}
 */
public class QuestionCountMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        int count=rs.getInt(1);
        return  count;
    }
}
