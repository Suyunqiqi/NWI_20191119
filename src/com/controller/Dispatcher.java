package com.controller;

import com.alibaba.fastjson.JSON;
import com.dao.DaoImpl;
import com.dao.DaoInterface;
import com.mysql.fabric.xmlrpc.base.Data;
import com.pojo.*;
import com.service.CommodityService;
import com.service.SellerService;
import com.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("*.action")
public class Dispatcher extends HttpServlet {
    private ModeAndView modeAndView;
    private SellerService sellerService;
    private UserService userService;
    private Commodity commodity;
    private CommodityAndShoppingcartdetail commodityAndShoppingcartdetail;
    private CommodityService commodityService;
    private Shoppingcartdetails shoppingcartdetails;
    @Override
    public void init(ServletConfig config) throws ServletException {
        modeAndView=new ModeAndView();
        sellerService=new SellerService();
        userService=new UserService();
        commodity=new Commodity();
        commodityAndShoppingcartdetail=new CommodityAndShoppingcartdetail();
        commodityService=new CommodityService();
        shoppingcartdetails=new Shoppingcartdetails();
        //dao=(DaoInterface)config.getServletContext().getAttribute("dao");
    }
    protected String getReqType(String uri){
        String reqType=null;
        String[] uris=uri.split("/");
        for (String u:uris){
            if (u.contains(".action")){
                reqType=u;
                return reqType;
            }
        }
        return "";
    }

    @Override
    public void destroy() {
        modeAndView=null;
        sellerService=null;
        userService=null;
        commodity=null;
        commodityService=null;
        commodityAndShoppingcartdetail=null;
        shoppingcartdetails=null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html,charset=utf-8");

        System.out.println("Dispatcher启动");

        //获取客户端请求路径
        String uri=getReqType(request.getServletPath());
        System.out.println(uri);
        //从请求中得到seession
        HttpSession session=request.getSession();
        //得到out对象
        PrintWriter out=response.getWriter();
        //进入登录逻辑
        if (uri.equals("login.action")){
            User user=new User();
            user.setUacc(request.getParameter("uacc"));
            user.setUpwd(request.getParameter("upwd"));

            System.out.println(modeAndView.getFlag());
            session.setAttribute("uname",modeAndView.getFlag());
            modeAndView=userService.login(user);
            out.write(JSON.toJSONString(modeAndView));
            if (modeAndView.getFlag() !=null){
                //往客户端cookie,保存账号密码
                Cookie cookie1=new Cookie("uacc",user.getUacc());
                Cookie cookie2=new Cookie("upwd",user.getUpwd());
                //设置cookie timeout时间
                cookie1.setMaxAge(60*5);
                cookie2.setMaxAge(60*5);
                //往客户端写cookie
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            //request.getRequestDispatcher(modeAndView.getUrl()).forward(request,response);
        }if (uri.equals("register.action")){
            System.out.println("进入注册");
            DaoInterface dao=(DaoInterface)request.getServletContext().getAttribute("dao");
            User user=new User();
            //user.setUid(Integer.parseInt(request.getParameter("uid")));
            user.setUname(request.getParameter("uname"));
            user.setUacc(request.getParameter("uacc"));
            user.setUpwd(request.getParameter("upwd"));
            user.setUtel(request.getParameter("utel"));
            modeAndView=userService.register(user);
            System.out.println(user.getUid()+"这里是Dispatcher");
            // request.getRequestDispatcher(modeAndView.getUrl()).forward(request,response);
            out.write(JSON.toJSONString(modeAndView));
        }if (uri.equals("SellerLogin.action")){
            Seller seller=new Seller();
            seller.setSacc(request.getParameter("sacc"));
            seller.setSpwd(request.getParameter("spwd"));
            /*session.setAttribute("uname",modeAndView.getMname());*/
            modeAndView=sellerService.login(seller);
            if (modeAndView.getFlag() !=null){
                //往客户端cookie,保存账号密码
                Cookie cookie1=new Cookie("uacc",seller.getSacc());
                Cookie cookie2=new Cookie("upwd",seller.getSpwd());
                //设置cookie timeout时间
                cookie1.setMaxAge(60*5);
                cookie2.setMaxAge(60*5);
                //往客户端写cookie
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            /*System.out.println(JSON.toJSONString(modeAndView));
            System.out.println("saddasasdasd"+modeAndView.getMname());*/
            out.write(JSON.toJSONString(modeAndView));
        }if (uri.equals("SellerRegister.action")){
            Seller seller=new Seller();
            //获取当前系统时间
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //将Date对象转换成字符串
            String stime=format.format(new Date());
            seller.setSacc(request.getParameter("sacc"));
            seller.setSpwd(request.getParameter("spwd"));
            seller.setStime(stime);
            System.out.println(seller.getStime());
            seller.setShopName(request.getParameter("shopName"));
            seller.setMainBusiness(request.getParameter("mainBusiness"));
            seller.setStel(request.getParameter("stel"));
            modeAndView=sellerService.register(seller);
            out.write(JSON.toJSONString(modeAndView));
        }if (uri.equals("welcome.action")){
//            DaoImpl dao=new DaoImpl();
            DaoInterface dao=(DaoInterface)request.getServletContext().getAttribute("dao");
            commodityService.setDao(dao);
            modeAndView=commodityService.queryAll(null);
           String x= JSON.toJSONString(modeAndView);
            System.out.println(x);

            out.write(x);
        }if (uri.equals("addToCart.action")){
            
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}