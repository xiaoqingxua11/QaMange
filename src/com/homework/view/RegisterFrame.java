package com.homework.view;

import com.homework.dao.Dao;
import com.homework.dao.impl.DaoImpl;
import com.homework.po.User;
import com.homework.service.UserService;
import com.homework.service.impl.UserServiceImpl;
import com.homework.util.MD5Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

/**
 * ClassName:    RegisterFrame
 * Package:    com.homework.view
 * Description:
 * Datetime:    2020/9/24   15:21
 * Author:   ${小情绪}
 */
public class RegisterFrame extends  JFrame{
    private JLabel jTitle;//标题
    private JLabel jEmail;//邮箱
    private  JTextField jEmailField;//
    private  JLabel jPassword;//密码
    private JPasswordField jPasswordField;//
    private  JLabel jConfirmPwd;//确认密码
    private  JPasswordField jConfirmPwdField;
    private  JLabel jCheck;//验证码
    private  JTextField jCheckField;//
    private  JLabel checkLable;
    private  JButton jRefresh;//刷新按钮
    private  JButton jLogin;//登录按钮
    private  JButton jRegister;//注册按钮
    private  JButton jRset;//重置
    private String str;
    private StringBuffer strbuf;
    private  String email;
    private  String pwd;
    private  String confirmPwd;
    private  String checkNum;
    private  String trueCheckNum;
    private  JLabel nickName;
    private  JTextField nickNameField;
    private UserService userService =new UserServiceImpl();
    private  String name;

    public RegisterFrame ()  {
        this.setTitle("注册界面");
        this.setSize(400,400);
        this.setResizable(false);
        this.setLocation(250,150);
        this.setLayout(null);
        //界面
        jTitle=new JLabel("用户注册");
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
        //确认密码
        jConfirmPwd =new JLabel("确认密码:");
        jConfirmPwd.setBounds(74,160,80,30);
        jConfirmPwdField=new JPasswordField();
        jConfirmPwdField.setBounds(150,160,170,30);
// 验证码
        jCheck=new JLabel("验证码:");
        jCheck.setBounds(90,200,80,30);
        jCheckField=new JTextField();
        jCheckField.setBounds(150,200,100,30);
        checkLable=new JLabel(getCheckNum());
        checkLable.setBounds(255,200,50,30);
        jRefresh=new JButton("刷新");
        jRefresh.setBounds(300,200,60,30);
        nickName=new JLabel("昵称:");
        nickName.setBounds(100,240,80,30);
        nickNameField=new JTextField();
        nickNameField.setBounds(150,240,170,30);
        //登录注册
        jLogin=new JButton("登录");
        jLogin.setBounds(80,280,60,30);
        jRset=new JButton("重置");
        jRset.setBounds(180,280,60,30);
        jRegister=new JButton("注册");
        jRegister.setBounds(280,280,60,30);
        //添加
        this.add(jTitle);
        this.add(jEmail);
        this.add(jEmailField);
        this.add(jPassword);
        this.add(jPasswordField);
        this.add(jConfirmPwd);
        this.add(jConfirmPwdField);
        this.add(jCheck);
        this.add(jCheckField);
        this.add(checkLable);
        this.add(jRefresh);
        this.add(nickName);
        this.add(nickNameField);
        this.add(jLogin);
        this.add(jRset);
        this.add(jRegister);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //添加事件
        ActionListener listener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("刷新")){
                    checkLable.setText(getCheckNum());
                    System.out.println(MD5Util.getMd5String("1"));
                }
                if(e.getActionCommand().equals("登录")){
                    new AdminFrame();
                    RegisterFrame.this.dispose();
                }
                if(e.getActionCommand().equals("注册")){
                    email=jEmailField.getText();
                    pwd=jPasswordField.getText();
                    confirmPwd=jConfirmPwdField.getText();
                    checkNum=jCheckField.getText();
                    trueCheckNum=checkLable.getText();
                    name=nickNameField.getText();
                    List<User> list =userService.findStudentByEmail(email);
                    if(list.size()>0){
                        JOptionPane.showMessageDialog(RegisterFrame.this,"该邮箱已注册！");
                    }else {
                        if(null!=pwd&&!"".equals(pwd)&&null!=confirmPwd&&!"".equals(confirmPwd)){
                            if(pwd.equalsIgnoreCase(confirmPwd)){
                                if(null!=checkNum&&!"".equals(checkNum)){
                                    if(checkNum.equalsIgnoreCase(trueCheckNum)){
                                        User user=new User();
                                        user.setEmail(email);
                                        user.setPassword(MD5Util.getMd5String(pwd));

                                        user.setNickname(name);
                                        boolean flage=userService.save(user);
                                        if(flage){
                                            JOptionPane.showMessageDialog(RegisterFrame.this,"注册成功！");
                                            reset();
                                            RegisterFrame.this.dispose();
                                            new AdminFrame();
                                        }else {
                                            JOptionPane.showMessageDialog(RegisterFrame.this,"注册失败！");
                                        }

                                    }else {
                                        JOptionPane.showMessageDialog(RegisterFrame.this,"验证码输入错误！");
                                    }


                                }else {
                                    JOptionPane.showMessageDialog(RegisterFrame.this,"请输入验证码！");
                                }
                            }else {
                                JOptionPane.showMessageDialog(RegisterFrame.this,"两次密码不一致！");
                                reset();
                            }

                        }else {
                            JOptionPane.showMessageDialog(RegisterFrame.this,"请输入密码！");
                        }

                    }

                }
                if(e.getActionCommand().equals("重置")){
                   reset();
                }
            }
        };
        jRefresh.addActionListener(listener);
        jLogin.addActionListener(listener);
        jRegister.addActionListener(listener);
        jRset.addActionListener(listener);
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
    //重置方法
    public  void reset(){
        jEmailField.setText("");
        jPasswordField.setText("");
        jConfirmPwdField.setText("");
        jCheckField.setText("");
        checkLable.setText(getCheckNum());
    }

//    public static void main(String[] args) {
//        new RegisterFrame();
//    }
}
