package com.fwq.car.dao;

import com.fwq.car.pojo.User;
import com.fwq.car.utils.DaoUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class userDao {

    public QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
    public static Connection connection = null;
    static {
        try {
            connection = DaoUtils.openConn();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }


    //查询所有用户

    public List<User> selectUserList() throws SQLException {
        String sql = "select * from user";
        return runner.query(connection,sql,new BeanListHandler<>(User.class));
    }

    //根据id查询用户
    public User selectUserByid(Integer id) throws SQLException {
        String sql = "select * from user where id = ?";
        return runner.query(connection,sql,new BeanHandler<>(User.class),id);
    }

    //删除用户
    public Boolean updateUser(Integer id,String name,Double money) throws SQLException {
        String sql = "update user set name = ?,money = ? where id = ?";
        return runner.update(connection,sql,name,money,id)>0?true:false;

    }

    //删除用户
    public Boolean delUser(Integer id) throws SQLException {
        String sql = "delete from user where id = ?";
        return runner.update(connection,sql,id)>0?true:false;

    }
    //添加用户
    public Boolean addUser(User user) throws SQLException {
        String sql = "insert into user(id,name,psd,type,money) values (null,?,?,?,?)";
        return runner.update(connection,sql,user.getName(),user.getPsd(),user.getType(),user.getMoney())>0?true:false;
    }

    //登录
    //根据id查询用户
    public User selectUserBylogin(String name,String psd,Integer role) throws SQLException {
        String sql = "select * from user where name = ? and psd = ? and type = ?";
        return runner.query(connection,sql,new BeanHandler<>(User.class),name,psd,role);
    }

    //修改余额
    public Boolean updateUserByMoney(Integer id,Double money) throws SQLException {

        String sql = "update user set money = ? where id = ?";
        return runner.update(connection,sql,money,id)>0?true:false;
    }




    @Test
    public void test() throws SQLException {
        userDao userDao = new userDao();
        List<User> users = userDao.selectUserList();
        System.out.println(users);
    }

    @Test
    public void test2() throws SQLException {
        userDao userDao = new userDao();
        User user = userDao.selectUserByid(1);
        System.out.println(user);

    }


}
