package com.homework.po;

/**
 * ClassName:    Reply
 * Package:    com.homework.po
 * Description:
 * Datetime:    2020/9/27   10:49
 * Author:   ${小情绪}
 */
public class Reply {
    private String content;
    private int goodcount;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGoodcount() {
        return goodcount;
    }

    public void setGoodcount(int goodcount) {
        this.goodcount = goodcount;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "content='" + content + '\'' +
                ", goodcount=" + goodcount +
                '}';
    }

    public Reply() {
    }
}
