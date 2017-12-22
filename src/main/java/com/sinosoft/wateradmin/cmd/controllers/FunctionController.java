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
@RequestMapping("/main")
public class
FunctionController {
	@Autowired
	private IRoutePlanningService routePlanningService;

	@Autowired
	private IFunctionalModuleService functionalModuleService;

	/**
	 * 跳指挥平台
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.cmd")
	public String jumpCmd()throws Exception{
		return "cmd/zhdd";
	}

	/**
	 * 跳办案管理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.case")
	public String jumpCase(HttpServletRequest request, HttpServletResponse response)throws Exception{
		initMenuList(request);
		return "handingcase/bagl_index";
	}
	/**
	 * 跳在线学习
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.learning")
	public String jumpLearning(HttpServletRequest request, HttpServletResponse response)throws Exception{
		initMenuList(request);
		return "learning/zxxx_index";
	}
	/**
	 * 跳协同办公
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.office")
	public String jumpOffice(HttpServletRequest request, HttpServletResponse response)throws Exception{
		initMenuList(request);
		return "office/xtbg_index";
	}

	private void initMenuList(HttpServletRequest request){
		//--系统编号
		Integer fmId = Integer.valueOf(request.getParameter("fmId"));
		Map map = new HashMap();
		map.put("fmId",fmId);
		map.put("fmType", 3);//1-app,2-指挥中心，3-普通操作员能操作的模块，4-系统管理员能操作的模块

		List menuList = new ArrayList();

		//--获取在线学习系统的一级菜单
		List<FunctionalModule> firstModuleList = this.functionalModuleService.selectChildNodeByfmIdAndType(map);
		for (int i = 0; i < firstModuleList.size(); i++) {//--遍历一级菜单获取二级菜单列表
			FunctionalModule moduleTmp = firstModuleList.get(i);

			map.remove("fmId");//--删除map中的fmId
			map.put("fmId",moduleTmp.getFmId());//--添加fmId

			List<FunctionalModule> secondModuleTmpList = this.functionalModuleService.selectChildNodeByfmIdAndType(map);
			Map<FunctionalModule,List<FunctionalModule>> mapModule = new HashMap<>();
			mapModule.put(moduleTmp,secondModuleTmpList);
			menuList.add(mapModule);
		}
		request.getSession().setAttribute("menuList",menuList);
	}
	/**
	 * 跳在线学习
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.guiji")
	public String jumpGuiji(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "cmd/guiji";
	}

	/**
	 * 跳路线规划
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.route")
	public String jumpRoute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "cmd/route_plan";
	}

	/**
	 * 跳视频监控
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.video")
	public String jumpVideo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "cmd/video";
	}

	/**
	 * 跳监控
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jump.monitor")
	public String jumpVideoMonitor(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "cmd/video_monitor";
	}
}