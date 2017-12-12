package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.AppShipmasterAddresslist;

import java.util.List;

/**
 * 采沙/运沙船主通讯录  Service
 * @author lvzhixue
 *
 */
public interface IAppShipmasterAddresslistService {

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
