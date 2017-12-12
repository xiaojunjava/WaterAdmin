package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.AppVideoMonitorDevice;
import com.sinosoft.wateradmin.app.service.IAppVideoMonitorDeviceService;
import com.sinosoft.wateradmin.common.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：视频监控设备
 * @author lvzhixue
 * @date 2017-11-27 11:29
 */
@Controller
@RequestMapping("/videoDevice")
public class AppVideoMonitorDeviceController {

	@Autowired
	private IAppVideoMonitorDeviceService appVideoMonitorDeviceService;

	//--跳转到视频监控设备管理页面
	@RequestMapping(value = "/goToVideoPage")
	public String goToVideoPage()throws Exception{
		return "app/appVideoMonitorDevice";
	}

	/**
	 *查询视频设备信息
	 * @param appVideoMonitorDevice
	 * @return
	 */
	@RequestMapping(value="/getVideoDeviceData",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody BasePage getVideoDeviceData(@RequestParam(required=true,defaultValue="1") Integer page,
											  @RequestParam(required=true,defaultValue="10") Integer rows,
											 AppVideoMonitorDevice appVideoMonitorDevice) throws Exception{
		PageHelper.startPage(page, rows);
		List<AppVideoMonitorDevice>  appVideoMonitorDeviceList = this.appVideoMonitorDeviceService.selectDatas(appVideoMonitorDevice);

		BasePage<AppVideoMonitorDevice> pi = new BasePage<AppVideoMonitorDevice>(appVideoMonitorDeviceList);


		return pi;
	}

	/**
	 * 新增1条视频设备信息
	 * @param appVideoMonitorDevice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveVideoMonitor",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object saveVideoMonitor(@RequestBody AppVideoMonitorDevice appVideoMonitorDevice)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(appVideoMonitorDevice==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		int ee=appVideoMonitorDeviceService.insert(appVideoMonitorDevice);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}

	@RequestMapping(value="/updateVideoMonitor",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object updateVideoMonitor(@RequestBody AppVideoMonitorDevice appVideoMonitorDevice , HttpServletRequest request)throws Exception{
		Map m=new HashMap();
		Integer vmId = Integer.valueOf(request.getParameter("vmId"));
		appVideoMonitorDevice.setVmId(vmId);

		/***条件异常判断***/
		if(appVideoMonitorDevice==null||(appVideoMonitorDevice!=null&&appVideoMonitorDevice.getVmId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appVideoMonitorDeviceService.update(appVideoMonitorDevice);
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
	 * @param vmId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delVideoMonitor",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object delVideoMonitor(int vmId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(vmId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=appVideoMonitorDeviceService.deleteByPrimaryKey(vmId);
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