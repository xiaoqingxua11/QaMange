package com.homework.view;

import com.homework.po.Question;
import com.homework.service.QusetionService;
import com.homework.service.impl.QuestionServiceImpl;
import com.homework.view.model.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ClassName:    MyQuestionFrame
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/25   14:48
 * Author:   ${小情绪}
 */
public class MyQuestionFrame  extends JFrame {
    //头部
    private JPanel topPanel;
    private JLabel keyLable;
    private JTextField keyLableField;
    private JButton searchBtn;
    private JButton addBtn;

    //中间
    private JTable jtable;
    private JScrollPane jsp;
    //尾部
    private JPanel bottomPanel;
    private JButton deleBtn;
    private JButton updateBtn;
    private JButton watchBtn;
    private QusetionService qusetionService = new QuestionServiceImpl();

    private int pageNum = 1;//开始时显示第一页
    private String key;//关键字
    private int curId;
    private  int questionId;//被选中的问题Id
    private  String questionTitle;//被选中的问题的标题
    private  int selectedRow;
    private int selctdedColum;
    private String ceilString;



    public MyQuestionFrame(int user_id) {
        this.setTitle("我的问题");
        this.setSize(800, 800);
        //头部
        topPanel = new JPanel();
        keyLable = new JLabel("关键字");
        keyLableField = new JTextField(18);
        searchBtn = new JButton("搜索");
        addBtn=new JButton("发布新问题");

        topPanel.add(keyLable);
        topPanel.add(keyLableField);
        topPanel.add(searchBtn);
        topPanel.add(addBtn);


        //中间组件
        jtable = new JTable(new TableModel(qusetionService.getMyQusetionByUser_id(pageNum,key,user_id)));
        jsp = new JScrollPane(jtable);

        //尾部
        bottomPanel = new JPanel();
        deleBtn = new JButton("删除");
        updateBtn = new JButton("修改");
        watchBtn = new JButton("查看");
        bottomPanel.add(deleBtn);
        bottomPanel.add(updateBtn);
        bottomPanel.add(watchBtn);
        this.add(jsp);
        this.add(topPanel, "North");
        this.add(bottomPanel, "South");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        curId=user_id;
        //绑定事件
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //底部按钮
                //改sql语句 从子表里删除
                if (e.getActionCommand().equals("删除")) {

                    try {
                        selectedRow = jtable.getSelectedRow();
                        selctdedColum = jtable.getSelectedColumn();
                        ceilString = jtable.getModel().getValueAt(selectedRow, selctdedColum).toString();

                        List<Question> questionList=qusetionService.getAllQusetions();
                        for (Question question: questionList) {
                            questionId=question.getId();
                            questionTitle=question.getTitle();
                            if(String.valueOf(question.getId()).equals(ceilString)||question.getTitle().equals(ceilString)){
                                break;
                            }
                        }
                        Boolean flage1=qusetionService.removeFromCollect(questionId);
                        Boolean flage2=qusetionService.removeFromReply(questionId);
                        if(flage1==true&&flage2==true){
                            Boolean flage=
                                    qusetionService.romoveQusetionById(questionId);
                            if(flage){
                                update();
                                JOptionPane.showMessageDialog(MyQuestionFrame.this,"删除成功");
                                MyQuestionFrame.this.dispose();
                            }else{
                                JOptionPane.showMessageDialog(MyQuestionFrame.this,"删除失败");
                            }
                        }else{
                            Boolean flage=
                                    qusetionService.romoveQusetionById(questionId);
                            if(flage){
                                update();
                                JOptionPane.showMessageDialog(MyQuestionFrame.this,"删除成功");
                                MyQuestionFrame.this.dispose();
                            }else{
                                JOptionPane.showMessageDialog(MyQuestionFrame.this,"删除失败");
                            }
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(MyQuestionFrame.this,"请先选择表格内容");
                    }



                } else if (e.getActionCommand().equals("修改")) {
                    int selectedRow = jtable.getSelectedRow();
                    int selctdedColum = jtable.getSelectedColumn();
                    String ceilString = jtable.getModel().getValueAt(selectedRow, selctdedColum).toString();

                    List<Question> questionList=qusetionService.getAllQusetions();
                    for (Question question: questionList) {
                        questionId=question.getId();
                        questionTitle=question.getTitle();
                        if(String.valueOf(question.getId()).equals(ceilString)||question.getTitle().equals(ceilString)){
                            break;
                        }
                    }
                    new updateFrame(user_id,questionId);
                    MyQuestionFrame.this.dispose();

                } else if (e.getActionCommand().equals("查看")) {
                    int selectedRow = jtable.getSelectedRow();
                    int selctdedColum = jtable.getSelectedColumn();
                    String ceilString = jtable.getModel().getValueAt(selectedRow, selctdedColum).toString();

                    List<Question> questionList=qusetionService.getAllQusetions();
                    for (Question question: questionList) {
                        questionId=question.getId();
                        questionTitle=question.getTitle();
                        if(String.valueOf(question.getId()).equals(ceilString)||question.getTitle().equals(ceilString)){
                            break;
                        }
                    }
                    new DetailFrame(questionId,user_id);
                } else if (e.getActionCommand().equals("搜索")) {
                    key=keyLableField.getText();
                    update();
                }else if (e.getActionCommand().equals("发布新问题")) {
                    new publishFrame(user_id);
                }
            }
        };
        //绑定按钮
        searchBtn.addActionListener(listener);
        addBtn.addActionListener(listener);
        deleBtn.addActionListener(listener);
        updateBtn.addActionListener(listener);
        watchBtn.addActionListener(listener);
    }
    //跟新表格内容
    public void update() {
        this.remove(jsp);
        jtable = new JTable(new TableModel(qusetionService.getMyQusetionByUser_id(pageNum,key,curId)));
        jsp = new JScrollPane(jtable);
        this.add(jsp,"Center");
        this.setVisible(true);
    }

//    public static void main(String[] args) {
//        new MyQuestionFrame(2);
//    }

}
