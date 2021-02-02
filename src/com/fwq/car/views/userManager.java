package com.fwq.car.views;

import com.fwq.car.dao.userDao;
import com.fwq.car.pojo.User;
import com.fwq.car.utils.ListUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userManager {


    JTable table;
    JScrollPane sp;
    DefaultTableModel dft = new DefaultTableModel();

    userDao userDao = new userDao();

    String[] columnNames = new String[] { "id", "用户名", "用户类型", "余额" };
    public JPanel userPanel() throws SQLException {

        //创建内容框
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(null);
        jPanel2.setBorder(BorderFactory.createTitledBorder("用户信息管理"));
        jPanel2.setBounds(155, 0, 540, 495);

        JLabel jLabel = new JLabel("id:");
        jLabel.setBounds(35,50,20,20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(65,50,50,20);

        JButton jButton6 = new JButton("查询用户");
        JButton jButton7 = new JButton("删除用户");
        JButton jButton8 = new JButton("修改用户");
        JButton jButton9 = new JButton("添加用户");
        jButton6.setBounds(125,50,90,20);
        jButton7.setBounds(220,50,90,20);
        jButton8.setBounds(315,50,90,20);
        jButton9.setBounds(410,50,90,20);





        List<User> userslist = userDao.selectUserList();
        Object[][] users = ListUtils.toUserArray(userslist);


        dft.setDataVector(users,columnNames);
        table = new JTable(dft);
        table.setRowHeight(30);
        sp = new JScrollPane(table);
        sp.setBounds(60,100,400,250);


        //修改用户信息控件
        JLabel jLabel2 = new JLabel("姓名");
        jLabel2.setBounds(35,400,40,20);
        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(65,400,50,20);


        JLabel jLabel3 = new JLabel("密码");
        jLabel3.setBounds(120,400,40,20);
        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150,400,50,20);

        JLabel jLabel4 = new JLabel("余额");
        jLabel4.setBounds(200,400,40,20);
        JTextField jTextField4 = new JTextField();
        jTextField4.setBounds(230,400,50,20);



        JButton jButton10 = new JButton("确认修改");
        jButton10.setBounds(300,400,90,20);

        JButton jButton11 = new JButton("确认添加");
        jButton11.setBounds(390,400,90,20);



        jPanel2.add(jLabel);
        jPanel2.add(jButton6);
        jPanel2.add(jButton7);
        jPanel2.add(jButton8);
        jPanel2.add(jButton9);
        jPanel2.add(sp);
        jPanel2.add(jTextField);


        jPanel2.add(jLabel2);
        jPanel2.add(jLabel3);
        jPanel2.add(jLabel4);
        jPanel2.add(jTextField2);
        jPanel2.add(jPasswordField);
        jPanel2.add(jTextField4);
        jPanel2.add(jButton10);
        jPanel2.add(jButton11);




        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jTextField2.setVisible(false);
        jTextField4.setVisible(false);
        jPasswordField.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);



        //查询用户按钮事件
        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //隐藏控件 清空数据
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);
                jTextField2.setVisible(false);
                jTextField4.setVisible(false);
                jPasswordField.setVisible(false);
                jButton10.setVisible(false);
                jButton11.setVisible(false);

                jTextField2.setText("");
                jTextField4.setText("");
                jPasswordField.setText("");



                System.out.println("ada");
                String input_id = jTextField.getText();
                //输入框为空
                if("".equals(input_id)){
                    //查询全部
                    try {
                        Object[][] objects = ListUtils.toUserArray(userDao.selectUserList());
                        dft.setDataVector(objects,columnNames);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }else {
                    //按照id查询用户
                    List<User> list = new ArrayList<>();

                    User user = null;
                    try {
                        user = userDao.selectUserByid(Integer.valueOf(input_id));
                        if(user!=null){
                            list.add(user);
                            Object[][] objects = ListUtils.toUserArray(list);
                            dft.setDataVector(objects,columnNames);
                        }else {
                            JOptionPane.showMessageDialog(null,"没有该用户！");
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                }




            }
        });

        //删除用户按钮事件
        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //隐藏控件 清空数据
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);
                jTextField2.setVisible(false);
                jTextField4.setVisible(false);
                jPasswordField.setVisible(false);
                jButton10.setVisible(false);
                jButton11.setVisible(false);

                jTextField2.setText("");
                jTextField4.setText("");
                jPasswordField.setText("");



                System.out.println("ada");
                String input_id = jTextField.getText();
                if("".equals(input_id)){
                    JOptionPane.showMessageDialog(null,"请输入用户id");
                }else{

                    //根据id删除用户
                    try {
                        Boolean flag = userDao.delUser(Integer.valueOf(input_id));
                        if(flag){
                            JOptionPane.showMessageDialog(null,"删除成功！");


                        }else {
                            JOptionPane.showMessageDialog(null,"删除失败！");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                }
            }
        });

        //修改用户按钮事件
        jButton8.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("ad");
                String input_id = jTextField.getText();

                //隐藏控件 清空数据
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);
                jTextField2.setVisible(false);
                jTextField4.setVisible(false);
                jPasswordField.setVisible(false);
                jButton10.setVisible(false);
                jButton11.setVisible(false);
                jTextField2.setText("");
                jTextField4.setText("");
                jPasswordField.setText("");

                if("".equals(input_id)){

                    JOptionPane.showMessageDialog(null,"请输入用户id");
                }else {

                    //根据id修改用户

                    //显示控件
                    jLabel2.setVisible(true);
                    jLabel4.setVisible(true);
                    jTextField2.setVisible(true);
                    jTextField4.setVisible(true);
                    jButton10.setVisible(true);
                    try {
                        User user = userDao.selectUserByid(Integer.valueOf(input_id));

                        jTextField2.setText(user.getName());
                        jTextField4.setText(String.valueOf(user.getMoney()));



                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                }


            }
        });

        //添加用户按钮事件
        jButton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //隐藏控件 清空数据
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);
                jTextField2.setVisible(false);
                jTextField4.setVisible(false);
                jPasswordField.setVisible(false);
                jButton10.setVisible(false);
                jButton11.setVisible(false);
                jTextField2.setText("");
                jTextField4.setText("");
                jPasswordField.setText("");


                //显示控件
                jLabel2.setVisible(true);
                jLabel3.setVisible(true);
                jLabel4.setVisible(true);
                jTextField2.setVisible(true);
                jTextField4.setVisible(true);
                jPasswordField.setVisible(true);
                jButton11.setVisible(true);


            }
        });



        //确认修改按钮事件
        jButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input_id = jTextField.getText();
                String name = jTextField2.getText();
                String money = jTextField4.getText();
                if("".equals(name)||"".equals(money)){
                    JOptionPane.showMessageDialog(null,"请输入要修改的值");
                }else {
                    try {
                        Boolean flag = userDao.updateUser(Integer.valueOf(input_id), name, Double.valueOf(money));
                        if (flag){
                            JOptionPane.showMessageDialog(null,"修改成功");

                        }else {

                            JOptionPane.showMessageDialog(null,"修改失败");


                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

        //确认添加按钮事件
        jButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String name = jTextField2.getText();
                String psd = jPasswordField.getText();
                String money = jTextField4.getText();

                if("".equals(name)||"".equals(psd)||"".equals(money)){
                    JOptionPane.showMessageDialog(null,"输入完整信息");
                }else {

                    User user = new User();
                    user.setName(name);
                    user.setPsd(psd);
                    user.setType(0);
                    user.setMoney(Double.valueOf(money));

                    try {
                        Boolean flag = userDao.addUser(user);

                        if(flag){
                            JOptionPane.showMessageDialog(null,"添加成功");

                        }else {
                            JOptionPane.showMessageDialog(null,"添加失败");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }





            }
        });




        return jPanel2;
    }
}
