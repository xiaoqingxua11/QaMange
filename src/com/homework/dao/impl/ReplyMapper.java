package com.homework.dao.impl;

import com.homework.po.Reply;
import com.homework.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:    ReplyMapper
 * Package:    com.homework.dao.impl
 * Description:
 * Datetime:    2020/9/27   10:47
 * Author:   ${小情绪}
 */
public class ReplyMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        String content=rs.getString("content");
        int goodCount=rs.getInt("goodcount");
        Reply reply=new Reply();
        reply.setContent(content);
        reply.setGoodcount(goodCount);
        return reply;
    }
}
