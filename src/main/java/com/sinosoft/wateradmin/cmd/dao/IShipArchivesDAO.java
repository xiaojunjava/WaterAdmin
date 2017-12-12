package com.sinosoft.wateradmin.cmd.dao;

import com.sinosoft.wateradmin.cmd.bean.ShipArchives;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车船档案  DAO层
 * @author lkj
 *
 */
@MyBatisDao
public interface IShipArchivesDAO {

	/**
	 * 根据主键查数据
	 * @param saId
	 * @return
	 */
	ShipArchives selectByPrimaryKey(@Param(value = "saId") int saId);

	/**
	 * 根据条件查询数据
	 * @param sa
	 * @return
	 */
	List<ShipArchives> selectDatas(ShipArchives sa);

	/**
	 * 根据主键删除1条数据
	 * @param saId
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "saId") int saId);

	/**
	 *新增记录
	 * @param sa
	 * @return
	 */
	int insert(ShipArchives sa);

	/**
	 * 更新记录
	 * @param sa
	 * @return
	 */
	int update(ShipArchives sa);

}
