package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.AppVideoMonitorDevice;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 视频监控设备_dao
 *
 * @author lvzhixue.
 * @create 2017/11/24 16:32
 */
@MyBatisDao
public interface IAppVideoMonitorDeviceDAO {
    int deleteByPrimaryKey(Integer vmId);

    int insert(AppVideoMonitorDevice record);

    int insertSelective(AppVideoMonitorDevice record);

    AppVideoMonitorDevice selectByPrimaryKey(Integer vmId);

    int updateByPrimaryKeySelective(AppVideoMonitorDevice record);

    int updateByPrimaryKey(AppVideoMonitorDevice record);

    List<AppVideoMonitorDevice> selectDatas(AppVideoMonitorDevice appVideoMonitorDevice);
}