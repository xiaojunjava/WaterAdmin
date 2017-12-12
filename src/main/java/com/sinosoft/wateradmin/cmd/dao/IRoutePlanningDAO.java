package com.sinosoft.wateradmin.cmd.dao;

import com.sinosoft.wateradmin.cmd.bean.RoutePlanning;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * 路线规划  DAO层
 * @author lkj
 */
@MyBatisDao
public interface IRoutePlanningDAO {

	/**
	 * 根据主键查数据
	 * @param rpId
	 * @return
	 */
	RoutePlanning selectByPrimaryKey(@Param(value = "rpId") int rpId);

	/**
	 * 根据条件查询数据
	 * @param rp
	 * @return
	 */
	List<RoutePlanning> selectDatas(RoutePlanning rp);

	/**
	 * 根据主键删除1条数据
	 * @param rpId
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "rpId") int rpId);

	/**
	 *新增记录
	 * @param rp
	 * @return
	 */
	int insert(RoutePlanning rp);

	/**
	 * 更新记录
	 * @param rp
	 * @return
	 */
	int update(RoutePlanning rp);

}
