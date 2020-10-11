package com.homework.dao.impl;

import com.homework.po.Question;
import com.homework.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * ClassName:    QuestionMapper
 * Package:    com.homework.dao.impl
 * Description:
 * Datetime:    2020/9/24   19:52
 * Author:   ${小情绪}
 */
public class QuestionMapper implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        int id=rs.getInt("id");
        String title=rs.getString("title");
        String content=rs.getString("content");
        int user_id=rs.getInt("user_id");
        int collectCount=rs.getInt("collectcount");
        Date publishTime=rs.getDate("publishtime");
        Question question= new Question();
        question.setId(id);
        question.setTitle(title);
        question.setContent(content);
        question.setUser_id(user_id);
        question.setCollectcount(collectCount);
        question.setPublishTime(publishTime);
        return question;
    }
}
