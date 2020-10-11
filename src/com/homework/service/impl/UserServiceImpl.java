package com.homework.service.impl;

import com.homework.dao.Dao;
import com.homework.dao.impl.DaoImpl;
import com.homework.po.User;
import com.homework.service.UserService;

import java.util.List;

/**
 * ClassName:    UserServiceImpl
 * Package:    com.homework.service.impl
 * Description:
 * Datetime:    2020/9/24   20:11
 * Author:   ${小情绪}
 */
public class UserServiceImpl  implements UserService {
    private Dao dao=new DaoImpl();
    @Override
    public List<User> findStudentByEmail(String email) {
        return dao.findStudentByEmail(email);
    }

    @Override
    public boolean save(User user) {
        return dao.save(user);
    }


}
