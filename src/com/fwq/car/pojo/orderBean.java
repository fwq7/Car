package com.fwq.car.pojo;

import java.util.Date;

public class orderBean {

    private Integer id;
    private String uname;
    private String cname;
    private String btime;
    private String rtime;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "orderBean{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", cname='" + cname + '\'' +
                ", btime='" + btime + '\'' +
                ", rtime='" + rtime + '\'' +
                ", status=" + status +
                '}';
    }
}
