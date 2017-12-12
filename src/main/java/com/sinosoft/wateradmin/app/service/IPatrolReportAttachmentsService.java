package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.PatrolReportAttachments;
import java.util.List;

/**
 * 巡查上报附件  Service
 * @author lkj
 *
 */
public interface IPatrolReportAttachmentsService {

	/**
	 * 根据主键查数据
	 * @param praId
	 * @return
	 */
	public PatrolReportAttachments selectByPrimaryKey(int praId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param pra
	 * @return
	 * @throws Exception
	 */
	public List<PatrolReportAttachments> selectDatas(PatrolReportAttachments pra) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param praId
	 * @return
	 */
	public int deleteByPrimaryKey(int praId) throws Exception;

	/**
	 *新增记录
	 * @param pra
	 * @return
	 */
	public int insert(PatrolReportAttachments pra) throws Exception;

	/**
	 * 新增多条记录
	 * @param pras
	 * @return
	 * @throws Exception
	 */
	public int insertBatch(List<PatrolReportAttachments> pras) throws Exception;
	/**
	 * 更新记录
	 * @param pra
	 * @return
	 */
	public int updateByPrimaryKey(PatrolReportAttachments pra) throws Exception;

}
