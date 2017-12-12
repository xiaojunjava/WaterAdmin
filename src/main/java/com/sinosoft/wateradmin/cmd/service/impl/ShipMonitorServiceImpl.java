package com.sinosoft.wateradmin.cmd.service.impl;

import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;
import com.sinosoft.wateradmin.cmd.dao.IShipMonitorDAO;
import com.sinosoft.wateradmin.cmd.service.IShipMonitorService;
import com.sinosoft.wateradmin.common.CommonUtil;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  路线规划  Service实现类
 * @author lkj
 *
 */
@Service
@Transactional
public class ShipMonitorServiceImpl implements IShipMonitorService {
	@Resource
	private IShipMonitorDAO shipMonitorDAO;
	@Resource
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	@Override
	public ShipMonitor selectByPrimaryKey(int csmId) throws Exception{
		return shipMonitorDAO.selectByPrimaryKey(csmId);
	}
	@Override
	public List<ShipMonitor> selectDatas(ShipMonitor sm) throws Exception{
		return shipMonitorDAO.selectDatas(sm);
	}
	@Override
	public int deleteByPrimaryKey(int csmId) throws Exception{
		return shipMonitorDAO.deleteByPrimaryKey(csmId);
	}
	@Override
	public int insert(ShipMonitor sm) throws Exception{
		return shipMonitorDAO.insert(sm);
	}
	@Override
	public int update(ShipMonitor sm) throws Exception{
		return shipMonitorDAO.update(sm);
	}
	//gis坐标处理
	@Override
	public void doGisRequest() throws  Exception{
	    //获取监控对象列表
		//每个对象的gis坐标获取----->调用服务//todo
		String longitude="";
		String latitude="";
		//当前对象坐标是否为新轨迹
		String key="";
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
	//取实时坐标
	public Map<String,String> getNowGis(String saId){
		String key="";//根据saId获取key
		BoundHashOperations<String, String, Map<String, String>> ops =redisTemplate.boundHashOps(key);
		List<Map<String, String>> HK = ops.values();
		Map<String,String> m=null;
		if(HK!=null&&!HK.isEmpty()){
			m=HK.get(HK.size()-1);
		}
		return m;
	}
	//取轨迹
	public List<Map<String,String>> getGisList(String saId){
		String key="";//根据saId获取key
		//时间段默认当天的00点到当前的记录集合+缓存中的//todo 封装处理
		//返回list坐标集
		return null;
	}
}
