package com.sinosoft.wateradmin.app.controllers;

import com.sinosoft.wateradmin.app.service.IPatrolReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制层：巡查上报
 * <p>Restful形式实现(当前用的spring mvc的， 也可集成使用jesery的)</p>
 *@author lkj
 */
@Controller
@RequestMapping("/xczf")
public class Xczf_Report_Controller {
	@Autowired
	private IPatrolReportService patrolReportService;

	//--跳转到“巡查执法”-“现场执法”-“信息查询”页面
	@RequestMapping(value = "/jump_xczf_report_search")
	public String jumpXczfReportSearch(HttpServletRequest request) throws Exception {

		return "app/xczf_report_search";
	}
	//--跳转到“巡查执法”-“巡查台账”-“巡查查询”页面
	@RequestMapping(value = "/jump_xczf_ledger_search")
	public String jumpXczfLedgerSearch(HttpServletRequest request) throws Exception {

		return "app/xczf_ledger_search";
	}
}