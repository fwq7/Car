package com.fwq.car.views;



import com.fwq.car.dao.carDao;
import com.fwq.car.dao.cartypeDao;
import com.fwq.car.pojo.Car;
import com.fwq.car.pojo.carType;
import com.fwq.car.utils.ListUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class carManager {


    JTable table;
    JScrollPane sp;
    DefaultTableModel dft = new DefaultTableModel();
    carDao carDao = new carDao();
    cartypeDao cartypeDao = new cartypeDao();
    String[] columnNames = new String[] { "id", "车名", "价格/天", "车辆类型","车辆状态" };

    public JPanel carPanel() throws SQLException {


        //创建内容框
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createTitledBorder("车辆信息管理"));
        jPanel.setBounds(155, 0, 540, 495);


        JLabel jLabel = new JLabel("id:");
        jLabel.setBounds(35,50,20,20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(65,50,50,20);

        JButton jButton6 = new JButton("查询车辆");
        JButton jButton7 = new JButton("删除车辆");
        JButton jButton8 = new JButton("修改车辆");
        JButton jButton9 = new JButton("添加车辆");
        jButton6.setBounds(125,50,90,20);
        jButton7.setBounds(220,50,90,20);
        jButton8.setBounds(315,50,90,20);
        jButton9.setBounds(410,50,90,20);

        JComboBox jComboBox1 = new JComboBox();
        JButton jButton12 = new JButton("分类查询");


        List<carType> carTypes = cartypeDao.selectcCarTypeList();

        //将数据库的所有分类信息加入到 下拉框中
        jComboBox1.addItem("请选择");
        for (carType carType : carTypes) {
            jComboBox1.addItem(carType.getName());
        }
        jComboBox1.setBounds(35,80,80,20);
        jButton12.setBounds(125,80,90,20);
        jPanel.add(jComboBox1);
        jPanel.add(jButton12);







        List<Car> carlist = carDao.selectCarList();
        Object[][] cars = ListUtils.toCarArray(carlist);


        dft.setDataVector(cars,columnNames);
        table = new JTable(dft);
        table.setRowHeight(30);
        sp = new JScrollPane(table);
        sp.setBounds(60,100,400,250);


        //修改用户信息控件
        JLabel jLabel2 = new JLabel("车名");
        jLabel2.setBounds(35,350,40,20);
        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(65,350,100,20);


        JLabel jLabel3 = new JLabel("价格");
        jLabel3.setBounds(35,380,40,20);
        JTextField jTextField3 = new JTextField();
        jTextField3.setBounds(65,380,100,20);

        JLabel jLabel4 = new JLabel("类型");
        jLabel4.setBounds(35,410,40,20);
        JComboBox jComboBox2 = new JComboBox();
        jComboBox2.addItem("请选择");
        for (carType carType : carTypes) {
            jComboBox2.addItem(carType.getName());
        }
        jComboBox2.setBounds(65,410,100,20);



        JButton jButton10 = new JButton("确认修改");
        jButton10.setBounds(200,400,90,20);

        JButton jButton11 = new JButton("确认添加");
        jButton11.setBounds(300,400,90,20);



        jPanel.add(jLabel);
        jPanel.add(jButton6);
        jPanel.add(jButton7);
        jPanel.add(jButton8);
        jPanel.add(jButton9);
        jPanel.add(sp);
        jPanel.add(jTextField);


        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel.add(jLabel4);
        jPanel.add(jTextField2);
        jPanel.add(jTextField3);
        jPanel.add(jComboBox2);
        jPanel.add(jButton10);
        jPanel.add(jButton11);




        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jTextField2.setVisible(false);
        jComboBox2.setVisible(false);
        jTextField3.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);


        //查询车辆按钮事件
        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //隐藏控件 清空数据
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);
                jTextField2.setVisible(false);
                jComboBox2.setVisible(false);
                jTextField3.setVisible(false);
                jButton10.setVisible(false);
                jButton11.setVisible(false);

                jTextField2.setText("");
                jComboBox2.setSelectedIndex(0);
                jTextField3.setText("");



                System.out.println("ada");
                String input_id = jTextField.getText();
                //输入框为空
                if("".equals(input_id)){
                    //查询全部
                    try {
                        Object[][] objects = ListUtils.toCarArray(carDao.selectCarList());
                        dft.setDataVector(objects,columnNames);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }else {
                    //按照id查询用户
                    List<Car> list = new ArrayList<>();

                    Car car = null;
                    try {
                        car = carDao.selectCarByid(Integer.valueOf(input_id));
                        if(car!=null){
                            list.add(car);
                            Object[][] objects = ListUtils.toCarArray(list);
                            dft.setDataVector(objects,columnNames);
                        }else {
                            JOptionPane.showMessageDialog(null,"没有该车辆！");
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });





        //删除车辆按钮事件
        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //隐藏控件 清空数据
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);
                jTextField2.setVisible(false);
                jComboBox2.setVisible(false);
                jTextField3.setVisible(false);
                jButton10.setVisible(false);
                jButton11.setVisible(false);

                jTextField2.setText("");
                jComboBox2.setSelectedIndex(0);
                jTextField3.setText("");



                System.out.println("ada");
                String input_id = jTextField.getText();
                if("".equals(input_id)){
                    JOptionPane.showMessageDialog(null,"请输入车辆id");
                }else{

                    //根据id删除用户
                    try {
                        Boolean flag = carDao.delCar(Integer.valueOf(input_id));
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




        //修改车辆按钮事件
        jButton8.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                String input_id = jTextField.getText();

                //隐藏控件 清空数据
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);
                jTextField2.setVisible(false);
                jComboBox2.setVisible(false);
                jTextField3.setVisible(false);
                jButton10.setVisible(false);
                jButton11.setVisible(false);
                jTextField2.setText("");
                jComboBox2.setSelectedIndex(0);
                jTextField3.setText("");

                if("".equals(input_id)){

                    JOptionPane.showMessageDialog(null,"请输入车辆id");
                }else {

                    //根据id修改车辆

                    //显示控件
                    jLabel2.setVisible(true);
                    jLabel3.setVisible(true);
                    jLabel4.setVisible(true);
                    jTextField2.setVisible(true);
                    jTextField3.setVisible(true);
                    jComboBox2.setVisible(true);
                    jButton10.setVisible(true);


                    try {
                        Car car = carDao.selectCarByid(Integer.valueOf(input_id));

                        jTextField2.setText(car.getName());
                        jTextField3.setText(car.getPrice().toString());
                        jComboBox2.setSelectedItem(car.getType());



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

                jButton10.setVisible(false);

                jTextField2.setText("");
                jTextField3.setText("");
                jComboBox2.setSelectedIndex(0);
                //显示控件
                jLabel2.setVisible(true);
                jLabel3.setVisible(true);
                jLabel4.setVisible(true);
                jTextField2.setVisible(true);
                jTextField3.setVisible(true);
                jComboBox2.setVisible(true);
                jButton11.setVisible(true);


            }
        });



        //确认修改按钮事件
        jButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String input_id = jTextField.getText();
                String name = jTextField2.getText();
                String price = jTextField3.getText();
                String type = (String) jComboBox2.getSelectedItem();
                if("".equals(name)||"".equals(price)){
                    JOptionPane.showMessageDialog(null,"请输入要修改的值");
                }else {
                    try {
                        Boolean flag = carDao.updateUCar(Integer.valueOf(input_id), name, Double.valueOf(price),type);
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
                String price = jTextField3.getText();
                String type = (String) jComboBox2.getSelectedItem();

                if("".equals(name)||"".equals(price)||jComboBox2.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null,"输入完整信息");
                }else {


                    Car car = new Car();
                    car.setName(name);
                    car.setPrice(Double.valueOf(price));
                    car.setType(type);
                    car.setStatus("在库");

                    try {
                        Boolean flag = carDao.addCar(car);

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







        //根据类型查询按钮事件

        jButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jComboBox1.getSelectedIndex();
                //如果没有选择类型
                if(index==0){
                    JOptionPane.showMessageDialog(null,"选择类型！");
                }else {
                    //分类查询
                    //获取选择的类型
                    String type =(String) jComboBox1.getSelectedItem();
                    try {
                        //根据类型查询
                        List<Car> carListByType = carDao.selectCarByType(type);
                        //如郭查询结构为空
                        if(carListByType.isEmpty()){
                            JOptionPane.showMessageDialog(null,"没有该类型的车！");
                        }else {
                            Object[][] objects = ListUtils.toCarArray(carListByType);
                            //更新 table 数据
                            dft.setDataVector(objects,columnNames);
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
