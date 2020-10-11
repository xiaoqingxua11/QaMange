package com.homework.dao.impl;

import com.homework.dao.Dao;
import com.homework.po.User;
import com.homework.util.JdbcTemplate;
import com.homework.util.JdbcUtil;

import java.util.List;

/**
 * ClassName:    DaoImpl
 * Package:    com.homework.dao.impl
 * Description:
 * Datetime:    2020/9/24   15:48
 * Author:   ${小情绪}
 */
public class DaoImpl implements Dao {
    private JdbcTemplate jt=new JdbcTemplate();
    @Override
    public List<User> findStudentByEmail(String email) {
        String sql=new StringBuffer()
                .append(" select id,email,password,nickname from t_user ")
                .append(" where email=? ")
                .toString();
        return jt.query(sql,new UserMapper(),email);
    }

    @Override
    public boolean save(User user) {
        String sql=new StringBuffer()
                .append(" insert into t_user (email,password,nickname) values(?,?,?) ")
                .toString();
        return jt.update(sql,user.getEmail(),user.getPassword(),user.getNickname());
    }
}
