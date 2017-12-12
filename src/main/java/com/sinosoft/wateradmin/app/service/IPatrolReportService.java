package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.PatrolReport;
import java.util.List;

/**
 * 巡查上报记录  Service
 * @author lkj
 *
 */
public interface IPatrolReportService {

	/**
	 * 根据主键查数据
	 * @param prId
	 * @return
	 */
	public PatrolReport selectByPrimaryKey(int prId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<PatrolReport> selectDatas(PatrolReport pr) throws Exception;
	/**
	 * 返回一定条数的数据
	 * @param limitNum
	 * @return
	 * @throws Exception
	 */
	public List<PatrolReport> selectSome(int limitNum) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param prId
	 * @return
	 */
	public int deleteByPrimaryKey(int prId) throws Exception;

	/**
	 *新增记录
	 * @param patrolReport
	 * @return
	 */
	public int insert(PatrolReport patrolReport) throws Exception;

	/**
	 * 更新记录
	 * @param patrolReport
	 * @return
	 */
	public int updateByPrimaryKey(PatrolReport patrolReport) throws Exception;

}
