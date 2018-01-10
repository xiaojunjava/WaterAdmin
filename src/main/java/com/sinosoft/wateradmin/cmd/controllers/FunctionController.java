package com.sinosoft.wateradmin.cmd.controllers;

import com.sinosoft.wateradmin.app.bean.FunctionalModule;
import com.sinosoft.wateradmin.app.bean.Users;
import com.sinosoft.wateradmin.app.service.IFunctionalModuleService;
import com.sinosoft.wateradmin.cmd.service.IRoutePlanningService;
import com.sinosoft.wateradmin.common.CommonUtil;
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
		Users nowUser=CommonUtil.getLoginUser();
		Map map = new HashMap();
		map.put("parFmId",fmId);
		map.put("roleId",nowUser.getRoleList().get(0).getRoleId());
		List menuList = new ArrayList();

		//--获取在线学习系统的一级菜单
		List<FunctionalModule> firstModuleList = this.functionalModuleService.selectChildNodeByfmId(fmId);
		for (int i = 0; i < firstModuleList.size(); i++) {//--遍历一级菜单获取二级菜单列表
			FunctionalModule moduleTmp = firstModuleList.get(i);

//			map.remove("fmId");//--删除map中的fmId
			map.put("parFmId",moduleTmp.getFmId());//--添加fmId

			List<FunctionalModule> secondModuleTmpList = this.functionalModuleService.selectChildNodes(map);
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
	//跳--指挥调度
	@RequestMapping(value = "/jump.cmd.zhdd")
	public String jumpCmdZhdd(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "cmd/cmd_zhdd";
	}
	//跳--采沙监管
	@RequestMapping(value = "/jump.cmd.csjg")
	public String jumpCmdCsjg(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "cmd/cmd_csjg";
	}
	//跳--监控管理
	@RequestMapping(value = "/jump.cmd.jkgl")
	public String jumpCmdJkgl(HttpServletRequest request, HttpServletResponse response)throws Exception{
		return "cmd/cmd_jkgl";
	}

	@RequestMapping(value = "/jump.xczf")
	public String jumpXczf(HttpServletRequest request, HttpServletResponse response)throws Exception{
		initMenuList(request);
		return "app/xczf_index";
	}
}