package com.sinosoft.wateradmin.cmd.controllers;

import com.sinosoft.wateradmin.app.bean.FunctionalModule;
import com.sinosoft.wateradmin.app.service.IFunctionalModuleService;
import com.sinosoft.wateradmin.cmd.service.IRoutePlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：主菜单跳转
 *@author lkj
 */
@Controller
public class  SysController {
	//前台退出
	@RequestMapping(value = "/jump.exit")
	public void jumpExitA(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.getSession().invalidate();
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}
	//后台退出
	@RequestMapping(value = "/jump.exit_manager")
	public void jumpExitB(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.getSession().invalidate();
		request.getRequestDispatcher("admin_login.jsp").forward(request,response);
	}
}