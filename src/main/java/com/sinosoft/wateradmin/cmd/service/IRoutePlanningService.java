package com.sinosoft.wateradmin.cmd.service;

import com.sinosoft.wateradmin.cmd.bean.RoutePlanning;
import java.util.List;
/**
 * 路线规划  Service
 * @author lkj
 */
public interface IRoutePlanningService {

	/**
	 * 根据主键查数据
	 * @param rpId
	 * @return
	 */
	public RoutePlanning selectByPrimaryKey(int rpId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param rp
	 * @return
	 * @throws Exception
	 */
	public List<RoutePlanning> selectDatas(RoutePlanning rp) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param rpId
	 * @return
	 */
	public int deleteByPrimaryKey(int rpId) throws Exception;

	/**
	 *新增记录
	 * @param rp
	 * @return
	 */
	public int insert(RoutePlanning rp) throws Exception;

	/**
	 * 更新记录
	 * @param rp
	 * @return
	 */
	public int update(RoutePlanning rp) throws Exception;

}
