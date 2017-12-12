package com.sinosoft.wateradmin.cmd.service;

import com.sinosoft.wateradmin.cmd.bean.ShipAlarm;
import java.util.List;

/**
 * 车船报警  Service接口
 * @author lkj
 */
public interface IShipAlarmService {

	/**
	 * 根据主键查数据
	 * @param csaId
	 * @return
	 */
	public ShipAlarm selectByPrimaryKey(int csaId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param shipAlarm
	 * @return
	 * @throws Exception
	 */
	public List<ShipAlarm> selectDatas(ShipAlarm shipAlarm) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param csaId
	 * @return
	 */
	public int deleteByPrimaryKey(int csaId) throws Exception;

	/**
	 *新增记录
	 * @param shipAlarm
	 * @return
	 */
	public int insert(ShipAlarm shipAlarm) throws Exception;

	/**
	 * 更新记录
	 * @param shipAlarm
	 * @return
	 */
	public int update(ShipAlarm shipAlarm) throws Exception;

}
