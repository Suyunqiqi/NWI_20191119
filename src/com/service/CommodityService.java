package com.service;

import com.dao.DaoImpl;
import com.dao.DaoInterface;
import com.pojo.Commodity;
import com.pojo.ModeAndView;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommodityService implements CommodityServiceInface{
    private DaoInterface dao;
    @Override
    public ModeAndView addCommdity(String sql, Commodity commodity) {
        return null;
    }

    @Override
    public ModeAndView queryAll(String sql) {
        sql="select * from commodity";
        ResultSet rs=null;
        ModeAndView model=new ModeAndView();
        try {
            dao.creatPtmt(sql);
            rs=dao.query();
            List<Commodity> commodities=new ArrayList<>();
            while(rs.next()){
                Commodity commodity=new Commodity();
                commodity.setCid(rs.getInt("cid"));
                commodity.setCaid(rs.getInt("caid"));
                commodity.setCname(rs.getString("cname"));
                commodity.setCprice(rs.getDouble("cprice"));
                commodity.setInStock(rs.getString("instock"));
                commodity.setPlace(rs.getString("place"));
                commodity.setProductImg(rs.getString("productimg"));
                commodity.setProductDes(rs.getString("productdes"));
                commodities.add(commodity);
            }
            //true
            model.setFlag("666");
            model.setObject(commodities);
        } catch (Exception e) {
            e.printStackTrace();
            model.setFlag("101--数据访问出错");
        }
        return model;
    }

    @Override
    public ModeAndView queryByCategory(String sql, int cateid) {
        return null;
    }

    @Override
    public ModeAndView queryById(String sql, int id) {
        return null;
    }

    @Override
    public ModeAndView deleteByid(String sql, int id) {
        return null;
    }

    @Override
    public ModeAndView deleteByCid(String sql, int cateid) {
        return null;
    }

    @Override
    public ModeAndView updateById(String sql, Commodity commodity) {
        return null;
    }

    @Override
    public DaoInterface getDao() {
        return this.dao;
    }

    @Override
    public void setDao(DaoInterface dao) {
        this.dao=dao;
    }
}
