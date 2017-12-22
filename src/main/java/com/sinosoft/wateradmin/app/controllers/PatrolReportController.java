package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.PatrolReport;
import com.sinosoft.wateradmin.app.service.IPatrolReportService;
import com.sinosoft.wateradmin.common.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：巡查上报
 * <p>Restful形式实现(当前用的spring mvc的， 也可集成使用jesery的)</p>
 *@author lkj
 */
@Controller
@RequestMapping("/app")
public class PatrolReportController {
	@Autowired
	private IPatrolReportService patrolReportService;

	/**
	 *获取巡查上报记录（查询记录）
	 * @param pr
	 * @return
	 */
	@RequestMapping(value="/getPatrolReportDatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody List<PatrolReport> getPatrolReportDatas(PatrolReport pr) throws Exception{
		List<PatrolReport>  ls=patrolReportService.selectDatas(pr);
		return ls;
	}

	/**
	 *获取巡查上报记录（查询记录）,getPatrolReportDatasForWeb
	 * 立案申请用
	 * @param pr
	 * @return
	 * @remark added by lvzhixue, 2017-12-13 11:33
	 */
	@RequestMapping(value = "/getPatrolReportDatasForWeb", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BasePage getPatrolReportDatasForWeb(@RequestParam(required = true, defaultValue = "1") Integer page,
									 @RequestParam(required = true, defaultValue = "10") Integer rows,
											   PatrolReport pr) throws Exception {
		PageHelper.startPage(page, rows);
		List<PatrolReport>  ls=patrolReportService.selectDatas(pr);
		BasePage<PatrolReport> pi = new BasePage<PatrolReport>(ls);
		return pi;
	}

	@RequestMapping(value="/getPrSomeData",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody List<PatrolReport> getPrSomeData(int limitNum) throws Exception{
		if(limitNum<=0)
			limitNum=8;//默认8条

		List<PatrolReport>  ls=patrolReportService.selectSome(limitNum);
		return ls;
	}
	/**
	 * 保存巡查上报记录
	 * @param patrolReport
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/savePr",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object savePr(@RequestBody PatrolReport patrolReport)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(patrolReport==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}else if(patrolReport.getPlId()<=0){
			m.put("tag","false");
			m.put("message","未获取外键参数值");
			return m;
		}
		int ee=patrolReportService.insert(patrolReport);
		if(ee>0){
			m.put("tag","true");
			m.put("message","添加成功");
			m.put("prId",patrolReport.getPrId());
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
	@RequestMapping(value="/updatePr",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object updatePr(@RequestBody PatrolReport patrolReport)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(patrolReport==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}else if(patrolReport.getPrId()<=0){
			m.put("tag","false");
			m.put("message","未获取主键参数值");
			return m;
		}else if(patrolReport.getPlId()<=0){//属于某个巡查台账记录
			m.put("tag","false");
			m.put("message","未获取外键主键参数值");
			return m;
		}
		int ee=patrolReportService.updateByPrimaryKey(patrolReport);
		if(ee>0){
			m.put("tag","true");
			m.put("message","更新成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
}