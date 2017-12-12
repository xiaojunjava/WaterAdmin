package com.sinosoft.wateradmin.app.controllers;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.AppPatrolLedger;
import com.sinosoft.wateradmin.app.service.IAppPatrolLedgerSerivice;
import com.sinosoft.wateradmin.common.BasePage;
import com.sinosoft.wateradmin.common.DateConvert;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 巡查台账  Controller
 *
 * @author hanjie.
 * @create 2017/11/3
 */
@Controller
@RequestMapping("/app")
public class AppPatrolLedgerController {
	private final static Logger logger = Logger.getLogger(AppPatrolLedgerController.class);

	@Autowired
	private IAppPatrolLedgerSerivice iAppPatrolLedgerSerivice;
	@Resource
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	public static String PREFIX_APPPATROLLEDGER = "apl:";

	/**
	 * 获取查询后的所有结果<p>add by lkj</p>
	 * @param pl
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSelectPLDatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	@ResponseBody
	public BasePage getSelectPLDatas(@RequestParam(required=true,defaultValue="1") Integer page,
												  @RequestParam(required=true,defaultValue="10") Integer rows,AppPatrolLedger pl)throws Exception {
		PageHelper.startPage(page, rows);
		List<AppPatrolLedger>  ls= iAppPatrolLedgerSerivice.selectDatas(pl);
		BasePage<AppPatrolLedger> pi=new BasePage<AppPatrolLedger>(ls);

		return pi;
	}
	/**
	 * 巡查登记  开始
	 *
	 *  客户端第一次请求将数据保存一份记录到数据库并获取主键，
	 *  然后根据主键生成key将记录保存至缓存并给给客户端返回主键值，完成巡查登记的功能。
	 * modified by lvzhixue 2017-11-10 13:39
	 * @param req
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDataBeginPatrol")
	@ResponseBody
	public String getDataBeginPatrol(HttpServletRequest req, HttpServletResponse response) throws Exception {
		//--用户记录号
		String userId = req.getParameter("userId");

		//--巡检人的名字
		String userName = req.getParameter("userName");

		//巡查记录号
		String plId = req.getParameter("plId");
		//巡查经度
		String longitude = req.getParameter("longitude");
		//巡查维度
		String latitude = req.getParameter("latitude");
		//巡查名称
		String plLedgerName = req.getParameter("plLedgerName");

		//调试
		logger.info("plId-----" + plId);
		logger.info("userId-----" + userId);
		logger.info("plLedgerName-----" + plLedgerName);
		logger.info("longitude-----" + longitude);
		logger.info("latitude-----" + latitude);
		logger.info("userName-----" + userName);

		//巡查开始第一次保存开始时间、用户获取该巡查的记录号
		AppPatrolLedger appPatrolLedger = new AppPatrolLedger();

		if (StringUtils.isEmpty(plId)) {
			appPatrolLedger.setPlLedgerName(plLedgerName);
			appPatrolLedger.setPlBeginTime(new Date());
			appPatrolLedger.setUserId(Integer.parseInt(userId));
			appPatrolLedger.setUserName(userName);
			//保存第一次数据至数据库，并获取主键值
			this.iAppPatrolLedgerSerivice.insert(appPatrolLedger);
		} else {
			appPatrolLedger.setPlId(Integer.parseInt(plId));
		}

		//保存至缓存
		String key = PREFIX_APPPATROLLEDGER + appPatrolLedger.getPlId();
		BoundHashOperations<String, String, Map<String, String>> ops = redisTemplate.boundHashOps(key);
		Map<String, String> data = new HashMap<String, String>();
		List<Map<String, String>> entries = ops.values();

		data.put("userId", userId);
		data.put("userName", userName);
		data.put("longitude", longitude);
		data.put("latitude", latitude);
		data.put("plBeginTime", DateConvert.DateToStr(new Date()));
		entries.add(data);
		ops.put(entries.toString(), data);

		return String.valueOf(appPatrolLedger.getPlId());
	}

	/**
	 * 巡查登记  结束
	 *
	 *   客户端将信息发送至后台，根据key去缓存中拿数据，并将数据进行处理
	 *   然后更新至数据库，完成巡查结束的功能。
	 *
	 * @param req
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDataEndPatrol")
	@ResponseBody
	public String getDataEndPatrol(HttpServletRequest req, HttpServletResponse response) throws Exception {

		//巡查记录号
		String plId = req.getParameter("plId");

		//巡查结果
		String plResult = req.getParameter("plResult");

		//调试
		logger.info("plId-----"+plId);
		logger.info("plResult-----"+plResult);

		AppPatrolLedger appPatrolLedger = new AppPatrolLedger();

		//--获取缓存中保存的gps数据，并保存到数据库中
		String key = PREFIX_APPPATROLLEDGER + plId;
		BoundHashOperations<String, String, Map<String, String>> ops = redisTemplate.boundHashOps(key);
		List<Map<String, String>> entries = ops.values();
		logger.info("entries-----"+entries.toString());

		//根据key获取缓存数据List<Map<String, String>> 并更新到数据库中
		if(null != entries && !entries.isEmpty()){
			logger.info("plTarjectory-----"+entries.toString());
			String str = "";
			for (int i=0; i<entries.size(); i++) {
				Map<String, String> m = entries.get(i);
				if (i==entries.size()-1) {
					str = str + "[" + m.get("latitude") + "," + m.get("longitude") + "]";
				}
				else {
					str = str + "[" + m.get("latitude") + "," + m.get("longitude") + "]" + ",";
				}
			}

			str = "[[" + str + "]]";
			str ="{"
				+"\"paths\": "+str +","
				+"\"spatialReference\":{\"wkid\":4326}"
                +"}";

			logger.info("坐标集：-----"+str);
			appPatrolLedger.setPlTarjectory(str);
		}

		appPatrolLedger.setPlId(Integer.parseInt(plId));
		appPatrolLedger.setPlEndTime(new Date());
		appPatrolLedger.setPlResult(plResult);

		if (null != appPatrolLedger) {
			this.iAppPatrolLedgerSerivice.updateByPrimaryKey(appPatrolLedger);
		}

		return "ok";
	}

	/**
	 * 轨迹回放  列表页
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping("getDataPlayback")
	@ResponseBody
	public List<AppPatrolLedger> getDataPlayback(HttpServletRequest req) throws Exception {
		String userId = req.getParameter("userId");
		List<AppPatrolLedger> appPatrolLedgerList = this.iAppPatrolLedgerSerivice.selectByUserId(Integer.parseInt(userId));
		return appPatrolLedgerList;
	}

	/**
	 * 轨迹回放  详情页
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping("getDataPlaybackDetail")
	@ResponseBody
	public AppPatrolLedger getDataPlaybackDetail(HttpServletRequest req) throws Exception {
		String plId = req.getParameter("plId");
		AppPatrolLedger appPatrolLedgerBean = this.iAppPatrolLedgerSerivice.selectByPrimaryKey(Integer.parseInt(plId));
		return appPatrolLedgerBean;
	}

	/**
	 * 获取当前所有正在巡检的人员的实时位置
	 * 格式：
		 {
			 total:4,
			 items:[{userId:1,userName:"乌鲁木齐",longitude:118.23520,latitude:25.49392,plBeginTime:"2017-11-15 20:10:10"},
	 				{userId:1,userName:"乌鲁木齐",longitude:118.23520,latitude:25.49392,plBeginTime:"2017-11-15 20:10:10"}]
		 }
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getRealTimeLocationOfPerson")
	@ResponseBody
	public String getRealTimeLocationOfPerson(HttpServletRequest request,HttpServletResponse response){
		int count = 0;
		List<String> keyStrList = new ArrayList<>();
		String zPoints = "";

		//--1、获取现在所有正在巡检人员的台账记录
		List<AppPatrolLedger> appPatrolLedgerList = iAppPatrolLedgerSerivice.getPatrolNow();

		//--2、遍历台账记录，获取key保存到list中
		for (int i = 0; i < appPatrolLedgerList.size(); i++) {
			AppPatrolLedger appPatrolLedger = appPatrolLedgerList.get(i);
			String key = PREFIX_APPPATROLLEDGER + appPatrolLedger.getPlId();
			keyStrList.add(key);
		}

		//--3、从缓存中读取每个正在巡检的人的最新位置信息
		//--3.1遍历keyStrList
		for (int i = 0; i < keyStrList.size(); i++) {

			//--获取缓存中保存的gps最新一个坐标数据
			String key = keyStrList.get(i);//--获取每一个人的标识
			BoundHashOperations<String, String, Map<String, String>> ops = redisTemplate.boundHashOps(key);
			try{
				List<Map<String, String>> entries = ops.values();//--获取坐标数据列表
				if (entries.size()>0) {
					count++;
					if (i == keyStrList.size() - 1) {//--最后一个
						zPoints = zPoints + "{userId:"+entries.get(entries.size()-1).get("userId")+",userName:"+entries.get(entries.size()-1).get("userName")+",longitude:"+entries.get(entries.size()-1).get("longitude")+",latitude:"+entries.get(entries.size()-1).get("latitude")+",plBeginTime:"+entries.get(entries.size()-1).get("plBeginTime")+"}";

					} else {//--不是最后一个
						zPoints = zPoints + "{userId:"+entries.get(entries.size()-1).get("userId")+",userName:"+entries.get(entries.size()-1).get("userName")+",longitude:"+entries.get(entries.size()-1).get("longitude")+",latitude:"+entries.get(entries.size()-1).get("latitude")+",plBeginTime:"+entries.get(entries.size()-1).get("plBeginTime")+"}" + ",";
					}
				}
			}catch (NoSuchElementException e){
				logger.info(key+",该巡检记录坐标数据未缓存！");
			}

		}

		//--最终字符串
		zPoints = "{" +
				"total:"+count+"," +
				"items:["+zPoints+"]" +
				"}";
		logger.info(zPoints);
		return zPoints;
	}


}