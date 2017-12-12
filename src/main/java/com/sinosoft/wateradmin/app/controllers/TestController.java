package com.sinosoft.wateradmin.app.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sinosoft.wateradmin.app.bean.TestBean;
import com.sinosoft.wateradmin.app.service.ITestService;
import com.sinosoft.wateradmin.cmd.service.IShipMonitorService;
import com.sinosoft.wateradmin.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务模型数据控制层
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private ITestService testService;
	@Resource
	private IShipMonitorService shipMonitorService;
	@Resource
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	@RequestMapping(value="/goToTest")
	public String getModelById(HttpServletRequest req){
		return "test";
	}

	@RequestMapping("getTestData")
	@ResponseBody
	public TestBean getTestData(HttpServletRequest req) {
		String id = req.getParameter("businessModelManageId");
		req.setAttribute("businessModelManageId", id);
		TestBean tb=testService.tetsQuery("1");
		return tb;
	}
	@RequestMapping("testReidsAdd")
	@ResponseBody
	public Map<String, String> testReidsAdd() throws Exception{
		BoundHashOperations<String, String, Map<String, String>> ops =redisTemplate.boundHashOps("111");
		List<Map<String, String>> HK = ops.values();
		Map<String, String> data = new HashMap<String, String>();
		data.put("longitude", "经度");
		data.put("latitude", "纬度");
		data.put("nowTime", CommonUtil.getDateTimeStr(CommonUtil.getDateTimeFmt(),new Date()));
		HK.add(data);
		ops.put(HK.toString(),data);

		return data;
	}
	@RequestMapping("testReidsGet")
	@ResponseBody
	public Map<String, String> testReidsGet() throws Exception{
		BoundHashOperations<String, String, Map<String, String>> ops =redisTemplate.boundHashOps("111");
		List<Map<String, String>> HK = ops.values();
		Map<String,String> m= new HashMap<String, String>();;
		if(HK!=null&&!HK.isEmpty()&&HK.size()>0){
			m=HK.get(HK.size()-1);
		}
		return m;
	}
	@RequestMapping("testReidsDel")
	@ResponseBody
	public Map<String, String> testReidsDel() throws Exception{
		redisTemplate.delete("111");
		Map<String, String> m = new HashMap<String, String>();
		m.put("tag", "删除成功");
		return m;
	}

}