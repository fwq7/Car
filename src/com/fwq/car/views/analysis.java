package com.fwq.car.views;

import com.fwq.car.dao.orderDao;
import com.fwq.car.pojo.OrderCountByUser;
import com.fwq.car.pojo.SaleBan;
import com.fwq.car.utils.ListUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class analysis {

    JTable table1;
    JScrollPane sp1;
    JTable table2;
    JScrollPane sp2;
    DefaultTableModel dft = new DefaultTableModel();
    orderDao orderDao = new orderDao();

    String[] columnNames1 = new String[] { "排名", "车名",  "车辆类型","价格/天","销量" };
    String[] columnNames2 = new String[] { "排名", "用户名", "订单量" };

    public JPanel analysisPanel() throws SQLException {

        //创建内容框
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(null);
        jPanel2.setBorder(BorderFactory.createTitledBorder("经营分析"));
        jPanel2.setBounds(155, 0, 540, 495);
        JButton jButton1 = new JButton("销量排行榜");
        JButton jButton2 = new JButton("用户活跃榜");
        jButton1.setBounds(40,50,100,20);
        jButton2.setBounds(180,50,100,20);

        table1 = new JTable(dft);
        table1.setRowHeight(30);
        sp1 = new JScrollPane(table1);
        sp1.setBounds(60,100,400,250);




        table2 = new JTable(dft);
        table2.setRowHeight(30);
        sp2 = new JScrollPane(table2);
        sp2.setBounds(60,100,400,250);


        jPanel2.add(sp1);
        jPanel2.add(sp2);

        sp1.setVisible(false);
        sp2.setVisible(false);
        jPanel2.add(jButton1);
        jPanel2.add(jButton2);



        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("销量排行榜");

                sp1.setVisible(true);
                sp2.setVisible(false);
                try {
                    List<SaleBan> saleBans = orderDao.selectOrderBySale();
                    dft.setDataVector(ListUtils.toSaleBanArray(saleBans),columnNames1);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("用户活跃榜");
                sp1.setVisible(false);
                sp2.setVisible(true);

                try {
                    List<OrderCountByUser> orderCountByUsers = orderDao.selectOrderByUserOrder();

                    dft.setDataVector(ListUtils.toOderUserCountArray(orderCountByUsers),columnNames2);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });


        return jPanel2;
    }
}
