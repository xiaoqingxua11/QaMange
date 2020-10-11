package com.homework.service;

import com.homework.po.User;

import java.util.List;

/**
 * ClassName:    UserService
 * Package:    com.homework.service
 * Description:
 * Datetime:    2020/9/24   20:10
 * Author:   ${小情绪}
 */
public interface UserService {
    //查找学生
    public List<User> findStudentByEmail(String email);
    //注册学生
    public  boolean save(User user);

}
