package com.sinosoft.wateradmin.cmd.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;
import com.sinosoft.wateradmin.cmd.service.IShipMonitorService;
import com.sinosoft.wateradmin.common.BasePage;
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
 * 控制层：车船实时位置监控记录
 *@author lkj
 */
@Controller
@RequestMapping("/sm")
public class ShipMonitorController {
	@Autowired
	private IShipMonitorService shipMonitorService;

	/**
	 * 跳转动作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goToListSm")
	public String goToListSm()throws Exception{
		return "cmd/ship_monitor_list";
	}
	/**
	 *查询-分页
	 * @param sm
	 * @return
	 */
	@RequestMapping(value="/getSMDatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody BasePage<ShipMonitor> getSMDatas(@RequestParam(required=true,defaultValue="1") Integer page,
													  @RequestParam(required=true,defaultValue="10") Integer rows, ShipMonitor sm) throws Exception{
		PageHelper.startPage(page, rows);
		List<ShipMonitor>  ls=shipMonitorService.selectDatas(sm);

		BasePage<ShipMonitor> pi=new BasePage<ShipMonitor>(ls);

		return pi;
	}
	/**
	 *根据id得到1个 路线规划 对象
	 * @param csmId
	 * @return
	 */
	@RequestMapping(value="/getSm",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody ShipMonitor getSm(int  csmId) throws Exception{
		ShipMonitor rp=shipMonitorService.selectByPrimaryKey(csmId);
		return rp;
	}
	/**
	 * 新增1条路线规划记录
	 * @param sm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addSm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object addSm(ShipMonitor sm)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(sm==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		sm.setSmBeginTime(new Date());//开始时间
		int ee=shipMonitorService.insert(sm);
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
	 * @param sm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateSm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object updateSm(ShipMonitor sm)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(sm==null||(sm!=null&&sm.getCsmId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=shipMonitorService.update(sm);
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
	 * 删除1条记录
	 * @param csmId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delSm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object delSm(int csmId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(csmId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=shipMonitorService.deleteByPrimaryKey(csmId);
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