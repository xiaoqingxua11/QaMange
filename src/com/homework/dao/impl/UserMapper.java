package com.homework.dao.impl;

import com.homework.po.User;
import com.homework.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:    UserMapper
 * Package:    com.homework.dao.impl
 * Description:
 * Datetime:    2020/9/24   16:15
 * Author:   ${小情绪}
 */
public class UserMapper  implements RowMapper {
    @Override
    public Object rowMapper(ResultSet rs) throws SQLException {
        int id=rs.getInt("id");
        String email=rs.getString("email");
        String password=rs.getString("password");
        String nickname=rs.getString("nickname");

        User user=new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setNickname(nickname);
        return user;
    }
}
