package com.fwq.car.views;

import com.fwq.car.dao.carDao;

import com.fwq.car.pojo.Car;
import com.fwq.car.utils.ListUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class carFix {


    JTable table;
    JScrollPane sp;
    DefaultTableModel dft = new DefaultTableModel();
    carDao carDao = new carDao();

    String[] columnNames = new String[] { "id", "车名", "价格/天", "车辆类型","车辆状态" };

    public JPanel oederpanel() throws SQLException {
        //创建内容框
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createTitledBorder("车辆报修"));
        jPanel.setBounds(155, 0, 540, 495);


        JLabel jLabel = new JLabel("id:");
        jLabel.setBounds(35,50,20,20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(65,50,100,20);


        JButton jButton6 = new JButton("查询车辆");
        jButton6.setBounds(200,50,90,20);



        JButton jButton7 = new JButton("确认报修");
        jButton7.setBounds(330,50,90,20);







        List<Car> carlist = carDao.selectCarList();
        Object[][] cars = ListUtils.toCarArray(carlist);

        dft.setDataVector(cars,columnNames);
        table = new JTable(dft);
        table.setRowHeight(30);
        sp = new JScrollPane(table);
        sp.setBounds(60,100,400,250);




        jPanel.add(jLabel);


        jPanel.add(jButton6);
        jPanel.add(jButton7);
        jPanel.add(sp);
        jPanel.add(jTextField);




        //查询车辆
        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //如果 id 为空  查询全部
                String id = jTextField.getText();

                if("".equals(id)){
                    try {
                        List<Car> carlist  =carDao.selectCarList();
                        Object[][] objects = ListUtils.toCarArray(carlist);
                        dft.setDataVector(objects,columnNames);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    try {
                        Car car = carDao.selectCarByid(Integer.valueOf(id));
                        List<Car> cars1 = new ArrayList<>();
                        cars1.add(car);
                        Object[][] objects = ListUtils.toCarArray(cars1);
                        dft.setDataVector(objects,columnNames);


                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });



        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String id = jTextField.getText();

                if("".equals(id)){
                    JOptionPane.showMessageDialog(null,"输入车辆id！");

                }else {

                    try {
                        Car car = carDao.selectCarByid(Integer.valueOf(id));

                        if(car==null){
                            JOptionPane.showMessageDialog(null,"没有该车！");
                        }else {
                            //更新车辆信息状态为 报修
                            car.setStatus("报修");
                            Boolean flag = carDao.updateUCarStatus(car.getId(),car.getStatus());
                            if(flag){
                                JOptionPane.showMessageDialog(null,"报修成功！");
                            }else {
                                JOptionPane.showMessageDialog(null,"报修失败！");
                            }

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
