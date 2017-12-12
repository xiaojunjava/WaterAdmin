package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.AppWateradminAddresslist;
import com.sinosoft.wateradmin.app.service.IAppWateradminAddresslistService;
import com.sinosoft.wateradmin.common.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：水政单位通讯录
 * @author lvzhixue
 * @date 2017-11-24 16:44
 */
@Controller
@RequestMapping("/wateradmin")
public class AppWateradminAddresslistController {

	@Autowired
	private IAppWateradminAddresslistService appWateradminAddresslistService;

	//--跳转到水政单位通讯录管理页面
	@RequestMapping(value = "/goToWateradminPage")
	public String goToWateradminPage()throws Exception{
		return "app/appWateradminAddresslist";
	}

	/**
	 *查询水政单位信息
	 * @param appWateradmin
	 * @return
	 */
	@RequestMapping(value="/getWateradminData")
	public @ResponseBody BasePage getWateradminData(@RequestParam(required=true,defaultValue="1") Integer page,
											  @RequestParam(required=true,defaultValue="10") Integer rows,
											 AppWateradminAddresslist appWateradmin) throws Exception{
		PageHelper.startPage(page, rows);
		List<AppWateradminAddresslist>  appWateradminList = this.appWateradminAddresslistService.selectDatas(appWateradmin);

		BasePage<AppWateradminAddresslist> pi = new BasePage<AppWateradminAddresslist>(appWateradminList);
		return pi;
	}

	/**
	 * 新增1条水政单位信息
	 * @param sa
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveWateradmin")
	public @ResponseBody Object saveWateradmin(@RequestBody AppWateradminAddresslist sa)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(sa==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		int ee=appWateradminAddresslistService.insert(sa);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
	@RequestMapping(value="/updateWateradmin")
	public @ResponseBody Object updateWateradmin(@RequestBody AppWateradminAddresslist wateradmin, HttpServletRequest request)throws Exception{
		Map m=new HashMap();

		Integer unitId = Integer.valueOf(request.getParameter("unitId"));
		wateradmin.setUnitId(unitId);

		/***条件异常判断***/
		if(wateradmin==null||(wateradmin!=null&&wateradmin.getUnitId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appWateradminAddresslistService.updateByPrimaryKey(wateradmin);
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
	 * @param unitId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delWateradmin")
	public @ResponseBody Object delWateradmin(int unitId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(unitId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appWateradminAddresslistService.deleteByPrimaryKey(unitId);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}

}