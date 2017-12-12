package com.sinosoft.wateradmin.cmd.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.Users;
import com.sinosoft.wateradmin.cmd.bean.InstructionIssued;
import com.sinosoft.wateradmin.cmd.bean.ShipAlarm;
import com.sinosoft.wateradmin.cmd.service.IInstructionIssuedService;
import com.sinosoft.wateradmin.cmd.service.IShipAlarmService;
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
 * 控制层：下发指令
 *@author lkj
 */
@Controller
@RequestMapping("/shipAlarm")
public class ShipAlarmController {
	@Autowired
	private IShipAlarmService shipAlarmService;

	/**
	 * 跳转动作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goToListIi")
	public String goToListIi()throws Exception{
		return "cmd/instruction_issued_list";
	}

	/**
	 * 查询-分页
	 * @param page 当前页码
	 * @param rows 每页数据量
	 * @param shipAlarm 传参实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getShipAlarmDatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody BasePage<ShipAlarm> getShipAlarmDatas(@RequestParam(required=true,defaultValue="1") Integer page,
														@RequestParam(required=true,defaultValue="10") Integer rows, ShipAlarm shipAlarm) throws Exception{
		PageHelper.startPage(page, rows);
		List<ShipAlarm>  ls=shipAlarmService.selectDatas(shipAlarm);

		BasePage<ShipAlarm> bp=new BasePage<ShipAlarm>(ls);

		return bp;
	}
	@RequestMapping(value="/getNowShipAlarmDatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody List<ShipAlarm> getNowShipAlarmDatas(ShipAlarm shipAlarm) throws Exception{

		List<ShipAlarm>  ls=shipAlarmService.selectDatas(shipAlarm);

		return ls;
	}
	/**
	 *根据id得到1个 路线规划 对象
	 * @param csaId
	 * @return
	 */
	@RequestMapping(value="/getShipAlarm",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody ShipAlarm getShipAlarm(int csaId) throws Exception{
		ShipAlarm ii=shipAlarmService.selectByPrimaryKey(csaId);
		return ii;
	}
	/**
	 * 新增
	 * @param shipAlarm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addShipAlarm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object addShipAlarm(ShipAlarm shipAlarm)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(shipAlarm.getSaId()<=0){//没有车船id
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		shipAlarm.setCsaBegintime(new Date());

		int ee=shipAlarmService.insert(shipAlarm);
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
	 * @param shipAlarm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateShipAlarm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object updateShipAlarm(ShipAlarm shipAlarm)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(shipAlarm.getCsaId()<=0){//没有主键
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=shipAlarmService.update(shipAlarm);
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
	 * @param csaId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delShipAlarm",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object delIi(int csaId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(csaId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=shipAlarmService.deleteByPrimaryKey(csaId);
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