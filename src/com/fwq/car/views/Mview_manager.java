package com.fwq.car.views;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Mview_manager {

    //设置 第一次加载主页面 标志
    boolean flag = true;

    //菜单panel
    JPanel jPanel1;

    //用户管理panel
    JPanel jPanel2;
    //车辆管理panel
    JPanel jPanel3;
    //车辆报修panel
    JPanel jPanel4;
    //经营分析panel
    JPanel jPanel5;

    public void init(JFrame loginFrame) throws SQLException {


        JFrame jFrame = new JFrame();
        jFrame.setSize(700, 500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setTitle("车辆租赁系统");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建菜单框
        jPanel1 = new JPanel();
        jPanel1.setLayout(null);
        jPanel1.setBorder(BorderFactory.createTitledBorder("菜单"));
        jPanel1.setBounds(0, 0, 150, 495);

        JButton button1 = new JButton("用户管理");
        JButton button2 = new JButton("车辆管理");
        JButton button3 = new JButton("车辆报修");
        JButton button4 = new JButton("经营分析");
        JButton button5 = new JButton("退出登录");

        button1.setBounds(25, 50, 100, 30);
        button2.setBounds(25, 120, 100, 30);
        button3.setBounds(25, 190, 100, 30);
        button4.setBounds(25, 260, 100, 30);
        button5.setBounds(25, 330, 100, 30);

        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        jPanel1.add(button4);
        jPanel1.add(button5);


        //创建用户内容框
        jPanel2 = new userManager().userPanel();
        jPanel3 = new carManager().carPanel();
        jPanel4 = new carFix().oederpanel();
        jPanel5 = new analysis().analysisPanel();


        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);
        jFrame.add(jPanel5);
        jFrame.setVisible(true);


        //用户管理按钮点击事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flag){
                    return;
                }else {

                    System.out.println("用户管理");
                    jPanel2.setVisible(true);
                    jPanel3.setVisible(false);
                    jPanel4.setVisible(false);

                }
            }
        });
        //车辆管理按钮点击事件
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=false;
                System.out.println("车辆管理");
                jPanel2.setVisible(false);
                jPanel3.setVisible(true);
                jPanel4.setVisible(false);

            }
        });
        //订单管理按钮点击事件
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=false;
                System.out.println("订单管理");
                jPanel2.setVisible(false);
                jPanel3.setVisible(false);
                jPanel4.setVisible(true);

            }
        });
        //类别管理按钮点击事件
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag=false;
                System.out.println("类别管理");
                jPanel2.setVisible(false);
                jPanel3.setVisible(false);
                jPanel4.setVisible(false);
                jPanel5.setVisible(true);



            }
        });
        //退出登录按钮点击事件
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出登录");
                jFrame.setVisible(false);
                loginFrame.setVisible(true);

            }
        });

    }

}

