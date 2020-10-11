package com.homework.service.impl;

import com.homework.constraint.Page;
import com.homework.dao.Dao;
import com.homework.dao.QusetionDao;
import com.homework.dao.impl.DaoImpl;
import com.homework.dao.impl.QusetionDaoImpl;
import com.homework.po.Question;
import com.homework.po.Reply;
import com.homework.service.QusetionService;

import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * ClassName:    QuestionServiceImpl
 * Package:    com.homework.service.impl
 * Description:
 * Datetime:    2020/9/24   20:19
 * Author:   ${小情绪}
 */
public class QuestionServiceImpl implements QusetionService {
    private QusetionDao qusetionDao=new QusetionDaoImpl();
    @Override
    public List<Question> getAllQusetion(int pageNum,String key) {
        return qusetionDao.getAllQuestion(pageNum,key);
    }

    @Override
    public int getMaxPage(String key) {
        int maxPage = 1;//记录最大的页码数
        int count = qusetionDao.getCount(key);//表中一共有的学生记录数
        if(count % Page.PAGE_SIZE == 0)
        {
            maxPage = count / Page.PAGE_SIZE;
        }
        else
        {
            maxPage = count / Page.PAGE_SIZE + 1;
        }
        return maxPage;
    }

    @Override
    public int getMaxPageById(String key, String user_id) {
        int maxPage = 1;//记录最大的页码数
        int count = qusetionDao.getCountById(key,user_id);//表中一共有的学生记录数
        if(count % Page.PAGE_SIZE == 0)
        {
            maxPage = count / Page.PAGE_SIZE;
        }
        else
        {
            maxPage = count / Page.PAGE_SIZE + 1;
        }
        return maxPage;
    }

    //查id发布的所有问题
    @Override
    public List<Question> getMyQusetionByUser_id(int pageNum, String key,int user_id) {
        return qusetionDao.getMyQusetionByUser_id(pageNum,key,user_id);
    }
    //查id收藏的所有问题
    @Override
    public List<Question> getCollectedQusetionByUser_id(int pageNum, String key, int user_id) {
        return qusetionDao.getCollectedQusetionByUser_id(pageNum,key,user_id);
    }


    //获取所有问题无分页
    @Override
    public List<Question> getAllQusetions() {
        return qusetionDao.getAllQusetions();
    }
    //根据问题id获得问题
    @Override
    public List<Question> getQusetionByQuestionId(int questionId) {
        return qusetionDao.getQusetionByQuestionId(questionId);
    }
    //根据问题id删除问题
    @Override
    public Boolean romoveQusetionById(int questionId) {
        return qusetionDao.romoveQusetionById(questionId);
    }
    //根据问题id删除问题

    @Override
    public List<Question> getQusetionById(int questionId) {
        return qusetionDao.getQusetionById(questionId);
    }

    @Override
    public List<Reply> getReplyByQuestionId(int questionId) {
        return qusetionDao.getReplyByQuestionId(questionId);
    }

    @Override
    public Boolean publishQuestion(String title, String content, int user_id) {
        return qusetionDao.publishQuestion(title,content,user_id);
    }

    @Override
    public Boolean updateReply(String anwserMessage, int qusetionId, int user_id) {
        return qusetionDao.updateReply(anwserMessage,qusetionId,user_id);
    }

    @Override
    public List<Question>  isCollected(int question_id,int user_id) {
        return qusetionDao.isCollected(question_id,user_id);
    }

    @Override
    public Boolean collect(int question_id,int user_id) {
        return qusetionDao.collect(question_id,user_id);
    }

    @Override
    public Boolean minusGood(String contentText) {
        return qusetionDao.minusGood(contentText);
    }

    @Override
    public Boolean plusGood(String contentText) {
        return qusetionDao.plusGood(contentText);
    }

    @Override
    public List<Reply> getAllReply() {
        return qusetionDao.getAllReply();
    }

    @Override
    public Boolean removeFromCollect(int questionId) {
        return qusetionDao.removeFromCollect(questionId);
    }

    @Override
    public Boolean removeFromReply(int questionId) {
        return qusetionDao.removeFromReply(questionId);
    }

    @Override
    public Boolean update(String title,String content,int questionId) {
        return qusetionDao.update(title,content,questionId);
    }

    @Override
    public Boolean disCollect(int question_id, int user_id) {
        return qusetionDao.disCollect
                (question_id,user_id);
    }


}
