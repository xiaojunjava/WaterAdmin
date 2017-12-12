package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.AppUavDevice;

import java.util.List;

/**
 * 无人机设备  Service
 * @author lvzhixue
 *
 */
public interface IAppUavDeviceService {

	/**
	 * 根据主键查数据
	 * @param uavId
	 * @return
	 */
	public AppUavDevice selectByPrimaryKey(int uavId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param appUavDevice
	 * @return
	 * @throws Exception
	 */
	public List<AppUavDevice> selectDatas(AppUavDevice appUavDevice) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param uavId
	 * @return
	 */
	public int deleteByPrimaryKey(int uavId) throws Exception;

	/**
	 *新增记录
	 * @param appUavDevice
	 * @return
	 */
	public int insert(AppUavDevice appUavDevice) throws Exception;

	/**
	 * 更新记录
	 * @param appUavDevice
	 * @return
	 */
	public int update(AppUavDevice appUavDevice) throws Exception;

}
