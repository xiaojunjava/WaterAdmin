package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppTipstaffAddresslist;
import com.sinosoft.wateradmin.app.dao.IAppTipstaffAddresslistDAO;
import com.sinosoft.wateradmin.app.service.IAppTipstaffAddresslistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *执法人员通讯录  Service实现类
 * @author lvzhixue
 *
 */
@Service
@Transactional
public class AppTipstaffAddresslistServiceImpl implements IAppTipstaffAddresslistService{

	@Resource
	private IAppTipstaffAddresslistDAO appTipstaffAddresslistDAO;


	@Override
	public int deleteByPrimaryKey(Integer tipstaffId) {
		return appTipstaffAddresslistDAO.deleteByPrimaryKey(tipstaffId);
	}

	@Override
	public int insert(AppTipstaffAddresslist record) {
		return appTipstaffAddresslistDAO.insert(record);
	}

	@Override
	public int insertSelective(AppTipstaffAddresslist record) {
		return appTipstaffAddresslistDAO.insertSelective(record);
	}

	@Override
	public AppTipstaffAddresslist selectByPrimaryKey(Integer tipstaffId) {
		return appTipstaffAddresslistDAO.selectByPrimaryKey(tipstaffId);
	}

	@Override
	public int updateByPrimaryKeySelective(AppTipstaffAddresslist record) {
		return appTipstaffAddresslistDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AppTipstaffAddresslist record) {
		return appTipstaffAddresslistDAO.updateByPrimaryKey(record);
	}

	@Override
	public List<AppTipstaffAddresslist> getAppTipstaffAddresslist() {
		return appTipstaffAddresslistDAO.getAppTipstaffAddresslist();
	}

	@Override
	public List<AppTipstaffAddresslist> selectDatas(AppTipstaffAddresslist appTipstaffAddresslist) {
		return appTipstaffAddresslistDAO.selectDatas(appTipstaffAddresslist);
	}
}
