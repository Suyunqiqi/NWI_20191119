package com.service;

import com.dao.DaoImpl;
import com.pojo.ModeAndView;
import com.pojo.Seller;

import java.sql.ResultSet;

public class SellerService implements SellerServiceInterface {
    private DaoImpl di=new DaoImpl();
    @Override
    public ModeAndView login(Seller seller) {
        ModeAndView modeAndView=new ModeAndView();
        try {
            di.creatConn();
            String sql="select * from shopping.seller where sacc=?";
            di.creatPtmt(sql,seller.getSacc());
            System.out.println("创建SQL语句----执行商家登录");

            ResultSet rs=di.query();
            if (rs.next()){
                if (seller.getSpwd().equals(rs.getString("spwd"))){
                    modeAndView.setUrl("welcome.html");
                    modeAndView.setFlag("true");
                }else{
                    modeAndView.setMsg("密码错误，请重试");
                    modeAndView.setUrl("SellerLogin.html");
                }
            }else{
                modeAndView.setMsg("账号不存在，请先注册");
                modeAndView.setUrl("SellerLogin.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modeAndView;
    }

    @Override
    public ModeAndView register(Seller seller) {
        System.out.println("进入注册");
        ModeAndView modeAndView=new ModeAndView();
        try {
            di.creatConn();
            String sql="insert into shopping.seller(sacc,spwd,stime,shopName,mainBusiness,stel)values(?,?,?,?,?,?)";
            System.out.println("创建连接");
            di.creatPtmt(sql,seller.getSacc(),seller.getSpwd(),seller.getStime(),seller.getShopName(),seller.getMainBusiness(),seller.getStel());
            System.out.println("创建SQL语句----执行商家注册");
            int S=0;
            S=di.update();
            if (S>0){
                modeAndView.setUrl("SellerLogin.html");
                modeAndView.setMsg("已注册，请登录");
                modeAndView.setFlag("true");
            }else{
                modeAndView.setMsg("注册失败");
                modeAndView.setUrl("SellerRegister.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modeAndView;
    }
}
