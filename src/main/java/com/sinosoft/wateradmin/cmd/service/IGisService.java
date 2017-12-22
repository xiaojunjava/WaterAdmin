package com.sinosoft.wateradmin.cmd.service;

import com.sinosoft.wateradmin.cmd.bean.ShipMonitor;

import java.util.List;
import java.util.Map;

/**
 * Gis相关（指挥调度用）  Service
 * @author lkj
 */
public interface IGisService {
	/**
	 * gis缓存的存储及序列化操作
	 * @throws Exception
	 */
	public void doGisRequest() throws  Exception;

	/**
	 *获取所有正在执法的人的实时坐标
	 * @return
	 * @throws Exception
	 */
	public Map getNowPeopleGis() throws Exception;

	/**
	 * 获取所有目标实时移动中的坐标（车/船）
	 * @param saType
	 * @return
	 * @throws Exception
	 */
	public Map getNowSaGis(String saType) throws Exception;

}
