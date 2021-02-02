package com.fwq.car.views;

import com.fwq.car.dao.userDao;
import com.fwq.car.pojo.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class login {

    userDao userDao = new userDao();

    public void init(){

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

        JRadioButton jRadioButton1 = new JRadioButton("用户");
        jRadioButton1.setSelected(true);
        jRadioButton1.setBounds(80,110,80,30);
        JRadioButton jRadioButton2 = new JRadioButton("管理员");
        jRadioButton2.setBounds(200,110,80,30);
        JButton jButton = new JButton("登录");
        jButton.setBounds(80,150,80,30);
        JButton jButton2 = new JButton("注册");
        jButton2.setBounds(200,150,80,30);

        jFrame.add(jLabel1);
        jFrame.add(jTextField1);
        jFrame.add(jLabel2);
        jFrame.add(jTextField2);
        jFrame.add(jRadioButton1);
        jFrame.add(jRadioButton2);
        jFrame.add(jButton);
        jFrame.add(jButton2);
        jFrame.setVisible(true);


        //选择用户时 管理员取消选择
        jRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jRadioButton2.setSelected(false);
            }
        });

        //选择管理员时 用户取消选择
        jRadioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jRadioButton1.setSelected(false);
            }
        });

        //登录的点击事件函数
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //主界面
                Mview_manager mview_manager = new Mview_manager();
                Mview_user mview_user = new Mview_user();
                User user=null;

                String name = jTextField1.getText();
                String psd = jTextField2.getText();
                int role = 0;
                //如果选择 用户
                if(jRadioButton1.isSelected()){
                    role = 0;
                }else { //选择管理员
                    role = 1;
                }
                try {
                    user = userDao.selectUserBylogin(name, psd, role);
                    if(user==null){
                        JOptionPane.showMessageDialog(null,"用户名密码错误！");
                    }else {
                        JOptionPane.showMessageDialog(null,"登陆成功！");
                        //跳转主页面 前 清空控件的值
                        jTextField1.setText("");
                        jTextField2.setText("");
                        jFrame.setVisible(false);
                        if (user.getType()==0){
                            //用户登录
                            mview_user.init(jFrame,user.getId());

                        }else {
                            //管理员登录
                            mview_manager.init(jFrame);
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        //注册按钮点击事件
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jTextField1.setText("");
                jTextField2.setText("");
                //跳转注册页面
                jFrame.setVisible(false);

                register register = new register();
                register.init(jFrame);


            }
        });
    }


}
