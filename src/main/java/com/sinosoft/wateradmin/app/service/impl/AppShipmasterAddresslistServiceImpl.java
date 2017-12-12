package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppShipmasterAddresslist;
import com.sinosoft.wateradmin.app.dao.IAppShipmasterAddresslistDAO;
import com.sinosoft.wateradmin.app.service.IAppShipmasterAddresslistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *采沙/运沙船主通讯录  Service实现类
 * @author lvzhixue
 *
 */
@Service
@Transactional
public class AppShipmasterAddresslistServiceImpl implements IAppShipmasterAddresslistService{

	@Resource
	private IAppShipmasterAddresslistDAO appShipmasterAddresslistDAO;


	@Override
	public int deleteByPrimaryKey(Integer asaId) {
		return appShipmasterAddresslistDAO.deleteByPrimaryKey(asaId);
	}

	@Override
	public int insert(AppShipmasterAddresslist record) {
		return appShipmasterAddresslistDAO.insert(record);
	}

	@Override
	public int insertSelective(AppShipmasterAddresslist record) {
		return appShipmasterAddresslistDAO.insertSelective(record);
	}

	@Override
	public AppShipmasterAddresslist selectByPrimaryKey(Integer asaId) {
		return appShipmasterAddresslistDAO.selectByPrimaryKey(asaId);
	}

	@Override
	public int updateByPrimaryKeySelective(AppShipmasterAddresslist record) {
		return appShipmasterAddresslistDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AppShipmasterAddresslist record) {
		return appShipmasterAddresslistDAO.updateByPrimaryKey(record);
	}

	@Override
	public List<AppShipmasterAddresslist> getCaiShipmasterAddresslist() {
		return appShipmasterAddresslistDAO.getCaiShipmasterAddresslist();
	}

	@Override
	public List<AppShipmasterAddresslist> getYunShipmasterAddresslist() {
		return appShipmasterAddresslistDAO.getYunShipmasterAddresslist();
	}

	@Override
	public List<AppShipmasterAddresslist> selectDatas(AppShipmasterAddresslist shipmaster) {
		return appShipmasterAddresslistDAO.selectDatas(shipmaster);
	}
}
