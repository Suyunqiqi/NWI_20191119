package com.service;

import com.dao.DaoImpl;
import com.dao.DaoInterface;
import com.pojo.Commodity;
import com.pojo.ModeAndView;

public interface CommodityServiceInface {
    //添加
    ModeAndView addCommdity(String sql,Commodity commodity);
    //查询所有商品
    ModeAndView queryAll(String sql);
    //查询部分商品
    ModeAndView queryByCategory(String sql,int cateid);
    //查询单个商品
    ModeAndView queryById(String sql,int id);
    //删除商品（用Id）
    ModeAndView deleteByid(String sql,int id);
    //删除商品（用类别）
    ModeAndView deleteByCid(String sql,int cateid);
    //修改商品信息（用Id）
    ModeAndView updateById(String sql,Commodity commodity);
    DaoInterface getDao();
    void setDao(DaoInterface dao);
}
