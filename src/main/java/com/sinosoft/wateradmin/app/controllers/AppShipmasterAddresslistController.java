package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.AppShipmasterAddresslist;
import com.sinosoft.wateradmin.app.service.IAppShipmasterAddresslistService;
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
 * 控制层：采沙/运沙船主通讯录
 * @author lvzhixue
 * @date 2017-11-24 16:44
 */
@Controller
@RequestMapping("/shipmaster")
public class AppShipmasterAddresslistController {

	@Autowired
	private IAppShipmasterAddresslistService appShipmasterAddresslistService;

	//--跳转到采沙/运沙船主通讯录管理页面
	@RequestMapping(value = "/goToShipmasterPageCai")
	public String goToShipmasterPageCai()throws Exception{
		return "app/appShipmasterAddresslist_cai";
	}

	//--跳转到采沙/运沙船主通讯录管理页面
	@RequestMapping(value = "/goToShipmasterPageYun")
	public String goToShipmasterPageYun()throws Exception{
		return "app/appShipmasterAddresslist_yun";
	}

	/**
	 *查询采沙/运沙船主信息
	 * @param shipmaster
	 * @return
	 */
	@RequestMapping(value="/getShipmasterData")
	public @ResponseBody BasePage getShipmasterData(@RequestParam(required=true,defaultValue="1") Integer page,
													@RequestParam(required=true,defaultValue="10") Integer rows,
													AppShipmasterAddresslist shipmaster,HttpServletRequest request) throws Exception{
		PageHelper.startPage(page, rows);

		String asaType = request.getParameter("asaType");
		shipmaster.setAsaType(asaType);

		List<AppShipmasterAddresslist>  appShipmasterList = this.appShipmasterAddresslistService.selectDatas(shipmaster);

		BasePage<AppShipmasterAddresslist> pi = new BasePage<AppShipmasterAddresslist>(appShipmasterList);
		return pi;
	}

	/**
	 * 新增1条采沙/运沙船主信息
	 * @param shipmaster
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveShipmaster")
	public @ResponseBody Object saveShipmaster(@RequestBody AppShipmasterAddresslist shipmaster,HttpServletRequest request)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(shipmaster==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}

		String asaType = request.getParameter("asaType");
		shipmaster.setAsaType(asaType);

		int ee=appShipmasterAddresslistService.insert(shipmaster);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
	@RequestMapping(value="/updateShipmaster")
	public @ResponseBody Object updateShipmaster(@RequestBody AppShipmasterAddresslist shipmaster, HttpServletRequest request)throws Exception{
		Map m=new HashMap();

		Integer asaId = Integer.valueOf(request.getParameter("asaId"));
		String asaType = request.getParameter("asaType");
		shipmaster.setAsaType(asaType);
		shipmaster.setAsaId(asaId);

		/***条件异常判断***/
		if(shipmaster==null||(shipmaster!=null&&shipmaster.getAsaId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appShipmasterAddresslistService.updateByPrimaryKey(shipmaster);
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
	 * @param asaId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delShipmaster")
	public @ResponseBody Object delShipmaster(int asaId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(asaId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appShipmasterAddresslistService.deleteByPrimaryKey(asaId);
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