package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.AppTipstaffAddresslist;

import java.util.List;

/**
 * 执法人员通讯录  Service
 * @author lvzhixue
 *
 */
public interface IAppTipstaffAddresslistService {

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
