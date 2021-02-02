package com.fwq.car.views;

import com.fwq.car.dao.userDao;
import com.fwq.car.pojo.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Mview_user {

    //设置 第一次加载主页面 标志
    boolean flag = true;

    //菜单panel
    JPanel jPanel1;

    //个人信息panel
    JPanel jPanel2;
    //车辆检索panel
    JPanel jPanel3;
    //我的订单panel
    JPanel jPanel4;


    JLabel jLabel6;

    userDao userDao = new userDao();
    public void init(JFrame loginFrame,Integer id) throws SQLException {


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

        JButton button1 = new JButton("个人信息");
        JButton button2 = new JButton("车辆检索");
        JButton button3 = new JButton("我的订单");
        JButton button4 = new JButton("退出登录");


        button1.setBounds(25, 50, 100, 30);
        button2.setBounds(25, 120, 100, 30);
        button3.setBounds(25, 190, 100, 30);
        button4.setBounds(25, 260, 100, 30);


        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        jPanel1.add(button4);

        jPanel2 = new userInfo().userInfoPanel();
        jPanel3 = new findCar().findCarPanel(id);
        jPanel4 = new myOrder().myOrderPanel(id);



        User user = userDao.selectUserByid(id);
        JLabel jLabel1 = new JLabel("姓名:");
        jLabel1.setBounds(100,100,100,30);

        JLabel jLabel2 = new JLabel(user.getName());
        jLabel2.setBounds(200,100,100,30);
        JLabel jLabel3 = new JLabel("类型:");

        jLabel3.setBounds(100,150,100,30);
        JLabel jLabel4 = new JLabel("普通用户");
        jLabel4.setBounds(200,150,100,30);
        JLabel jLabel5 = new JLabel("余额:");
        jLabel5.setBounds(100,200,100,30);
        jLabel6 = new JLabel(user.getMoney().toString());
        jLabel6.setBounds(200,200,100,30);

        jPanel2.add(jLabel1);
        jPanel2.add(jLabel2);
        jPanel2.add(jLabel3);
        jPanel2.add(jLabel4);
        jPanel2.add(jLabel5);
        jPanel2.add(jLabel6);



        jPanel2.setVisible(true);
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);





        jFrame.add(jPanel1);
        jFrame.add(jPanel2);
        jFrame.add(jPanel3);
        jFrame.add(jPanel4);




        jFrame.setVisible(true);


        //个人信息按钮点击事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("个人信息");
                if(flag){
                    return;
                }else {

                    User user = null;
                    try {
                        user = userDao.selectUserByid(id);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    jLabel6.setText(user.getMoney().toString());

                    jPanel2.setVisible(true);
                    jPanel3.setVisible(false);
                    jPanel4.setVisible(false);
                }


            }
        });
        //车辆检索按钮点击事件
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("车辆检索");
                flag=false;
                jPanel2.setVisible(false);
                jPanel3.setVisible(true);
                jPanel4.setVisible(false);


            }
        });
        //我的订单按钮点击事件
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("我的订单");
                flag=false;
                jPanel2.setVisible(false);
                jPanel3.setVisible(false);
                jPanel4.setVisible(true);
            }
        });






        //退出登录按钮点击事件
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出登录");
                jFrame.setVisible(false);
                loginFrame.setVisible(true);

            }
        });

    }
}
