package com.sinosoft.wateradmin.app.service;


import com.sinosoft.wateradmin.app.bean.AppVideoMonitorDevice;

import java.util.List;

/**
 * 视频监控设备  Service
 * @author lvzhixue
 *
 */
public interface IAppVideoMonitorDeviceService {

	/**
	 * 根据主键查数据
	 * @param vmId
	 * @return
	 */
	public AppVideoMonitorDevice selectByPrimaryKey(int vmId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param appVideoMonitorDevice
	 * @return
	 * @throws Exception
	 */
	public List<AppVideoMonitorDevice> selectDatas(AppVideoMonitorDevice appVideoMonitorDevice) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param vmId
	 * @return
	 */
	public int deleteByPrimaryKey(int vmId) throws Exception;

	/**
	 *新增记录
	 * @param appVideoMonitorDevice
	 * @return
	 */
	public int insert(AppVideoMonitorDevice appVideoMonitorDevice) throws Exception;

	/**
	 * 更新记录
	 * @param appVideoMonitorDevice
	 * @return
	 */
	public int update(AppVideoMonitorDevice appVideoMonitorDevice) throws Exception;

}
