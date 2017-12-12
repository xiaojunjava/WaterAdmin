package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.AppLawenforceRecorderDevice;
import com.sinosoft.wateradmin.app.service.IAppLawenforceRecorderDeviceService;
import com.sinosoft.wateradmin.common.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：执法记录仪
 * @author lvzhixue
 * @date 2017-11-27 11:33
 */
@Controller
@RequestMapping("/recordDevice")
public class AppLawenforceRecorderDeviceController {

	@Autowired
	private IAppLawenforceRecorderDeviceService appLawenforceRecorderDeviceService;

	//--跳转到执法记录仪管理页面
	@RequestMapping(value = "/goToRecordPage")
	public String goToRecordPage()throws Exception{
		return "app/appLawenforceRecorderDevice";
	}

	/**
	 *查询执法记录仪信息
	 * @param appLawenforceRecorderDevice
	 * @return
	 */
	@RequestMapping(value="/getRecordDeviceData")
	public @ResponseBody BasePage getRecorderDeviceData(@RequestParam(required=true,defaultValue="1") Integer page,
											  @RequestParam(required=true,defaultValue="10") Integer rows,
											 AppLawenforceRecorderDevice appLawenforceRecorderDevice) throws Exception{
		PageHelper.startPage(page, rows);
		List<AppLawenforceRecorderDevice>  appRecorderDeviceList = this.appLawenforceRecorderDeviceService.selectDatas(appLawenforceRecorderDevice);

		BasePage<AppLawenforceRecorderDevice> pi = new BasePage<AppLawenforceRecorderDevice>(appRecorderDeviceList);


		return pi;
	}

	/**
	 * 新增1条执法记录仪信息
	 * @param recorderDevice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveRecorder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object saveRecorder(@RequestBody AppLawenforceRecorderDevice recorderDevice)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(recorderDevice==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		int ee = this.appLawenforceRecorderDeviceService.insert(recorderDevice);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
	@RequestMapping(value="/updateRecorder")
	public @ResponseBody Object updateRecorder(@RequestBody AppLawenforceRecorderDevice recorderDevice, HttpServletRequest request)throws Exception{
		Map m=new HashMap();

		Integer alrId = Integer.valueOf(request.getParameter("alrId"));

		recorderDevice.setAlrId(alrId);

		/***条件异常判断***/
		if(recorderDevice==null||(recorderDevice!=null&&recorderDevice.getAlrId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee =  this.appLawenforceRecorderDeviceService.updateByPrimaryKey(recorderDevice);
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
	 * @param alrId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delRecorder")
	public @ResponseBody Object delRecorder(int alrId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(alrId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee =  this.appLawenforceRecorderDeviceService.deleteByPrimaryKey(alrId);
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