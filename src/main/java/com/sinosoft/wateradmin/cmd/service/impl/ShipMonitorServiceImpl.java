package com.sinosoft.wateradmin.cmd.service.impl;

import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;
import com.sinosoft.wateradmin.cmd.dao.IShipMonitorDAO;
import com.sinosoft.wateradmin.cmd.service.IShipMonitorService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
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
	public List<ShipMonitor> selectPlanDatas(String playSql) throws Exception{
		return shipMonitorDAO.selectPlanDatas(playSql);
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

}
