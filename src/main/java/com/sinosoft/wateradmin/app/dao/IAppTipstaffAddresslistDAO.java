package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.AppTipstaffAddresslist;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 执法人员通讯录
 * added by lvzhixue 2017-11-06 11:50
 */
@MyBatisDao
public interface IAppTipstaffAddresslistDAO {
    int deleteByPrimaryKey(Integer tipstaffId);

    int insert(AppTipstaffAddresslist record);

    int insertSelective(AppTipstaffAddresslist record);

    AppTipstaffAddresslist selectByPrimaryKey(Integer tipstaffId);

    int updateByPrimaryKeySelective(AppTipstaffAddresslist record);

    int updateByPrimaryKey(AppTipstaffAddresslist record);

    //--执法人员通讯录列表
    List<AppTipstaffAddresslist> getAppTipstaffAddresslist();

    List<AppTipstaffAddresslist> selectDatas(AppTipstaffAddresslist appTipstaffAddresslist);
}