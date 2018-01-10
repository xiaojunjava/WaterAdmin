package com.sinosoft.wateradmin.listeners;

import com.sinosoft.wateradmin.app.bean.Role;
import com.sinosoft.wateradmin.app.bean.Users;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter extends HttpServlet implements Filter {

    private Logger log = Logger.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);

        Users user = (Users) session.getAttribute("user");
		//演示时提供外链时用
		if(user==null){
				user=new Users();
				user.setUserLoginname("admin");
				user.setUserName("张婷婷");//与其它演示页保持显示名字的一致
				user.setUserPsw("111111");
				List ls=new ArrayList<>();
				Role role=new Role();
				role.setRoleId(1);
				ls.add(role);
				user.setRoleList(ls);
				request.getSession().setAttribute("user",user);
		}
        if (user == null) {
			String url = request.getRequestURI();
			//处理不过滤的情况
			if(url.endsWith(".js")||url.endsWith(".jpg")||url.endsWith(".png")||url.endsWith(".css")||url.endsWith(".jpeg")||url.endsWith("login.jsp")
					||url.indexOf("/login")>=0||url.indexOf("/app")>=0){
				chain.doFilter(request, response);
			}else if (url.indexOf("/manager") > 0||url.indexOf("_manager") > 0){//后台管理界面超时，跳后台首页
                String location = request.getContextPath()+"/admin_login.jsp";
				response.sendRedirect(location);

                System.out.println("成功拦截到外星人企图入侵系统后台   :  " + url);
                response.setHeader("Cache-Control", "no-store");
                response.setDateHeader("Expires", 0);
                response.setHeader("Pragma", "no-cache");
            }else{//其它跳普通用户首页
                String location = request.getContextPath()+"/login.jsp";
				response.sendRedirect(location);
                //request.getRequestDispatcher(location).forward(request, response);

                System.out.println("成功拦截到外星人企图入侵系统   :  " + url);
                response.setHeader("Cache-Control", "no-store");
                response.setDateHeader("Expires", 0);
                response.setHeader("Pragma", "no-cache");
            }
        } else {
            chain.doFilter(request, response);
        }
    }


}
