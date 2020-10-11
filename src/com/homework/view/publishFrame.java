package com.homework.view;

import com.homework.po.Question;
import com.homework.service.QusetionService;
import com.homework.service.impl.QuestionServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ClassName:    publishFrame
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/26   17:13
 * Author:   ${小情绪}
 */
public class publishFrame extends JFrame {
    private JLabel title;
    private JLabel questionName;
    private JTextField  questionNameField;
    private JLabel questionContent;
    private JTextArea questionContentArea;
    private JButton publishBtn;
    private  JButton resetBtn;
    private QusetionService qusetionService=new QuestionServiceImpl();
    public publishFrame(int user_id)  {

        this.setTitle("发布问题");
        this.setSize(800,800);
        this.setLocation(250,150);
        this.setLayout(null);
        title=new JLabel("发布新问题");
        title.setBounds(300,100,100,50);
        questionName=new JLabel("问题：");
        questionName.setBounds(150,180,50,50);
        questionNameField=new JTextField();
        questionNameField.setBounds(200,180,250,50);
        questionContent=new JLabel("内容:");
        questionContent.setBounds(150,300,50,50);
        questionContentArea=new JTextArea();
        questionContentArea.setBounds(200,300,500,150);
        publishBtn=new JButton("发布");
        publishBtn.setBounds(200,500,65,25);
        resetBtn=new JButton("重置");
        resetBtn.setBounds(500,500,65,25);
        this.add(title);
        this.add(questionName);
        this.add(questionNameField);
        this.add(questionContent);
        this.add(questionContentArea);
        this.add(publishBtn);
        this.add(resetBtn);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //添加事件
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("发布")) {
                    Question question=new Question();
                    question.setTitle(questionNameField.getText());
                    question.setContent(questionContentArea.getText());
                    question.setUser_id(user_id);
                   Boolean flage=qusetionService.publishQuestion(question.getTitle(),question.getContent(),question.getUser_id());
                   if(flage){
                       JOptionPane.showMessageDialog(publishFrame.this,"发布成功！");
                       publishFrame.this.dispose();
                       new MyQuestionFrame(user_id);
                   }else {
                       JOptionPane.showMessageDialog(publishFrame.this,"发布失败！");
                   }
                } else if (e.getActionCommand().equals("重置")) {
                 questionNameField.setText("");
                 questionContentArea.setText("");
                }


            }
        };
        publishBtn.addActionListener(listener);
        resetBtn.addActionListener(listener);
    }


}
