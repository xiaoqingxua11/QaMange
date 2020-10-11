package com.homework.view;


import com.homework.po.User;
import com.homework.service.UserService;
import com.homework.service.impl.UserServiceImpl;
import com.homework.util.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

/**
 * 登录界面
 * ClassName:    AdminFrame
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/24   13:54
 * Author:   ${小情绪}
 */
public class AdminFrame  extends JFrame {
  private JLabel jTitle;//标题
  private JLabel jEmail;//邮箱
  private  JTextField jEmailField;//
  private  JLabel jPassword;//密码
  private JPasswordField jPasswordField;//
  private  JLabel jCheck;//验证码
  private  JTextField jCheckField;//
  private  JLabel checkLable;
  private  JButton jRefresh;//刷新按钮
  private  JButton jLogin;//登录按钮
  private  JButton jRegister;//注册按钮
     private String str;
    private StringBuffer strbuf;
    private String email;
    private  String pwd;
    private  String checkNum;
    private  String rightCheckNum;

    private UserService userService=new UserServiceImpl();

    public AdminFrame()  {
        this.setTitle("登录界面");
        this.setSize(400,400);
        this.setResizable(false);
        this.setLocation(250,150);
        this.setLayout(null);
        //界面
        jTitle=new JLabel("欢迎来到问答系统");
        //标题
        jTitle.setBounds(150,15,200,30);
        //邮箱
        jEmail=new JLabel("邮箱:");
        jEmail.setBounds(100,80,30,30);
        jEmailField=new JTextField();
        jEmailField.setBounds(150,80,170,30);
        //密码
        jPassword =new JLabel("密码:");
        jPassword.setBounds(100,120,30,30);
        jPasswordField=new JPasswordField();
        jPasswordField.setBounds(150,120,170,30);
//        //验证码
        jCheck=new JLabel("验证码:");
        jCheck.setBounds(90,160,80,30);
        jCheckField=new JTextField();
        jCheckField.setBounds(150,160,100,30);
        checkLable=new JLabel(getCheckNum());
        checkLable.setBounds(255,160,50,30);
        jRefresh=new JButton("刷新");
        jRefresh.setBounds(300,160,60,30);
        //登录注册
        jLogin=new JButton("登录");
        jLogin.setBounds(100,250,60,30);
        jRegister=new JButton("注册");
        jRegister.setBounds(260,250,60,30);
        //添加
        this.add(jTitle);
        this.add(jEmail);
        this.add(jEmailField);
        this.add(jPassword);
        this.add(jPasswordField);
        this.add(jCheck);
        this.add(jCheckField);
        this.add(checkLable);
        this.add(jRefresh);
        this.add(jLogin);
        this.add(jRegister);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);

        //添加事件
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if(e.getActionCommand().equals("刷新")){
                 checkLable.setText(getCheckNum());
             }
             if(e.getActionCommand().equals("登录")){
                email=jEmailField.getText();
                pwd=jPasswordField.getText();
                checkNum=jCheckField.getText();
                rightCheckNum=checkLable.getText();
                if(null!=checkNum&&!"".equals(checkNum)){
                    if(checkNum.equalsIgnoreCase(rightCheckNum)){
                      List<User> a= userService.findStudentByEmail(email);
                      if(a.size()>0){
                          User user=a.get(0);
                          if(null!=pwd&&!"".equals(pwd)){
                              if(MD5Util.getMd5String(pwd).equals(user.getPassword())){
                                  JOptionPane.showMessageDialog(AdminFrame.this,"欢迎你"+user.getNickname());
                                  AdminFrame.this.dispose();
                                  new MainFrame(user.getId());
                              }else {
                                  JOptionPane.showMessageDialog(AdminFrame.this,"密码错误！");
                                  checkLable.setText(getCheckNum());
                              }
                          }else {
                              JOptionPane.showMessageDialog(AdminFrame.this,"密码为空！");
                              checkLable.setText(getCheckNum());
                          }
                      }else {
                          JOptionPane.showMessageDialog(AdminFrame.this,"用户未注册！");
                          jEmailField.setText("");
                          jPasswordField.setText("");
                          checkLable.setText(getCheckNum());
                      }


                    }else {
                        JOptionPane.showMessageDialog(AdminFrame.this,"验证码错误！");
                        checkLable.setText(getCheckNum());
                    }
                }else {
                    JOptionPane.showMessageDialog(AdminFrame.this,"请输入验证码！");
                }

             }
             if(e.getActionCommand().equals("注册")){
                 new RegisterFrame();
                 AdminFrame.this.dispose();
             }
            }
        };
        jRefresh.addActionListener(listener);
        jLogin.addActionListener(listener);
        jRegister.addActionListener(listener);
    }
    //获取验证码
      public String getCheckNum(){
          Random ra=new Random();
          str="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
          strbuf=new StringBuffer();
          for (int i=0;i<4;i++){
              int index=ra.nextInt(62);
              char c = str.charAt(index);
              strbuf.append(c);
          }
        return  strbuf.toString();
      }

    public static void main(String[] args) {
        new AdminFrame();
    }
}
