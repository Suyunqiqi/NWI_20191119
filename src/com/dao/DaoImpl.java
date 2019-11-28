package com.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoImpl implements DaoInterface{
    private Connection conn;
    private PreparedStatement ptmt;
    @Override
    public void creatConn() throws Exception {
        DataSource c3p0Pool=new ComboPooledDataSource();
        conn=c3p0Pool.getConnection();
    }

    @Override
    public void creatPtmt(String sql, Object... objects) throws Exception {
        ptmt=conn.prepareStatement(sql);
        for (int i=0;i<objects.length;i++){
            ptmt.setObject(i+1,objects[i]);
        }
    }

    @Override
    public ResultSet query() throws Exception {
        return ptmt.executeQuery();
    }

    @Override
    public int update() throws Exception {
        return ptmt.executeUpdate();
    }

    @Override
    public void closeConn() throws Exception {
        conn.close();
    }

    @Override
    public void closePtmt() throws Exception {
        ptmt.close();
    }

    @Override
    public void closers(ResultSet rs) throws Exception {
        rs.close();
    }
    public Connection getConn(){
        return conn;
    }
}
