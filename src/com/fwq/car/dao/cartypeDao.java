package com.fwq.car.dao;


import com.fwq.car.pojo.carType;
import com.fwq.car.utils.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class cartypeDao {

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

    public List<carType> selectcCarTypeList() throws SQLException {
        String sql = "select * from cartype";
        return runner.query(connection,sql,new BeanListHandler<>(carType.class));
    }


}
