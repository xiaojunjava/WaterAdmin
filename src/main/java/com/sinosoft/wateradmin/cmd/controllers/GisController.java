package com.sinosoft.wateradmin.cmd.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;
import com.sinosoft.wateradmin.cmd.service.IGisService;
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
@RequestMapping("/gis")
public class GisController {
	@Autowired
	private IShipMonitorService shipMonitorService;
	@Autowired
	private IGisService gisService;

	/**
	 * 获取所有正在执法的人的实时坐标
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getNowPeopleGis",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody  Map getNowPeopleGis() throws Exception{
		return gisService.getNowPeopleGis();
	}

	/**
	 * 获取执法历史轨迹
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getHisPeopleGis",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody  Map getHisPeopleGis() throws Exception{
		return null;
	}

	/**
	 * 获取所有目标实时移动中的坐标（车/船）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getNowSaGis",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody  Map getNowSaGis(String saType) throws Exception{
		if(saType==null||(saType!=null&&saType.trim().length()<=0)){
			return new HashMap();
		}
		return gisService.getNowSaGis(saType);
	}

	/**
	 * 获取
	 * @param saType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getHisSaGis",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody  Map getHisSaGis(String saType) throws Exception{
		if(saType==null||(saType!=null&&saType.trim().length()<=0)){
			return new HashMap();
		}
		return null;
	}
}