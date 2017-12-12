package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppUavDevice;
import com.sinosoft.wateradmin.app.dao.IAppUavDeviceDAO;
import com.sinosoft.wateradmin.app.service.IAppUavDeviceService;
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
public class AppUavDeviceServiceImpl implements IAppUavDeviceService{
	@Resource
	private IAppUavDeviceDAO appUavDeviceDAO;
	/**
	 * 根据主键查数据
	 * @param uavId
	 * @return
	 */
	public AppUavDevice selectByPrimaryKey(int uavId) throws Exception{
		return appUavDeviceDAO.selectByPrimaryKey(uavId);
	}

	/**
	 * 根据条件查数据
	 * @param appUavDevice
	 * @return
	 * @throws Exception
	 */
	public List<AppUavDevice> selectDatas(AppUavDevice appUavDevice) throws Exception{
		return appUavDeviceDAO.selectDatas(appUavDevice);
	}

	/**
	 * 根据主键删除1条数据
	 * @param uavId
	 * @return
	 */
	public int deleteByPrimaryKey(int uavId) throws Exception{
		return appUavDeviceDAO.deleteByPrimaryKey(uavId);
	}

	/**
	 *新增记录
	 * @param appUavDevice
	 * @return
	 */
	public int insert(AppUavDevice appUavDevice) throws Exception{
		return appUavDeviceDAO.insert(appUavDevice);
	}

	/**
	 * 更新记录
	 * @param appUavDevice
	 * @return
	 */
	public int update(AppUavDevice appUavDevice) throws Exception{
		return appUavDeviceDAO.updateByPrimaryKeySelective(appUavDevice);
	}

}
