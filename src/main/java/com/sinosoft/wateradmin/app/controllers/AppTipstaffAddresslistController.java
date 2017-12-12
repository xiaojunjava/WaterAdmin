package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.AppTipstaffAddresslist;
import com.sinosoft.wateradmin.app.service.IAppTipstaffAddresslistService;
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
 * 控制层：执法人员通讯录
 * @author lvzhixue
 * @date 2017-11-24 16:44
 */
@Controller
@RequestMapping("/tipstaff")
public class AppTipstaffAddresslistController {

	@Autowired
	private IAppTipstaffAddresslistService appTipstaffAddresslistService;

	//--跳转到执法人员通讯录管理页面
	@RequestMapping(value = "/goToTipstaffPage")
	public String goToTipstaffPage()throws Exception{
		return "app/appTipstaffAddresslist";
	}

	/**
	 *查询执法人员信息
	 * @param tipstaff
	 * @return
	 */
	@RequestMapping(value="/getAtalTipstaffData")
	public @ResponseBody BasePage getTipstaffData(@RequestParam(required=true,defaultValue="1") Integer page,
													@RequestParam(required=true,defaultValue="10") Integer rows,
													AppTipstaffAddresslist tipstaff) throws Exception{
		PageHelper.startPage(page, rows);
		List<AppTipstaffAddresslist>  appTipstaffList = this.appTipstaffAddresslistService.selectDatas(tipstaff);

		BasePage<AppTipstaffAddresslist> pi = new BasePage<AppTipstaffAddresslist>(appTipstaffList);
		return pi;
	}

	/**
	 * 新增1条执法人员信息
	 * @param tipstaff
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveTipstaff")
	public @ResponseBody Object saveTipstaff(@RequestBody AppTipstaffAddresslist tipstaff)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(tipstaff==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		int ee=appTipstaffAddresslistService.insert(tipstaff);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
	@RequestMapping(value="/updateTipstaff")
	public @ResponseBody Object updateTipstaff(@RequestBody AppTipstaffAddresslist tipstaff, HttpServletRequest request)throws Exception{
		Map m=new HashMap();

		Integer tipstaffId = Integer.valueOf(request.getParameter("tipstaffId"));
		tipstaff.setTipstaffId(tipstaffId);

		/***条件异常判断***/
		if(tipstaff==null||(tipstaff!=null&&tipstaff.getTipstaffId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appTipstaffAddresslistService.updateByPrimaryKey(tipstaff);
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
	 * @param tipstaffId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delTipstaff")
	public @ResponseBody Object delTipstaff(int tipstaffId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(tipstaffId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appTipstaffAddresslistService.deleteByPrimaryKey(tipstaffId);
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