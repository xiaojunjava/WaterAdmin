package com.sinosoft.wateradmin.app.dao;

import java.util.List;

import com.sinosoft.wateradmin.app.bean.TestBean;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;


/**
 * XXXXX  DAO层
 * @author lkj
 *
 */
@MyBatisDao
public interface ITestDAO {

	/**
	 * test
	 * @param param1
	 * @return
	 */
	TestBean tetsQuery(@Param(value = "param1") String param1);


}
