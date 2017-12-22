package com.sinosoft.wateradmin.cmd.service.impl;

import com.sinosoft.wateradmin.app.bean.AppPatrolLedger;
import com.sinosoft.wateradmin.app.controllers.AppPatrolLedgerController;
import com.sinosoft.wateradmin.app.service.IAppPatrolLedgerSerivice;
import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;
import com.sinosoft.wateradmin.cmd.service.IGisService;
import com.sinosoft.wateradmin.cmd.service.IShipMonitorService;
import com.sinosoft.wateradmin.common.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Gis  Service实现类
 * @author lkj
 *
 */
@Service
@Transactional
public class GisServiceImpl implements IGisService {
	private final static Logger logger = Logger.getLogger(GisServiceImpl.class);
	@Resource
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	@Autowired
	private IAppPatrolLedgerSerivice appPatrolLedgerSerivice;
	@Autowired
	private IShipMonitorService shipMonitorService;
	public static final String PREFIX_SHIP_MONITOR="sa:";
	//获取所有正在执法的人的实时坐标
	public Map getNowPeopleGis() throws Exception{
		List<String> keyStrList = new ArrayList<>();
		String zPoints = "";

		//--1、获取现在所有正在巡检人员的台账记录
		List<AppPatrolLedger> appPatrolLedgerList = appPatrolLedgerSerivice.getPatrolNow();

		//--2、遍历台账记录，获取key保存到list中
		for (int i = 0; i < appPatrolLedgerList.size(); i++) {
			AppPatrolLedger appPatrolLedger = appPatrolLedgerList.get(i);
			String key = AppPatrolLedgerController.PREFIX_APPPATROLLEDGER + appPatrolLedger.getPlId();
			keyStrList.add(key);
		}

		//--3、从缓存中读取每个正在巡检的人的最新位置信息
		//--3.1遍历keyStrList
		int count = 0;
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
		Map m_return=new HashMap();
		m_return.put("total",count);
		m_return.put("items",zPoints);

		logger.info(zPoints);

		return m_return;
	}
	//获取所有目标实时移动中的坐标（车/船）
	public Map getNowSaGis(String saType) throws Exception{
		//--1、获取现在所有正在巡检人员的台账记录
		List<ShipMonitor> saList = shipMonitorService.selectPlanDatas(" and ( a.SM_ENDTIME is null or a.SM_ENDTIME ='') and b.sa_type='"+saType+"' ");
		//--2、遍历台账记录 ,从缓存中读取每个正在采沙船、运沙船、执法船、执法车的最新位置信息
		int count = 0;
		List points=new ArrayList();
		for (int i = 0; i < saList.size(); i++) {
			ShipMonitor sa = saList.get(i);
			String key = PREFIX_SHIP_MONITOR + sa.getCsmId();

			BoundHashOperations<String, String, Map<String, String>> ops = redisTemplate.boundHashOps(key);
			try{
				List<Map<String, String>> entries = ops.values();//--获取坐标数据列表
				if (entries.size()>0) {
					count++;
					Map m=new HashMap();
					m.put("longitude",entries.get(entries.size()-1).get("longitude"));
					m.put("latitude",entries.get(entries.size()-1).get("latitude"));
					m.put("plBeginTime",entries.get(entries.size()-1).get("plBeginTime"));

					points.add(m);
				}
			}catch (NoSuchElementException e){
				logger.info(key+",该巡检记录坐标数据未缓存！");
			}
		}
		Map m_return=new HashMap();

		m_return.put("total",count);
		m_return.put("items",points);

		logger.info("得到所有移动目标中的车/船，数量："+count);

		return m_return;
	}

	//gis缓存的存储及序列化操作
	@Override
	public void doGisRequest() throws  Exception{
	    //获取监控对象列表（计划内的车船？）
		//每个对象的gis坐标获取----->调用服务//todo
		String longitude="";
		String latitude="";
		//当前对象坐标是否为新轨迹
		String key="";  //用 "sa:"+主键csmId？
		// 是的话，创建db新记录
		if(true){
			key="车船id(sa_id)"+"轨迹id";
			//保存旧的
		}else{
			//不是新轨迹，找原有key
			key="车船id(sa_id)"+"已存在轨迹id";//todo根据车船的id查未结束的记录（1条船当前轨迹只能是1条）
		}

		BoundHashOperations<String, String, Map<String, String>> ops =redisTemplate.boundHashOps(key);
		List<Map<String, String>> HK = ops.values();
		Map<String, String> data = new HashMap<String, String>();
		data.put("longitude", "经度");
		data.put("latitude", "纬度");
		data.put("nowTime", CommonUtil.getDateTimeStr(CommonUtil.getDateTimeFmt(),new Date()));
		HK.add(data);
		ops.put(HK.toString(),data);

	}

	//使用：取某车/某船的实时坐标
	public Map<String,String> getNowSaGis1(String csmId){
		String key=PREFIX_SHIP_MONITOR+csmId;//取key
		BoundHashOperations<String, String, Map<String, String>> ops =redisTemplate.boundHashOps(key);
		List<Map<String, String>> HK = ops.values();
		Map<String,String> m=null;
		if(HK!=null&&!HK.isEmpty()){
			m=HK.get(HK.size()-1);
		}
		return m;
	}
	//使用：取轨迹-车船
	public List<Map<String,String>> getSaGisList(String csmId) throws  Exception{
		String key=PREFIX_SHIP_MONITOR+csmId;//取key
		//首先，判断是否还未结束（结束的只在db中）
		List<ShipMonitor> smList = shipMonitorService.selectPlanDatas(" and ( a.SM_ENDTIME is null or a.SM_ENDTIME ='') and a.csm_id="+csmId);
		List ls=new ArrayList();
		if(smList!=null&&smList.size()==1){//未结束
			BoundHashOperations<String, String, Map<String, String>> ops =redisTemplate.boundHashOps(key);
			List<Map<String, String>> HK = ops.values();
			Map mm=null;
			for(Map m:HK){
				m.get("longitude");
				m.get("latitude");

				mm=new HashMap();
				//todo 重新封装各属性
				ls.add(mm);
			}
		}else{//已结束
			ShipMonitor sm=shipMonitorService.selectByPrimaryKey(Integer.parseInt(csmId));
			Map  mm=new HashMap();
			//todo 重新封装各属性(有可能封闭某时间段内的多条记录）
			//ls.add(mm);
		}

		//返回list坐标集
		return ls;
	}
}
