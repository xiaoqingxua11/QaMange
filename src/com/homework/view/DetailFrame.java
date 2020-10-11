package com.homework.view;

import com.homework.po.Question;
import com.homework.po.Reply;
import com.homework.service.QusetionService;
import com.homework.service.impl.QuestionServiceImpl;
import com.homework.view.model.DetaiModel;
import com.homework.view.model.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ClassName:    DetailFrame
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/26   14:21
 * Author:   ${小情绪}
 */
public class DetailFrame extends JFrame {
    //头部
    private  JPanel topPanel;
    private JLabel title;
    private  JTextField titleField;
    private  JLabel content;
    private  JTextArea contentField;
    private JButton collectBtn;
    private JButton goodBtn;
    //中间
    private JTable jtable;
    private JScrollPane jsp;

    //尾部
    private  JPanel bottomPanel;
    private JLabel answer;
    private  JTextArea jTextArea;
    private  JScrollPane jScrollPane;
    private  JButton sendBtn;
    private QusetionService qusetionService=new QuestionServiceImpl();
    private int id;
    private  String contentText;
    private int goodCount;
    //默认没有点赞
    Boolean flage=false;
    private  int selectedRow;
    private int selctdedColum;
    private String ceilString;
    private  int selectedRow1;
    private int selctdedColum1;
    private String ceilString1;

    /**
     *
     * @param questionId//当前问题id
     * @param user_id//当前用户id
     */
    public DetailFrame(int questionId,int user_id) {
        id=questionId;
        List<Question> questionList=qusetionService.getQusetionByQuestionId(questionId);
        System.out.println(questionList);
        Question question=new Question();
        question.setTitle(questionList.get(0).getTitle());
        question.setContent(questionList.get(0).getContent());
        this.setTitle("问题详情");
        this.setSize(800,800);
        this.setLocation(250,150);

        topPanel=new JPanel();
        title=new JLabel("问题：");
        title.setBounds(50,50,50,50);
        titleField=new JTextField(65);
        titleField.setEditable(false);
        titleField.setText(question.getTitle());
        titleField.setBounds(60,50,500,50);

        content=new JLabel("内容：");
        content.setBounds(50,100,50,50);
        contentField=new JTextArea(25,40);
        contentField.setText(question.getContent());
        contentField.setEditable(false);
        contentField.setBounds(60,100,550,25);
        collectBtn=new JButton("收藏");
        collectBtn.setBounds(600,50,80,40);
        goodBtn=new JButton("点赞");
        goodBtn.setBounds(600,100,80,40);

        //中间组件
        jtable = new JTable(new DetaiModel(qusetionService.getReplyByQuestionId(questionId)));
        jsp = new JScrollPane(jtable);

        //尾部
        bottomPanel=new JPanel();
        answer=new JLabel("回复:");
        jTextArea=new JTextArea(2,50);
        jScrollPane=new JScrollPane(jTextArea);

        sendBtn=new JButton("发送");

        topPanel.add(title);
        topPanel.add(titleField);
        topPanel.add(content);
        topPanel.add(contentField);
        topPanel.add(collectBtn);
        topPanel.add(goodBtn);
        bottomPanel.add(answer);
        bottomPanel.add(jScrollPane);
        bottomPanel.add(sendBtn);
        bottomPanel.add(jsp);

        this.add(topPanel,"North");
        this.add(jsp);
        this.add(bottomPanel,"South");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("收藏")){
                   List<Question> questions= qusetionService.isCollected(id,user_id);
                   if(questions.size()>0){

                       Boolean flage=qusetionService.disCollect(id,user_id);
                       if(flage){
                           JOptionPane.showMessageDialog(DetailFrame.this,"取消收藏成功！");
                       }else{
                           JOptionPane.showMessageDialog(DetailFrame.this,"取消收藏失败！");
                       }

                   }else {
                        Boolean flage= qusetionService.collect(id,user_id);
                        if(flage){
                            JOptionPane.showMessageDialog(DetailFrame.this,"收藏成功！");

                        }else {
                            JOptionPane.showMessageDialog(DetailFrame.this,"收藏失败！");
                        }
                       questions.clear();
                   }
                }else if(e.getActionCommand().equals("点赞")){
                    try {
                        selectedRow = jtable.getSelectedRow();
                        selctdedColum = jtable.getSelectedColumn();
                        ceilString = jtable.getModel().getValueAt(selectedRow, selctdedColum).toString();

                        List<Reply> replyList=qusetionService.getAllReply();
                        for (Reply reply: replyList) {
                            goodCount=reply.getGoodcount();
                            contentText=reply.getContent();
                            if(String.valueOf(goodCount).equals(ceilString)){
                                JOptionPane.showMessageDialog(DetailFrame.this,"请选择内容");
                            }
                            if(reply.getContent().equals(ceilString)){

                                break;
                            }
                        }
                        //默认没有点赞 tips:从数据库中获取标志位  通过判断标志位进行判断是否点过赞
                        if(flage){
                                JOptionPane.showMessageDialog(DetailFrame.this,"取消点赞点赞成功!");
                                qusetionService.minusGood(contentText);
                                flage=!flage;
                                update();


                        }else{
                            JOptionPane.showMessageDialog(DetailFrame.this,"点赞成功!");
                            qusetionService.plusGood(contentText);
                            flage=!flage;
                            update();
                        }
                    } catch (HeadlessException headlessException) {
                        JOptionPane.showMessageDialog(DetailFrame.this,"请选择表格内容!");
                    }

                }else if(e.getActionCommand().equals("发送")){
                    String answerMessage=jTextArea.getText();
                    if(answerMessage.length()>0){
                        Boolean flage= qusetionService.updateReply(answerMessage,questionId,user_id);
                        if(flage){
                            JOptionPane.showMessageDialog(DetailFrame.this,"回复成功！");
                            jTextArea.setText("");
                            update();
                        }else{
                            JOptionPane.showMessageDialog(DetailFrame.this,"回复失败！");
                        }
                    }else {
                        JOptionPane.showMessageDialog(DetailFrame.this,"请输入回复内容！");
                    }

                }
            }
        };
        collectBtn.addActionListener(listener);
        goodBtn.addActionListener(listener);
        sendBtn.addActionListener(listener);
    }
    //跟新表格内容
    public void update() {
        this.remove(jsp);
        jtable = new JTable(new DetaiModel(qusetionService.getReplyByQuestionId(id)));
        jsp = new JScrollPane(jtable);
        this.add(jsp,"Center");
        this.setVisible(true);
    }
//    public static void main(String[] args) {
//        new DetailFrame(1,1);
//    }
}
