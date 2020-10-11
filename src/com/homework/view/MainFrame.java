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
 * //首页
 * ClassName:    MainFrame
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/24   18:57
 * Author:   ${小情绪}
 */
public class MainFrame extends JFrame {
    //头部
    private JPanel topPanel;
    private JLabel keyLable;
    private JTextField keyLableField;
    private JButton searchBtn;
    private JButton myQestBtn;
    private JButton myCollectBtn;
    //中间
    private JTable jtable;
    private JScrollPane jsp;
    //尾部
    private JPanel bottomPanel;
    private JButton homeBtn;
    private JButton preBtn;
    private JLabel jPage;
    private JButton nextBtn;
    private JButton lastBtn;
    private JButton watchBtn;
    private QusetionService qusetionService = new QuestionServiceImpl();

    private int pageNum = 1;//开始时显示第一页
    private String key;//关键字
    private  int questionId;//被选中的问题Id
    private  String questionTitle;//被选中的问题的标题
    private  int selectedRow;
    private int selctdedColum;
    private String ceilString;



    public MainFrame(int user_id) {
        this.setTitle("论坛首页");
        this.setSize(800, 800);
        //头部
        topPanel = new JPanel();
        keyLable = new JLabel("关键字");
        keyLableField = new JTextField(18);
        searchBtn = new JButton("搜索");
        myQestBtn = new JButton("我的问题");
        myCollectBtn = new JButton("我的收藏");
        topPanel.add(keyLable);
        topPanel.add(keyLableField);
        topPanel.add(searchBtn);
        topPanel.add(myQestBtn);
        topPanel.add(myCollectBtn);

        //中间组件
        jtable = new JTable(new TableModel(qusetionService.getAllQusetion(pageNum, key)));
        jsp = new JScrollPane(jtable);

        //尾部
        bottomPanel = new JPanel();
        homeBtn = new JButton("首页");
        preBtn = new JButton("上一页");
        jPage = new JLabel("第" + pageNum + "页");
        nextBtn = new JButton("下一页");
        lastBtn = new JButton("尾页");
        watchBtn = new JButton("查看");
        bottomPanel.add(homeBtn);
        bottomPanel.add(preBtn);
        bottomPanel.add(jPage);
        bottomPanel.add(nextBtn);
        bottomPanel.add(lastBtn);
        bottomPanel.add(watchBtn);
        this.add(jsp);
        this.add(topPanel, "North");
        this.add(bottomPanel, "South");
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        //绑定事件
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //底部按钮
                if (e.getActionCommand().equals("首页")) {
                    if(pageNum==1){
                        JOptionPane.showMessageDialog(MainFrame.this,"已经是第一页啦");
                    }else{
                        pageNum=1;
                        jPage.setText("第" + pageNum + "页");
                        key=keyLableField.getText();
                        update();
                    }
                } else if (e.getActionCommand().equals("上一页")) {
                    if(pageNum>1){
                        pageNum=pageNum-1;
                        jPage.setText("第" + pageNum + "页");
                        key=keyLableField.getText();
                        update();
                    }else {
                        JOptionPane.showMessageDialog(MainFrame.this,"已经是第一页啦！");
                    }
                } else if (e.getActionCommand().equals("下一页")) {
                    key=keyLableField.getText();
                   int maxPage=qusetionService.getMaxPage(key);
                   if(pageNum<maxPage){
                       pageNum=pageNum+1;
                       jPage.setText(String.valueOf("第" + pageNum + "页"));
                       update();
                   }else {
                       JOptionPane.showMessageDialog(MainFrame.this,"已经是最后一页啦！");
                   }
                } else if (e.getActionCommand().equals("尾页")) {
                    int  maxPage=qusetionService.getMaxPage(key);
                    if(pageNum<maxPage){
                        pageNum=maxPage;
                        jPage.setText(String.valueOf("第" + pageNum + "页"));
                        update();
                    }else {
                        JOptionPane.showMessageDialog(MainFrame.this,"已经是最后一页啦！");
                    }
                } else if (e.getActionCommand().equals("查看")) {
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
                        new DetailFrame(questionId,user_id);
//                    MainFrame.this.dispose();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(MainFrame.this,"请先选择表格内容");
                    }

                } else if (e.getActionCommand().equals("搜索")) {//头部按钮
                    key=keyLableField.getText();
                    pageNum=1;
                    jPage.setText("第" + pageNum + "页");
                    update();
                } else if (e.getActionCommand().equals("我的问题")) {
                    new MyQuestionFrame(user_id);
//                    MainFrame.this.dispose();
                } else if (e.getActionCommand().equals("我的收藏")) {
                    new MyCollectFrame(user_id);
//                    MainFrame.this.dispose();
                }

            }
        };
        //绑定按钮
        searchBtn.addActionListener(listener);
        myQestBtn.addActionListener(listener);
        myCollectBtn.addActionListener(listener);
        homeBtn.addActionListener(listener);
        preBtn.addActionListener(listener);
        nextBtn.addActionListener(listener);
        lastBtn.addActionListener(listener);
        watchBtn.addActionListener(listener);
    }
    //跟新表格内容
    public void update() {
        this.remove(jsp);
        jtable = new JTable(new TableModel(qusetionService.getAllQusetion(pageNum,key)));
        jsp = new JScrollPane(jtable);
        this.add(jsp,"Center");
        this.setVisible(true);
    }

//    public static void main(String[] args) {
//        new MainFrame(1);
//    }
}
