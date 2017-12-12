package com.sinosoft.wateradmin.app.dao;

import com.sinosoft.wateradmin.app.bean.PatrolReport;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 巡查上报记录  DAO层
 * @author lkj
 *
 */
@MyBatisDao
public interface IPatrolReportDAO {

	/**
	 * 根据主键查数据
	 * @param prId
	 * @return
	 */
	PatrolReport selectByPrimaryKey(@Param(value = "prId") int prId);

	/**
	 * 根据条件查询数据
	 * @return
	 */
	List<PatrolReport> selectDatas(PatrolReport pr);
	/**
	 * 根据返回一定条数的记录
	 * @param limitNum
	 * @return
	 */
	List<PatrolReport> selectSome(int limitNum);

	/**
	 * 根据主键删除1条数据
	 * @param prId
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "prId") int prId);

	/**
	 *新增记录
	 * @param patrolReport
	 * @return
	 */
	int insert(PatrolReport patrolReport);

	/**
	 * 更新记录
	 * @param patrolReport
	 * @return
	 */
	int updateByPrimaryKey(PatrolReport patrolReport);

}
