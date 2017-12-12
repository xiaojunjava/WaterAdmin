package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.AppLawenforceRecorderDevice;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 执法记录仪_dao
 *
 * @author lvzhixue.
 * @create 2017/11/24 16:32
 */
@MyBatisDao
public interface IAppLawenforceRecorderDeviceDAO {
    int deleteByPrimaryKey(Integer alrId);

    int insert(AppLawenforceRecorderDevice record);

    int insertSelective(AppLawenforceRecorderDevice record);

    AppLawenforceRecorderDevice selectByPrimaryKey(Integer alrId);

    int updateByPrimaryKeySelective(AppLawenforceRecorderDevice record);

    int updateByPrimaryKey(AppLawenforceRecorderDevice record);

    List<AppLawenforceRecorderDevice> selectDatas(AppLawenforceRecorderDevice appLawenforceRecorderDevice);
}