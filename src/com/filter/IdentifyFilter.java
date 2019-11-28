package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class IdentifyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest reqq=(HttpServletRequest)req;
        HttpServletResponse ress=(HttpServletResponse)resp;
        /*Cookie[] cookies=reqq.getCookies();
        String uacc=null;
        String upwd=null;
        if (cookies!=null){
            for (Cookie c:cookies){
                if (c.getName().equals("uacc")){
                    uacc=c.getValue();
                }if (c.getName().equals("upwd")){
                    upwd=c.getValue();
                }
            }
        }*/
        String uri=reqq.getServletPath();
        //System.out.println(uri);
        HttpSession session=reqq.getSession();
        if (uri.endsWith("welcome.html")){
            if (session.getAttribute("uname")==null){
                /*System.out.println("开启拦截");
                reqq.setAttribute("msg","你还没有登录,请先登录");
                reqq.getRequestDispatcher("login.html").forward(reqq,ress);
                return;*/
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
