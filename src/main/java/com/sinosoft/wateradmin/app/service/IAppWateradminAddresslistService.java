package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.AppWateradminAddresslist;

import java.util.List;

/**
 * 水政单位通讯录  Service
 * @author lvzhixue
 *
 */
public interface IAppWateradminAddresslistService {

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
