package com.fwq.car.views;

import javax.swing.*;
import java.sql.SQLException;

public class userInfo {

    public JPanel userInfoPanel() throws SQLException {

        //创建内容框
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createTitledBorder("用户信息"));
        jPanel.setBounds(155, 0, 540, 495);
        return jPanel;
    }
}
