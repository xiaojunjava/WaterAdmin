package com.sinosoft.wateradmin.cmd.service;

import com.sinosoft.wateradmin.cmd.bean.ShipArchives;
import java.util.List;

/**
 * 车船档案  Service
 * @author lkj
 *
 */
public interface IShipArchivesService {

	/**
	 * 根据主键查数据
	 * @param saId
	 * @return
	 */
	public ShipArchives selectByPrimaryKey(int saId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param sa
	 * @return
	 * @throws Exception
	 */
	public List<ShipArchives> selectDatas(ShipArchives sa) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param saId
	 * @return
	 */
	public int deleteByPrimaryKey(int saId) throws Exception;

	/**
	 *新增记录
	 * @param sa
	 * @return
	 */
	public int insert(ShipArchives sa) throws Exception;

	/**
	 * 更新记录
	 * @param sa
	 * @return
	 */
	public int update(ShipArchives sa) throws Exception;

}
