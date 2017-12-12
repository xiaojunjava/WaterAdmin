package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.AppShipmasterAddresslist;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 采沙/运沙船主通讯录
 * added by lvzhixue 2017-11-06 11:50
 */

@MyBatisDao
public interface IAppShipmasterAddresslistDAO {
    int deleteByPrimaryKey(Integer asaId);

    int insert(AppShipmasterAddresslist record);

    int insertSelective(AppShipmasterAddresslist record);

    AppShipmasterAddresslist selectByPrimaryKey(Integer asaId);

    int updateByPrimaryKeySelective(AppShipmasterAddresslist record);

    int updateByPrimaryKey(AppShipmasterAddresslist record);

    //--采沙船主通讯录
    List<AppShipmasterAddresslist> getCaiShipmasterAddresslist();

    //--运沙船主通讯录
    List<AppShipmasterAddresslist> getYunShipmasterAddresslist();

    List<AppShipmasterAddresslist> selectDatas(AppShipmasterAddresslist shipmaster);
}