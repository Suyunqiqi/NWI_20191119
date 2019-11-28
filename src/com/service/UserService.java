package com.service;

import com.dao.DaoImpl;
import com.pojo.Commodity;
import com.pojo.ModeAndView;
import com.pojo.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {
    private DaoImpl di= new DaoImpl();
    private ModeAndView modeAndView=new ModeAndView();
    @Override
    public ModeAndView login(User user) {
        //ModeAndView modeAndView=new ModeAndView();
        try {
            di.creatConn();
            String sql="select * from shopping.user where uacc=?";
            System.out.println("建立连接");
            di.creatPtmt(sql,user.getUacc());
            System.out.println("创建SQL语句----执行登录");
            ResultSet rs=di.query();
            if (rs.next()){
                if (user.getUpwd().equals(rs.getString("upwd"))){
                    user.setUid(rs.getInt("uid"));
                    user.setUname(rs.getString("uname"));
                    user.setUacc(rs.getString("uacc"));
                    user.setUpwd(rs.getString("upwd"));
                    user.setUtel(rs.getString("utel"));
                    modeAndView.setMsg("欢迎登录");
                    modeAndView.setUrl("welcome.html");
                    modeAndView.setFlag("true");
                }else{
                    modeAndView.setMsg("密码错误，请重试");
                    modeAndView.setUrl("login.html");
                }
            }else{
                modeAndView.setMsg("账号不存在，请先去注册");
                modeAndView.setUrl("login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modeAndView;
    }

    @Override
    public ModeAndView register(User user) {
        System.out.println("用户注册业务层");
        //ModeAndView modeAndView=new ModeAndView();
        try {
            di.creatConn();
                String sql="insert into shopping.user(uname,uacc,upwd,utel)values(?,?,?,?)";
                System.out.println("建立连接");
                di.creatPtmt(sql,user.getUname(),user.getUacc(),user.getUpwd(),user.getUtel());
                System.out.println("创建SQL语句----执行用户注册");
                int h=0;
                h=di.update();
                System.out.println("-------------"+h);
                if (h>0){
                modeAndView.setUrl("login.html");
                modeAndView.setMsg("已注册，请登录");
                modeAndView.setFlag("true");
            }else {
                modeAndView.setMsg("注册失败");
                modeAndView.setUrl("register.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modeAndView;
    }
}




   /* @Override
    public List<?> look() {
        List<Commodity> list=new ArrayList<Commodity>();
        System.out.println("过来读取数据库里的商品");
        try {
            di.creatConn();
            String sql="select * from shopping.commodity";
            di.creatPtmt(sql);
            ResultSet rs=di.query();
            while(rs.next()){
                Commodity commodity=new Commodity();
                commodity.setCid(rs.getInt("cid"));
                commodity.setCaid(rs.getInt("caid"));
                commodity.setCname(rs.getString("cname"));
                commodity.setCprice(rs.getString("cprice"));
                commodity.setInStock(rs.getString("instock"));
                commodity.setPlace(rs.getString("place"));
                commodity.setProductImg(rs.getString("productimg"));
                commodity.setProductDes(rs.getString("productdes"));
                list.add(commodity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }*/