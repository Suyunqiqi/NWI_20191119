package com.service;

import com.dao.DaoImpl;
import com.dao.DaoInterface;
import com.pojo.ModeAndView;
import com.pojo.Shoppingcartdetails;

public interface ShoppingCartdetailsServiceInface {
    //添加商品到购物车
    ModeAndView addToCart(String sql, Shoppingcartdetails shoppingcartdetails);
    //从购物车中移除商品
    ModeAndView delComCart(String sql,int cid,int pid);
    //下单（删除）
    ModeAndView delByCartId(String sql,int cid);
    //查看购物车(查询，用购物车id)
    ModeAndView queryAllByCid(String sql,int cid);
    //修改数量（购物车id,和商品id）
    ModeAndView updateNumByPid(String sql,int cid,int pid);
    DaoInterface getDao();
    void setDao(DaoImpl dao);
}
