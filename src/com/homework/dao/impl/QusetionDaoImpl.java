package com.homework.dao.impl;

import com.homework.constraint.Page;
import com.homework.dao.QusetionDao;
import com.homework.po.Question;
import com.homework.po.Reply;
import com.homework.util.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClassName:    QusetionDaoImpl
 * Package:    com.homework.dao.impl
 * Description:
 * Datetime:    2020/9/24   20:28
 * Author:   ${小情绪}
 */
public class QusetionDaoImpl implements QusetionDao {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private JdbcTemplate jt = new JdbcTemplate();

    //查询所以问题————》分页器
    @Override
    public List<Question> getAllQuestion(int pageNum, String key) {
        StringBuffer sql = new StringBuffer()
                .append(" select id,title,content,user_id,collectcount,publishtime from t_question where 1=1 ");

        if (null != key && !"".equals(key.trim())) {
            sql.append(" and title like '%" + key.trim() + "%' ");
        }
        sql.append(" order by publishtime desc ")
                .append(" limit " + (pageNum - 1) * Page.PAGE_SIZE + "," + Page.PAGE_SIZE);

        return jt.query(sql.toString(), new QuestionMapper());
    }


    //查询数据数量
    @Override
    public int getCount(String key) {
        StringBuffer sql = new StringBuffer()
                .append(" select count(*) from t_question where 1=1 ");

        if(null != key && !"".equals(key.trim()))
        {
            sql.append(" and title like '%"+key+"%' ");
        }
        return (Integer)jt.query(sql.toString(), new QuestionCountMapper()).get(0);
    }

        @Override
    public int getCountById(String key, String user_id) {
        StringBuffer sql = new StringBuffer()
                .append(" select count(*) from t_question where 1=1 ");
        if(null != user_id && !"".equals(user_id.trim()))
        {
            sql.append(" and user_id=? ");
        }

        if(null != key && !"".equals(key.trim()))
        {
            sql.append(" and title like '%"+key+"%' ");
        }
        return (Integer)jt.query(sql.toString(), new QuestionCountMapper(),user_id).get(0);

    }

    //查id发布的所有问题
    @Override
    public List<Question> getMyQusetionByUser_id(int pageNum, String key,int user_id) {
        StringBuffer sql = new StringBuffer()
                .append("select * ")
                .append(" from t_question inner join t_user on t_question.user_id=t_user.id AND t_user.id =? ");


        if (null != key && !"".equals(key.trim())) {
            sql.append(" and title like '%" + key.trim() + "%' ");
        }
        sql.append(" order by publishtime desc ");
        System.out.println(sql.toString());
        return jt.query(sql.toString(), new QuestionMapper(),user_id);
    }
//    //查id收藏的所有问题
    @Override
    public List<Question> getCollectedQusetionByUser_id(int pageNum, String key, int user_id) {
        StringBuffer sql = new StringBuffer()
                .append("select * ")
                .append(" from t_question inner join t_collect on t_question.id=t_collect.question_id AND t_collect.user_id =? ");
        if (null != key && !"".equals(key.trim())) {
            sql.append(" and title like '%" + key.trim() + "%' ");
        }
        sql.append(" order by publishtime desc ")
                .append(" limit " + (pageNum - 1) * Page.PAGE_SIZE + "," + Page.PAGE_SIZE);
        return jt.query(sql.toString(),new QuestionMapper(),user_id);
    }

    //获取所有问题无分页
    @Override
    public List<Question> getAllQusetions() {
        StringBuffer sql = new StringBuffer()
                .append(" select id,title,content,user_id,collectcount,publishtime ")
                .append(" from t_question  ");
        return jt.query(sql.toString(), new QuestionMapper());
    }

    @Override
    public List<Question> getQusetionByQuestionId(int questionId) {
        StringBuffer sql = new StringBuffer()
                .append(" select id,title,content,user_id,collectcount,publishtime ")
                .append(" from t_question  ")
                .append(" where id=? ");
        return jt.query(sql.toString(),new QuestionMapper(),questionId);
    }
    //删除
    @Override
    public Boolean romoveQusetionById(int questionId) {
        StringBuffer sql = new StringBuffer()
                .append(" delete from t_question ")
                .append(" where id=? ");
        return jt.update(sql.toString(),questionId);
    }
    //修改
    @Override
    public List<Question>  getQusetionById(int questionId) {
        StringBuffer sql = new StringBuffer()
                .append(" select id,title,content,user_id,collectcount,publishtime from t_question ")
                .append(" where id=? ");
        return jt.query(sql.toString(),new QuestionMapper(),questionId);
    }

    @Override
    public List<Reply> getReplyByQuestionId(int questionId) {
        StringBuffer sql = new StringBuffer()
                .append(" select id,content,goodcount from t_reply ")
                .append(" where question_id=? ");
        return jt.query(sql.toString(),new ReplyMapper(),questionId);
    }

    @Override
    public Boolean publishQuestion(String title, String content, int user_id) {
        StringBuffer sql = new StringBuffer()
                .append(" insert into t_question (title,content,user_id,collectcount,publishtime) ")
                .append(" values(?,?,?,0,?) ");
        return jt.update(sql.toString(),title,content,user_id,sdf.format(new Date()));
    }

    @Override
    public Boolean updateReply(String anwserMessage, int qusetionId, int user_id) {
        StringBuffer sql = new StringBuffer()
                .append(" insert into t_reply (content,question_id,user_id,goodcount) ")
                .append(" values(?,?,?,0) ");
        return jt.update(sql.toString(),anwserMessage,qusetionId,user_id);
    }

    @Override
    public List<Question> isCollected(int question_id,int user_id) {
        StringBuffer sql = new StringBuffer()
                .append(" select question_id from t_collect ")
                .append(" WHERE question_id=? AND user_id=?");
        return jt.query(sql.toString(),new QuestionCountMapper(),question_id,user_id);
    }

    @Override
    public Boolean collect(int question_id,int user_id) {
        StringBuffer sql = new StringBuffer()
                .append(" insert into t_collect (question_id,user_id) ")
                .append("  values(?,?) ");
        return jt.update(sql.toString(),question_id,user_id) ;
    }

    @Override
    public Boolean disCollect(int question_id, int user_id) {
        StringBuffer sql = new StringBuffer()
                .append(" delete from t_collect  ")
                .append("  where question_id=? and user_id=? ");
        return jt.update(sql.toString(),question_id,user_id) ;
    }

    @Override
    public Boolean minusGood(String contentText) {
        StringBuffer sql = new StringBuffer()
                .append(" update t_reply set goodcount=goodcount-1  ")
                .append("  where content=? ");
        return jt.update(sql.toString(),contentText);
    }

    @Override
    public Boolean plusGood(String contentText) {
        StringBuffer sql = new StringBuffer()
                .append(" update t_reply set goodcount=goodcount+1  ")
                .append("  where content=? ");
        return jt.update(sql.toString(),contentText);
    }

    @Override
    public List<Reply> getAllReply() {
        StringBuffer sql = new StringBuffer()
                .append(" select * from t_reply ");

        return jt.query(sql.toString(),new ReplyMapper()) ;
    }

    @Override
    public Boolean removeFromCollect(int questionId) {
        StringBuffer sql = new StringBuffer()
                .append(" delete from t_collect ")
                .append(" where question_id=?");
        return jt.update(sql.toString(),questionId);
    }

    @Override
    public Boolean removeFromReply(int questionId) {
        StringBuffer sql = new StringBuffer()
                .append(" delete from t_reply ")
                .append(" where question_id=?");
        return jt.update(sql.toString(),questionId);
    }

    @Override
    public Boolean update(String title,String content,int questionId) {
        StringBuffer sql = new StringBuffer()
                .append(" update t_question set title=?,content=? ")
                .append("  where id=? ");
        return jt.update(sql.toString(),title,content,questionId);
    }


}
