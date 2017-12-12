package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.TestBean;
import com.sinosoft.wateradmin.app.dao.ITestDAO;
import com.sinosoft.wateradmin.app.service.ITestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TestServiceImpl implements ITestService{
	@Resource
	private ITestDAO testDAO;

	@Override
	public TestBean tetsQuery(String param1){
		return testDAO.tetsQuery("1");
	}
}
