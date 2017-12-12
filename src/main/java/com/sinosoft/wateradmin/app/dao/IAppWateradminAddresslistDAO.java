package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.AppWateradminAddresslist;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 水政单位通讯录
 * added by lvzhixue 2017-11-06 11:50
 */
@MyBatisDao
public interface IAppWateradminAddresslistDAO {
    int deleteByPrimaryKey(Integer unitId);

    int insert(AppWateradminAddresslist record);

    int insertSelective(AppWateradminAddresslist record);

    AppWateradminAddresslist selectByPrimaryKey(Integer unitId);

    int updateByPrimaryKeySelective(AppWateradminAddresslist record);

    int updateByPrimaryKey(AppWateradminAddresslist record);

    //--水政单位通讯录列表
    List<AppWateradminAddresslist> getAppWateradminAddresslist();

    List<AppWateradminAddresslist> selectDatas(AppWateradminAddresslist appWateradminAddresslist);
}