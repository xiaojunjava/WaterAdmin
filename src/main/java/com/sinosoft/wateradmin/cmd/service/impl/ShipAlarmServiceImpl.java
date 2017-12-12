package com.sinosoft.wateradmin.cmd.service.impl;

import com.sinosoft.wateradmin.cmd.bean.ShipAlarm;
import com.sinosoft.wateradmin.cmd.dao.IShipAlarmDAO;
import com.sinosoft.wateradmin.cmd.service.IShipAlarmService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  车船报警  Service实现类
 * @author lkj
 *
 */
@Service
@Transactional
public class ShipAlarmServiceImpl implements IShipAlarmService {
	@Resource
	private IShipAlarmDAO shipAlarmDAO;
	@Resource
	private RedisTemplate<String, Map<String, String>> redisTemplate;
	@Override
	public ShipAlarm selectByPrimaryKey(int csaId) throws Exception{
		return shipAlarmDAO.selectByPrimaryKey(csaId);
	}
	@Override
	public List<ShipAlarm> selectDatas(ShipAlarm shipAlarm) throws Exception{
		return shipAlarmDAO.selectDatas(shipAlarm);
	}
	@Override
	public int deleteByPrimaryKey(int csaId) throws Exception{
		return shipAlarmDAO.deleteByPrimaryKey(csaId);
	}
	@Override
	public int insert(ShipAlarm shipAlarm) throws Exception{
		return shipAlarmDAO.insert(shipAlarm);
	}
	@Override
	public int update(ShipAlarm shipAlarm) throws Exception{
		return shipAlarmDAO.update(shipAlarm);
	}
}
