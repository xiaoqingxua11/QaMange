package com.homework.po;

import java.util.Date;

/**
 * 问题实体类
 * ClassName:    Question
 * Package:    com.homework.po
 * Description:
 * Datetime:    2020/9/24   19:48
 * Author:   ${小情绪}
 */
public class Question {
    private int id;
    private  String title;
    private  String content;
    private  int  user_id;
    private  int  collectcount;
    private Date  publishTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCollectcount() {
        return collectcount;
    }

    public void setCollectcount(int collectcount) {
        this.collectcount = collectcount;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user_id=" + user_id +
                ", collectcount=" + collectcount +
                ", publishTime=" + publishTime +
                '}';
    }

    public Question() {
    }
}
