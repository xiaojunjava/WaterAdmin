package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppWateradminAddresslist;
import com.sinosoft.wateradmin.app.dao.IAppWateradminAddresslistDAO;
import com.sinosoft.wateradmin.app.service.IAppWateradminAddresslistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *水政单位通讯录  Service实现类
 * @author lvzhixue
 *
 */
@Service
@Transactional
public class AppWateradminAddresslistServiceImpl implements IAppWateradminAddresslistService{

	@Resource
	private IAppWateradminAddresslistDAO appWateradminAddresslistDAO;

	@Override
	public int deleteByPrimaryKey(Integer unitId) {
		return appWateradminAddresslistDAO.deleteByPrimaryKey(unitId);
	}

	@Override
	public int insert(AppWateradminAddresslist record) {
		return appWateradminAddresslistDAO.insert(record);
	}

	@Override
	public int insertSelective(AppWateradminAddresslist record) {
		return appWateradminAddresslistDAO.insertSelective(record);
	}

	@Override
	public AppWateradminAddresslist selectByPrimaryKey(Integer unitId) {
		return appWateradminAddresslistDAO.selectByPrimaryKey(unitId);
	}

	@Override
	public int updateByPrimaryKeySelective(AppWateradminAddresslist record) {
		return appWateradminAddresslistDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AppWateradminAddresslist record) {
		return appWateradminAddresslistDAO.updateByPrimaryKey(record);
	}

	@Override
	public List<AppWateradminAddresslist> getAppWateradminAddresslist() {
		return appWateradminAddresslistDAO.getAppWateradminAddresslist();
	}

	@Override
	public List<AppWateradminAddresslist> selectDatas(AppWateradminAddresslist appWateradminAddresslist) {
		return appWateradminAddresslistDAO.selectDatas(appWateradminAddresslist);
	}
}
