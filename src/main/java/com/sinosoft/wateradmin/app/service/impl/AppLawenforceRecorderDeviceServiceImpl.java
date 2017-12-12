package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppLawenforceRecorderDevice;
import com.sinosoft.wateradmin.app.dao.IAppLawenforceRecorderDeviceDAO;
import com.sinosoft.wateradmin.app.service.IAppLawenforceRecorderDeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 无人机设备  Service实现类
 * @author lvzhixue
 *
 */
@Service
@Transactional
public class AppLawenforceRecorderDeviceServiceImpl implements IAppLawenforceRecorderDeviceService{

	@Resource
	private IAppLawenforceRecorderDeviceDAO appLawenforceRecorderDeviceDAO;


	@Override
	public int deleteByPrimaryKey(Integer alrId) {
		return appLawenforceRecorderDeviceDAO.deleteByPrimaryKey(alrId);
	}

	@Override
	public int insert(AppLawenforceRecorderDevice record) {
		return appLawenforceRecorderDeviceDAO.insert(record);
	}

	@Override
	public int insertSelective(AppLawenforceRecorderDevice record) {
		return appLawenforceRecorderDeviceDAO.insertSelective(record);
	}

	@Override
	public AppLawenforceRecorderDevice selectByPrimaryKey(Integer alrId) {
		return appLawenforceRecorderDeviceDAO.selectByPrimaryKey(alrId);
	}

	@Override
	public int updateByPrimaryKeySelective(AppLawenforceRecorderDevice record) {
		return appLawenforceRecorderDeviceDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AppLawenforceRecorderDevice record) {
		return appLawenforceRecorderDeviceDAO.updateByPrimaryKey(record);
	}

	@Override
	public List<AppLawenforceRecorderDevice> selectDatas(AppLawenforceRecorderDevice appLawenforceRecorderDevice) {
		return appLawenforceRecorderDeviceDAO.selectDatas(appLawenforceRecorderDevice);
	}
}
