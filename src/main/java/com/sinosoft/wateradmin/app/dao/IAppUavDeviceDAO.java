package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.AppUavDevice;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 无人机_dao
 *
 * @author lvzhixue.
 * @create 2017/11/24 16:32
 */
@MyBatisDao
public interface IAppUavDeviceDAO {
    int deleteByPrimaryKey(Integer uavId);

    int insert(AppUavDevice record);

    int insertSelective(AppUavDevice record);

    AppUavDevice selectByPrimaryKey(Integer uavId);

    int updateByPrimaryKeySelective(AppUavDevice record);

    int updateByPrimaryKey(AppUavDevice record);

    List<AppUavDevice> selectDatas(AppUavDevice appUavDevice);
}