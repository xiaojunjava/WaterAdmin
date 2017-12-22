package com.sinosoft.wateradmin.cmd.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.cmd.bean.RoutePlanning;
import com.sinosoft.wateradmin.cmd.service.IRoutePlanningService;
import com.sinosoft.wateradmin.common.BasePage;
import com.sinosoft.wateradmin.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：路线规划
 *@author lkj
 */
@Controller
@RequestMapping("/rp")
public class RoutePlanningController {
	@Autowired
	private IRoutePlanningService routePlanningService;

	/**
	 * 跳转动作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goToListRp")
	public String goToListRp()throws Exception{
		return "cmd/route_planning_list";
	}
	/**
	 *查询路线规划信息
	 * @param rp
	 * @return
	 */
	@RequestMapping(value="/getRPDatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody List<RoutePlanning> getRPDatas(@RequestParam(required=true,defaultValue="1") Integer page,
														@RequestParam(required=true,defaultValue="10") Integer rows,RoutePlanning rp) throws Exception{
		PageHelper.startPage(page, rows);
		List<RoutePlanning>  ls=routePlanningService.selectDatas(rp);

		BasePage<RoutePlanning> pi=new BasePage<RoutePlanning>(ls);

		return ls;
	}
	/**
	 *根据id得到1个 路线规划 对象
	 * @param rpId
	 * @return
	 */
	@RequestMapping(value="/getRp",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody RoutePlanning getRp(int  rpId) throws Exception{
		RoutePlanning rp=routePlanningService.selectByPrimaryKey(rpId);
		return rp;
	}
	/**
	 * 新增1条路线规划记录
	 * @param rp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addRp",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object addRp(RoutePlanning rp)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(rp==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		rp.setRpMakeTime(new Date());
		rp.setRpMakePerson(CommonUtil.getLoginUser().getUserName());
		int ee=routePlanningService.insert(rp);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}

	/**
	 * 更新1条路线规划记录
	 * @param rp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateRp",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object updateRp(RoutePlanning rp)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(rp==null||(rp!=null&&rp.getRpId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=routePlanningService.update(rp);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}

	/**
	 * 删除一条记录
	 * @param rpId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delRp",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object delRp(int rpId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(rpId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=routePlanningService.deleteByPrimaryKey(rpId);
		if(ee>0){
			m.put("tag","true");
			m.put("message","删除成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
}