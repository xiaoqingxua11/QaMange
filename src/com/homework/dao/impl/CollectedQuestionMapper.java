package com.homework.dao.impl;

import com.homework.po.Question;
import com.homework.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:    CollectedQuestionMapper
 * Package:    com.homework.dao.impl
 * Description:
 * Datetime:    2020/9/27   14:27
 * Author:   ${小情绪}
 */
public class CollectedQuestionMapper  implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        int id=rs.getInt("question_id");
        Question question=new Question();
        question.setId(id);
        return question;
    }
}
