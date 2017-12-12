package com.sinosoft.wateradmin.cmd.dao;

import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车船实时位置监控记录  DAO层
 * @author lkj
 */
@MyBatisDao
public interface IShipMonitorDAO {

	/**
	 * 根据主键查数据
	 * @param csmId
	 * @return
	 */
	ShipMonitor selectByPrimaryKey(@Param(value = "csmId") int csmId);

	/**
	 * 根据条件查询数据
	 * @param sm
	 * @return
	 */
	List<ShipMonitor> selectDatas(ShipMonitor sm);

	/**
	 * 根据主键删除1条数据
	 * @param csmId
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "csmId") int csmId);

	/**
	 *新增记录
	 * @param sm
	 * @return
	 */
	int insert(ShipMonitor sm);

	/**
	 * 更新记录
	 * @param sm
	 * @return
	 */
	int update(ShipMonitor sm);

}
