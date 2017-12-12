package com.sinosoft.wateradmin.cmd.dao;

import com.sinosoft.wateradmin.cmd.bean.ShipAlarm;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车船报警  DAO层
 * @author lkj
 */
@MyBatisDao
public interface IShipAlarmDAO {

	/**
	 * 根据主键查数据
	 * @param csaId
	 * @return
	 */
	ShipAlarm selectByPrimaryKey(@Param(value = "csaId") int csaId);

	/**
	 * 根据条件查询数据
	 * @param shipAlarm
	 * @return
	 */
	List<ShipAlarm> selectDatas(ShipAlarm shipAlarm);

	/**
	 * 根据主键删除1条数据
	 * @param csaId
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "csaId") int csaId);

	/**
	 *新增记录
	 * @param shipAlarm
	 * @return
	 */
	int insert(ShipAlarm shipAlarm);

	/**
	 * 更新记录
	 * @param shipAlarm
	 * @return
	 */
	int update(ShipAlarm shipAlarm);

}
