package com.homework.service;

import com.homework.po.Question;
import com.homework.po.Reply;

import java.util.List;

/**
 * ClassName:    QusetionService
 * Package:    com.homework.service
 * Description:
 * Datetime:    2020/9/24   20:17
 * Author:   ${小情绪}
 */
public interface QusetionService {
    //查所有问题
    public List<Question> getAllQusetion( int pegeNum,String key);
    //获取最大页码
    public  int getMaxPage(String key);
    //获取最大页码
    public  int getMaxPageById(String key,String user_id);
    //查id发布的所有问题
    public List<Question> getMyQusetionByUser_id( int pageNum,String key,int user_id);
    //查id收藏的所有问题
    public List<Question> getCollectedQusetionByUser_id( int pageNum,String key,int user_id);
    //获取所有问题没有分页
    public List<Question> getAllQusetions();
    //根据问题id获取问题
    public List<Question> getQusetionByQuestionId(int questionId);
    //根据问题id删除问题
   public Boolean romoveQusetionById(int questionId);
    //根据问题id删除问题
    public List<Question> getQusetionById(int questionId);
    //根据问题id获得回复内容
    public List<Reply>getReplyByQuestionId(int uestionId);
    //发布问题
    public Boolean publishQuestion(String title,String content,int user_id);
    //回复问题
    public Boolean updateReply(String anwserMessage,int qusetionId,int user_id);
    //查看是否收藏
    public List<Question>  isCollected(int question_id,int user_id);
    //收藏
    public Boolean collect(int question_id,int user_id);
    //点赞数量-1
    public Boolean minusGood(String contentText);
    //点赞数量+1
    public Boolean plusGood(String contentText);
    //获取所有的回复
    public List<Reply> getAllReply();
    //删除从表数据
    public Boolean removeFromCollect(int questionId);
    public Boolean removeFromReply(int questionId);
    //修改
    public Boolean update(String title,String content,int questionId);
    //取消收藏
    public Boolean disCollect(int question_id,int user_id);
}
