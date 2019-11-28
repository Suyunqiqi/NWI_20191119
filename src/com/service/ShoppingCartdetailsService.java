package com.service;

import com.dao.DaoImpl;
import com.dao.DaoInterface;
import com.pojo.CommodityAndShoppingcartdetail;
import com.pojo.ModeAndView;
import com.pojo.Shoppingcartdetails;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartdetailsService implements ShoppingCartdetailsServiceInface {
    private DaoInterface dao;
    @Override
    public ModeAndView addToCart(String sql, Shoppingcartdetails shoppingcartdetails) {
        /*
         *去商品表查询商品单价
         */
        String sql2="select * from shopping.commodity where id=?";
        ResultSet rs=null;
        double cprice=0.0;
        try {
            dao.creatPtmt(sql2, shoppingcartdetails.getCid());
            rs=dao.query();
            if (rs.next()){
                cprice=rs.getDouble("cprice");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dao.closers(rs);
                dao.closePtmt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
         *将商品信息放入购物车详情表
         */
        sql="insert into shopping.shoppingcartdetails(cid,shid,Num,totalprice)values(?,?,?,?)";
        ModeAndView modeAndView=new ModeAndView();
        try {
            dao.creatPtmt(sql,shoppingcartdetails.getCid(),shoppingcartdetails.getShid(),shoppingcartdetails.getNum(),cprice*shoppingcartdetails.getNum());
            int line=dao.update();
            if (line==1){
                modeAndView.setFlag("666--添加购物车详情成功");
            }else{
                modeAndView.setFlag("102--添加购物车详情失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dao.closePtmt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return modeAndView;
    }

    @Override
    public ModeAndView delComCart(String sql, int cid, int pid) {
        return null;
    }

    @Override
    public ModeAndView delByCartId(String sql, int cid) {
        return null;
    }

    @Override
    public ModeAndView queryAllByCid(String sql, int cid) {
        ModeAndView modeAndView=new ModeAndView();
        sql="SELECT * FROM shopping.shoppingcartdetails s,shopping.commodity c,where s.shid=? and s.cid=c.cid";
        ResultSet rs=null;
        try {
            dao.creatPtmt(sql,cid);
            List<CommodityAndShoppingcartdetail> commodityAndShoppingcartdetails=new ArrayList<>();
            while(rs.next()){
                CommodityAndShoppingcartdetail commodityAndShoppingcartdetail=new CommodityAndShoppingcartdetail();

                commodityAndShoppingcartdetail.setNum(rs.getInt("num"));//商品数量
                commodityAndShoppingcartdetail.setCid(rs.getInt("cid"));
                commodityAndShoppingcartdetail.setCname(rs.getString("cname"));//商品名称
                commodityAndShoppingcartdetail.setProductImg(rs.getString("productimg"));//商品图片
                commodityAndShoppingcartdetail.setTotalprice(rs.getDouble("totalprice"));//商品总价


                commodityAndShoppingcartdetails.add(commodityAndShoppingcartdetail);
            }
            modeAndView.setObject(commodityAndShoppingcartdetails);
            modeAndView.setFlag("666--查看购物车完成");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dao.closers(rs);
                dao.closePtmt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return modeAndView;
    }

    @Override
    public ModeAndView updateNumByPid(String sql, int cid, int pid) {
        return null;
    }

    @Override
    public DaoInterface getDao() {
        return this.dao;
    }

    @Override
    public void setDao(DaoImpl dao) {
        this.dao=dao;
    }
}
