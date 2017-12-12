package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppVideoMonitorDevice;

import com.sinosoft.wateradmin.app.dao.IAppVideoMonitorDeviceDAO;
import com.sinosoft.wateradmin.app.service.IAppVideoMonitorDeviceService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频监控设备  Service实现类
 * @author lvzhixue
 *
 */
@Service
@Transactional
public class AppVideoMonitorDeviceServiceImpl implements IAppVideoMonitorDeviceService{
	
	@Resource
	private IAppVideoMonitorDeviceDAO appVideoMonitorDeviceDAO;
	/**
	 * 根据主键查数据
	 * @param vmId
	 * @return
	 */
	public AppVideoMonitorDevice selectByPrimaryKey(int vmId) throws Exception{
		return appVideoMonitorDeviceDAO.selectByPrimaryKey(vmId);
	}

	/**
	 * 根据条件查数据
	 *
	 * @param appVideoMonitorDevice
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AppVideoMonitorDevice> selectDatas(AppVideoMonitorDevice appVideoMonitorDevice) throws Exception {
		return appVideoMonitorDeviceDAO.selectDatas(appVideoMonitorDevice);
	}


	/**
	 * 根据主键删除1条数据
	 * @param vmId
	 * @return
	 */
	public int deleteByPrimaryKey(int vmId) throws Exception{
		return appVideoMonitorDeviceDAO.deleteByPrimaryKey(vmId);
	}

	/**
	 * 新增记录
	 *
	 * @param appVideoMonitorDevice
	 * @return
	 */
	@Override
	public int insert(AppVideoMonitorDevice appVideoMonitorDevice) throws Exception {
		return appVideoMonitorDeviceDAO.insert(appVideoMonitorDevice);
	}

	/**
	 * 更新记录
	 *
	 * @param appVideoMonitorDevice
	 * @return
	 */
	@Override
	public int update(AppVideoMonitorDevice appVideoMonitorDevice) throws Exception {
		return appVideoMonitorDeviceDAO.updateByPrimaryKeySelective(appVideoMonitorDevice);
	}





}
