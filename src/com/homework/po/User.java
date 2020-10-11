package com.homework.po;

/**
 * ClassName:    User
 * Package:    com.homework.po
 * Description:
 * Datetime:    2020/9/24   16:16
 * Author:   ${小情绪}
 */
public class User {
    private  int id;
    private  String email;
    private  String password;
    private  String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public User() {
    }
}

