package com.fwq.car.pojo;

public class OrderCountByUser {

    private String name;
    private Integer ordercount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Integer ordercount) {
        this.ordercount = ordercount;
    }

    @Override
    public String toString() {
        return "OrderCountByUser{" +
                "name='" + name + '\'' +
                ", ordercount=" + ordercount +
                '}';
    }
}
