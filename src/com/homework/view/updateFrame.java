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
 * ClassName:    updateFrame
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/27   17:07
 * Author:   ${小情绪}
 */
public class updateFrame extends JFrame {
    private  JLabel title;
    private  JTextField titleField;
    private  JLabel content;
    private  JTextArea contentArea;
    private  JButton update;
    private  JButton reset;
    private QusetionService qusetionService=new QuestionServiceImpl();
    private  Question question=new Question();
    public updateFrame(int user_id,int questionId)  {
        List<Question> questionList=qusetionService.getQusetionByQuestionId(questionId);
        question = questionList.get(0);
        this.setTitle("修改页面");
        this.setSize(800,800);
        this.setLocation(250,150);
        this.setLayout(null);
        title=new JLabel("标题:");
        title.setBounds(30,50,50,30);
        titleField=new JTextField();
        titleField.setBounds(80,50,500,50);
        titleField.setText(question.getTitle());
        content=new JLabel("内容:");
        content.setBounds(30,150,50,30);
        contentArea=new JTextArea();
        contentArea.setBounds(80,150,500,100);
        contentArea.setText(question.getContent());
        update=new JButton("修改");
        update.setBounds(250,500,100,50);
        reset=new JButton("重置");
        reset.setBounds(500,500,100,50);

        this.add(title);
        this.add(titleField);
        this.add(content);
        this.add(contentArea);
        this.add(update);
        this.add(reset);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=titleField.getText();
                String textArea=contentArea.getText();
                if(e.getActionCommand().equals("修改")){
                   Boolean flage=qusetionService.update(text,textArea,questionId);
                   if(flage){
                       JOptionPane.showMessageDialog(updateFrame.this,"修改成功");
                       updateFrame.this.dispose();
                       new MyQuestionFrame(user_id);
                   }else {
                       JOptionPane.showMessageDialog(updateFrame.this,"修改失败");
                   }

                }else if(e.getActionCommand().equals("重置")){
                    titleField.setText(question.getTitle());
                    contentArea.setText(question.getContent());
                }

            }
        };
        reset.addActionListener(listener);
        update.addActionListener(listener);
    }


}
