package com.homework.dao;

import com.homework.po.User;

import java.util.List;

/**
 * ClassName:    Dao
 * Package:    com.homework.dao
 * Description:
 * Datetime:    2020/9/24   15:47
 * Author:   ${小情绪}
 */
public interface Dao {
    //查找学生
    public List<User> findStudentByEmail(String email);
    //注册学生
    public  boolean save(User user);
}
