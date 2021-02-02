package com.fwq.car.dao;

import com.fwq.car.pojo.Order;

import com.fwq.car.pojo.OrderCountByUser;
import com.fwq.car.pojo.SaleBan;
import com.fwq.car.pojo.orderBean;
import com.fwq.car.utils.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class orderDao {

    public QueryRunner runner = new QueryRunner(DaoUtils.dataSource);
    public static Connection connection = null;
    static {
        try {
            connection = DaoUtils.openConn();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    //添加订单  借车
    public Boolean addOrder(Order order) throws SQLException {
        String sql = "insert into orders(id,uid,cid,btime,rtime,status) values (null,?,?,?,?,?)";
        return runner.update(connection,sql,order.getUid(),order.getCid(),order.getBtime(),order.getRtime(),order.getStatus())>0?true:false;
    }

    //查询用户订单
    public List<orderBean> selectOrderByUid(Integer uid) throws SQLException{
        String sql = " SELECT orders.id,user.name as uname,carinfo.name as cname,orders.btime,orders.rtime,orders.status FROM orders,user,carinfo WHERE uid = ? and uid=user.id and cid =carinfo.id ";
        return runner.query(connection,sql,new BeanListHandler<>(orderBean.class),uid);

    }

    //查询 订单 根据 id
    public Order selectOrderByid(Integer id,Integer uid) throws SQLException{
        String sql = " SELECT * from orders where id =? and uid =?";
        return runner.query(connection,sql,new BeanHandler<>(Order.class),id,uid);
    }

    //还车

    public Boolean updateOrderByReturn(Integer id,String rtime) throws SQLException {
        String sql = "update orders set rtime = ? ,status = 1 where id =?";
        return runner.update(connection,sql,rtime,id)>0?true:false;
    }

    //查询 销量排序

    public List<SaleBan> selectOrderBySale() throws SQLException {
        String sql = "SELECT carinfo.name,carinfo.type,carinfo.price,count(cid) as salecount from orders,carinfo where orders.status = 1 and cid=carinfo.id GROUP BY cid desc";
        return runner.query(connection,sql,new BeanListHandler<>(SaleBan.class));
    }


    //查询 用户订单量排行版
    public List<OrderCountByUser> selectOrderByUserOrder() throws SQLException {
        String sql = "SELECT user.name,count(uid) as ordercount from orders,user where orders.status = 1 and uid=user.id GROUP BY uid desc";
        return runner.query(connection,sql,new BeanListHandler<>(OrderCountByUser.class));
    }



    @Test
    public void test() throws SQLException {
        orderDao orderDao = new orderDao();
        List<orderBean> orderBeans = orderDao.selectOrderByUid(7);
        System.out.println(orderBeans);
    }

}
