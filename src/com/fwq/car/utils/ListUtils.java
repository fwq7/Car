package com.fwq.car.utils;

import com.fwq.car.pojo.*;

import java.util.List;

public class ListUtils {
    public static Object[][] toUserArray(List<User> list){


        Object[][] data=new Object[list.size()][4];
        for(int i=0;i<list.size();i++){
            User user = list.get(i);
            data[i][0]=user.getId();
            data[i][1]=user.getName();
            data[i][2]=user.getType()==1?"管理员":"普通用户";
            data[i][3]=user.getMoney();
        }
        return data;
    }


    public static Object[][] toCarArray(List<Car> list){
        Object[][] data=new Object[list.size()][5];

        for(int i=0;i<list.size();i++){
            Car car = list.get(i);
            data[i][0]=car.getId();
            data[i][1]=car.getName();
            data[i][2]=car.getPrice();
            data[i][3]=car.getType();
            data[i][4]=car.getStatus();

        }
        return data;
    }


    public static Object[][] toOrderArray(List<orderBean> list){
        Object[][] data=new Object[list.size()][6];

        for(int i=0;i<list.size();i++){
            orderBean orderBean = list.get(i);
            data[i][0]=orderBean.getId();
            data[i][1]=orderBean.getUname();
            data[i][2]=orderBean.getCname();
            data[i][3]=orderBean.getBtime();
            if(orderBean.getRtime()==null){
                data[i][4]="未还";
            }else {
                data[i][4]=orderBean.getRtime();
            }
            data[i][5]=orderBean.getStatus()==0?"未完成":"完成";
        }
        return data;
    }




    public static Object[][] toSaleBanArray(List<SaleBan> list){
        Object[][] data=new Object[list.size()][5];

        for(int i=0;i<list.size();i++){
            SaleBan saleBan = list.get(i);
            data[i][0]=i+1;
            data[i][1]=saleBan.getName();
            data[i][2]=saleBan.getType();
            data[i][3]=saleBan.getPrice();
            data[i][4]=saleBan.getSalecount();

        }
        return data;
    }



    public static Object[][] toOderUserCountArray(List<OrderCountByUser> list){
        Object[][] data=new Object[list.size()][3];

        for(int i=0;i<list.size();i++){
            OrderCountByUser OrderCountByUser = list.get(i);
            data[i][0]=i+1;
            data[i][1]=OrderCountByUser.getName();
            data[i][2]=OrderCountByUser.getOrdercount();
        }
        return data;
    }



}
