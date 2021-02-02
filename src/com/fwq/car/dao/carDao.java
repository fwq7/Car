package com.fwq.car.dao;

import com.fwq.car.pojo.Car;
import com.fwq.car.utils.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class carDao {

    public QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
    public static Connection connection = null;
    static {
        try {
            connection = DaoUtils.openConn();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    //查询所有车

    public List<Car> selectCarList() throws SQLException {
        String sql = "select * from carinfo";
        return runner.query(connection,sql,new BeanListHandler<>(Car.class));
    }


    //查询所有在库车
    public List<Car> selectCarList2() throws SQLException {
        String sql = "select * from carinfo where status = '在库'";
        return runner.query(connection,sql,new BeanListHandler<>(Car.class));
    }

    //根据id查询车
    public Car selectCarByid(Integer id) throws SQLException {
        String sql = "select * from carinfo where id = ?";
        return runner.query(connection,sql,new BeanHandler<>(Car.class),id);
    }

    //根据类别查询

    public List<Car> selectCarByType(String type) throws SQLException {
        String sql = "select * from carinfo where type = ?";
        return runner.query(connection,sql,new BeanListHandler<>(Car.class),type);

    }
    // id name  type 三个条件组合查询

    public List<Car> selectCarByCondition(Integer id,String name,String type) throws SQLException {


        String sql = "select * from carinfo where status = '在库' ";

        if(id!=0){
            sql+="and id ='"+id+"'";
        }
        if(!"".equals(name)){
            sql+="and name = '"+name+"'";
        }
        if(!"请选择".equals(type)){
            sql+="and type = '"+type+"'";
        }

        System.out.println(sql);
        return runner.query(connection,sql,new BeanListHandler<>(Car.class));

    }
    //修改车
    public Boolean updateUCar(Integer id,String name,Double price,String type) throws SQLException {
        String sql = "update carinfo set name = ?,price = ?, type = ? where id = ?";
        return runner.update(connection,sql,name,price,type,id)>0?true:false;
    }
    //报修
    public Boolean updateUCarStatus(Integer id,String status) throws SQLException {
        String sql = "update carinfo set status = ? where id = ?";
        return runner.update(connection,sql,status,id)>0?true:false;
    }
    //删除车
    public Boolean delCar(Integer id) throws SQLException {
        String sql = "delete from carinfo where id = ?";
        return runner.update(connection,sql,id)>0?true:false;

    }
    //添加车
    public Boolean addCar(Car car) throws SQLException {
        String sql = "insert into carinfo(id,name,price,type,status) values (null,?,?,?,?)";
        return runner.update(connection,sql,car.getName(),car.getPrice(),car.getType(),car.getStatus())>0?true:false;
    }

    //借车  还车
    public Boolean updateCarStatus(Integer id,String status) throws SQLException {
        String sql = "update carinfo set status = ? where id = ?";
        return runner.update(connection,sql,status,id)>0?true:false;

    }


}
