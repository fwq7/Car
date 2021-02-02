package com.fwq.car.views;

import com.fwq.car.dao.carDao;
import com.fwq.car.dao.cartypeDao;
import com.fwq.car.dao.orderDao;
import com.fwq.car.dao.userDao;
import com.fwq.car.pojo.Car;
import com.fwq.car.pojo.Order;
import com.fwq.car.pojo.User;
import com.fwq.car.pojo.carType;
import com.fwq.car.utils.ListUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class findCar {

    JTable table;
    JScrollPane sp;
    DefaultTableModel dft = new DefaultTableModel();
    carDao carDao = new carDao();
    cartypeDao cartypeDao = new cartypeDao();

    userDao userDao = new userDao();
    orderDao orderDao = new orderDao();

    String[] columnNames = new String[] { "id", "车名", "价格/天", "车辆类型","车辆状态" };

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public JPanel findCarPanel(Integer uid) throws SQLException {
        //创建内容框
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createTitledBorder("车辆检索"));
        jPanel.setBounds(155, 0, 540, 495);


        JLabel jLabel = new JLabel("id:");
        jLabel.setBounds(35,20,20,20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(65,20,80,20);

        JLabel jLabel2 = new JLabel("车名:");
        jLabel2.setBounds(35,50,40,20);
        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(65,50,80,20);

        JComboBox jComboBox = new JComboBox();
        jComboBox.addItem("请选择");
        List<carType> carTypes = cartypeDao.selectcCarTypeList();

        for (carType carType : carTypes) {
            jComboBox.addItem(carType.getName());
        }
        jComboBox.setBounds(35,80,110,20);

        JButton jButton6 = new JButton("查询车辆");
        jButton6.setBounds(180,80,90,20);



        JLabel jLabel3 = new JLabel("id:");
        jLabel3.setBounds(300,80,20,20);
        JTextField jTextField3 = new JTextField();
        jTextField3.setBounds(330,80,50,20);

        JButton jButton7 = new JButton("确认租车");
        jButton7.setBounds(400,80,90,20);



        List<Car> carlist = carDao.selectCarList2();
        Object[][] cars = ListUtils.toCarArray(carlist);

        dft.setDataVector(cars,columnNames);
        table = new JTable(dft);
        table.setRowHeight(30);
        sp = new JScrollPane(table);
        sp.setBounds(60,100,400,250);




        jPanel.add(jLabel);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
        jPanel.add(jComboBox);
        jPanel.add(jButton6);
        jPanel.add(jButton7);
        jPanel.add(sp);
        jPanel.add(jTextField);




        //查询车辆
        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 三个查询条件 任意组合查询


                //三个条件都为空时 查询全部

                String id = jTextField.getText();
                String name = jTextField2.getText();
                String selectedItem = (String) jComboBox.getSelectedItem();
                if("".equals(id)&&"".equals(name)&&"请选择".equals(selectedItem)){
                    //查询全部
                    try {
                        System.out.println("all");
                        Object[][] objects = ListUtils.toCarArray(carDao.selectCarList2());
                        dft.setDataVector(objects,columnNames);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    //条件组合查询 三个条件任意组合
                    if ("".equals(id)){
                        id="0";
                    }
                    try {
                        List<Car> cars1 = carDao.selectCarByCondition(Integer.valueOf(id), name, selectedItem);

                        dft.setDataVector(ListUtils.toCarArray(cars1),columnNames);

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                }



            }
        });

        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                System.out.println("确认租车");

                String cid = jTextField3.getText();
                if("".equals(cid)){
                    JOptionPane.showMessageDialog(null,"输入车辆id！");
                }else {
                    //下订单  插入订单表

                    //下单前 判断余额 余额大于价格 允许借车

                    try {
                        User user = userDao.selectUserByid(uid);
                        Car car = carDao.selectCarByid(Integer.valueOf(cid));

                        if (user.getMoney()>car.getPrice()){

                            //创建订单类
                            Order order = new Order();
                            order.setUid(uid);
                            order.setCid(Integer.valueOf(cid));
                            order.setBtime(dateFormat.format(new Date()));
                            order.setRtime(null);
                            order.setStatus(0);//0未完成 1完成

                            //插入数据库
                            Boolean flag = orderDao.addOrder(order);
                            if(flag){
                                //改变车的状态
                                Boolean flag2 = carDao.updateCarStatus(Integer.valueOf(cid), "借出");
                                if(flag2){
                                    JOptionPane.showMessageDialog(null,"借车成功！");

                                }else {
                                    JOptionPane.showMessageDialog(null,"借车失败！");
                                }
                            }else {
                                JOptionPane.showMessageDialog(null,"借车失败！");
                            }

                        }else {
                            JOptionPane.showMessageDialog(null,"余额不足！");
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }


                }

            }


        });





        return jPanel;
    }
}
