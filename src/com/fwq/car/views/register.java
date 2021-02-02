package com.fwq.car.views;

import com.fwq.car.dao.userDao;
import com.fwq.car.pojo.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class register {

    userDao userDao = new userDao();

    public void init(JFrame jFramelogin){


        JFrame jFrame = new JFrame();
        jFrame.setSize(400,300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JLabel jLabel1 = new JLabel("请输入用户名：");
        jLabel1.setBounds(50,10,100,30);
        JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(160,10,150,30);


        JLabel jLabel2 = new JLabel("请输入密码：");
        jLabel2.setBounds(50,60,100,30);
        JPasswordField jTextField2 = new JPasswordField();
        jTextField2.setBounds(160,60,150,30);

        JLabel jLabel3 = new JLabel("确认密码：");
        jLabel3.setBounds(50,100,100,30);
        JPasswordField jTextField3 = new JPasswordField();
        jTextField3.setBounds(160,100,150,30);


        JButton jButton = new JButton("注册");
        jButton.setBounds(80,170,80,30);

        JButton jButton2 = new JButton("返回登录");
        jButton2.setBounds(200,170,100,30);

        jFrame.add(jLabel1);
        jFrame.add(jTextField1);

        jFrame.add(jLabel2);
        jFrame.add(jTextField2);

        jFrame.add(jLabel3);
        jFrame.add(jTextField3);

        jFrame.add(jButton);
        jFrame.add(jButton2);
        jFrame.setVisible(true);



        //注册的点击事件函数
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jTextField1.getText();
                String psd = jTextField2.getText();
                int role = 0;

                //注册业务 插入数据库

                User user = new User();
                user.setName(name);
                user.setPsd(psd);
                user.setType(role);
                user.setMoney(Double.valueOf(0));

                Boolean flag = null;
                try {
                    flag = userDao.addUser(user);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if(flag){
                    JOptionPane.showMessageDialog(null,"注册成功！，去登陆吧");
                    //返回登陆页面


                }else {

                    JOptionPane.showMessageDialog(null,"注册失败");
                }


            }
        });


        //返回登录点击事件
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //跳转注册页面
                jFramelogin.setVisible(true);

                jTextField1.setText("");
                jTextField2.setText("");
                jFrame.setVisible(false);


            }
        });


    }
}
