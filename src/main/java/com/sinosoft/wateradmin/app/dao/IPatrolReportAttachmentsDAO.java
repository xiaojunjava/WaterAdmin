package com.sinosoft.wateradmin.app.dao;

import com.sinosoft.wateradmin.app.bean.PatrolReportAttachments;
import com.sinosoft.wateradmin.common.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 巡查上报附件  DAO层
 * @author lkj
 *
 */
@MyBatisDao
public interface IPatrolReportAttachmentsDAO {

	/**
	 * 根据主键查数据
	 * @param praId
	 * @return
	 */
	PatrolReportAttachments selectByPrimaryKey(@Param(value = "praId") int praId);

	/**
	 * 根据条件查询数据
	 * @return
	 */
	List<PatrolReportAttachments> selectDatas(PatrolReportAttachments pra);

	/**
	 * 根据主键删除1条数据
	 * @param praId
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "praId") int praId);

	/**
	 *新增记录
	 * @param pra
	 * @return
	 */
	int insert(PatrolReportAttachments pra);

	/**
	 * 更新记录
	 * @param pra
	 * @return
	 */
	int updateByPrimaryKey(PatrolReportAttachments pra);

}
