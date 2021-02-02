package com.fwq.car.views;

import com.fwq.car.dao.carDao;
import com.fwq.car.dao.orderDao;
import com.fwq.car.dao.userDao;
import com.fwq.car.pojo.Car;
import com.fwq.car.pojo.Order;
import com.fwq.car.pojo.User;
import com.fwq.car.pojo.orderBean;
import com.fwq.car.utils.DaoUtils;
import com.fwq.car.utils.Dateutils;
import com.fwq.car.utils.ListUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class myOrder {


    JTable table;
    JScrollPane sp;

    DefaultTableModel dft = new DefaultTableModel();
    userDao userDao = new userDao();
    orderDao orderDao = new orderDao();
    carDao carDao = new carDao();

    String[] columnNames = new String[] { "订单号", "用户名", "车名", "借车时间","还车时间","订单状态" };

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public JPanel myOrderPanel(Integer uid) throws SQLException {

        //创建内容框
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createTitledBorder("我的订单"));
        jPanel.setBounds(155, 0, 540, 495);



        JLabel jLabel = new JLabel("id:");
        jLabel.setBounds(35,60,20,20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(65,60,80,20);

        JButton jButton = new JButton("确认还车");
        jButton.setBounds(180,60,90,20);


        JButton jButton2 = new JButton("刷新");
        jButton2.setBounds(300,60,70,20);


        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(jButton);
        jPanel.add(jButton2);


        List<orderBean> orderBeans = orderDao.selectOrderByUid(uid);
        Object[][] orders = ListUtils.toOrderArray(orderBeans);


        dft.setDataVector(orders,columnNames);
        table = new JTable(dft);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        sp = new JScrollPane(table);
        sp.setBounds(30,100,450,250);


        jPanel.add(sp);



        //确认还车

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String id = jTextField.getText();

                if("".equals(id)){
                    JOptionPane.showMessageDialog(null,"输入订单号！");
                }else {

                    //根据 id 查询订单信息
                    try {
                        Order order = orderDao.selectOrderByid(Integer.valueOf(id),uid);

                        if(order==null){
                            JOptionPane.showMessageDialog(null,"你没有该订单！");
                        }else {
                            Car car = carDao.selectCarByid(order.getCid());

                            Integer status = order.getStatus();
                            if(status==0){
                                //还车 修改还车时间 订单状态 用户余额更新
                                Date rdate = new Date();
                                String rtime = dateFormat.format(rdate);
                                Boolean flag_order = orderDao.updateOrderByReturn(Integer.valueOf(id), rtime);

                                User user = userDao.selectUserByid(uid);

                                //算出 还车和借车的时间差
                                int datec = Dateutils.getDayDiffer(rdate, dateFormat.parse(order.getBtime()));
                                //算出 现在余额
                                Double monery = user.getMoney()-car.getPrice()*datec;

                                //更新 余额

                                Boolean flag_user = userDao.updateUserByMoney(uid, monery);


                                //更新车辆状态 为在库
                                Boolean flag_car = carDao.updateCarStatus(order.getCid(), "在库");

                                if(flag_order&&flag_user&&flag_car){
                                    JOptionPane.showMessageDialog(null,"还车成功！");

                                    System.out.println(orderDao.selectOrderByUid(uid));
                                    //刷新表
                                    Object[][] objects = ListUtils.toOrderArray(orderDao.selectOrderByUid(uid));
                                    dft.setDataVector(objects,columnNames);
                                }else {
                                    JOptionPane.showMessageDialog(null,"还车失败！");
                                }


                            }else {
                                JOptionPane.showMessageDialog(null,"你已归还！");
                            }

                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //刷新表
                Object[][] objects = new Object[0][];
                try {
                    objects = ListUtils.toOrderArray(orderDao.selectOrderByUid(uid));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                dft.setDataVector(objects,columnNames);
            }
        });





        return jPanel;
    }
}
