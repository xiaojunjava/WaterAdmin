package com.sinosoft.wateradmin.cmd.service;

import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;

import java.util.List;

/**
 * 车船实时位置监控记录  Service
 * @author lkj
 */
public interface IShipMonitorService {

	/**
	 * 根据主键查数据
	 * @param csmId
	 * @return
	 */
	public ShipMonitor selectByPrimaryKey(int csmId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param sm
	 * @return
	 * @throws Exception
	 */
	public List<ShipMonitor> selectDatas(ShipMonitor sm) throws Exception;

	/**
	 * 自定义条件查询
	 * @param planSql
	 * @return
	 * @throws Exception
	 */
	public List<ShipMonitor> selectPlanDatas(String planSql) throws Exception;
	/**
	 * 根据主键删除1条数据
	 * @param csmId
	 * @return
	 */
	public int deleteByPrimaryKey(int csmId) throws Exception;

	/**
	 *新增记录
	 * @param sm
	 * @return
	 */
	public int insert(ShipMonitor sm) throws Exception;

	/**
	 * 更新记录
	 * @param sm
	 * @return
	 */
	public int update(ShipMonitor sm) throws Exception;

}
