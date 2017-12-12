package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.AppLawenforceRecorderDevice;

import java.util.List;

/**
 * 无人机设备  Service
 * @author lvzhixue
 *
 */
public interface IAppLawenforceRecorderDeviceService {

	int deleteByPrimaryKey(Integer alrId);

	int insert(AppLawenforceRecorderDevice record);

	int insertSelective(AppLawenforceRecorderDevice record);

	AppLawenforceRecorderDevice selectByPrimaryKey(Integer alrId);

	int updateByPrimaryKeySelective(AppLawenforceRecorderDevice record);

	int updateByPrimaryKey(AppLawenforceRecorderDevice record);

	List<AppLawenforceRecorderDevice> selectDatas(AppLawenforceRecorderDevice appLawenforceRecorderDevice);
}
