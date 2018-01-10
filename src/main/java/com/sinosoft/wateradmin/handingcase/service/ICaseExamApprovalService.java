package com.sinosoft.wateradmin.handingcase.service;

import com.sinosoft.wateradmin.handingcase.bean.CaseExamApproval;

import java.util.List;

/**
 * 立案审批表  Service
 * @author lvzhixue added by 2018/1/4 15:51
 *
 */
public interface ICaseExamApprovalService {

	/**
	 * 根据主键查数据
	 * @param ceaId
	 * @return
	 */
	public CaseExamApproval selectByPrimaryKey(int ceaId) throws Exception;

	/**
	 * 根据条件查数据
	 * @param caseExamApproval
	 * @return
	 * @throws Exception
	 */
	public List<CaseExamApproval> selectDatas(CaseExamApproval caseExamApproval) throws Exception;

	/**
	 * 根据主键删除1条数据
	 * @param ceaId
	 * @return
	 */
	public int deleteByPrimaryKey(int ceaId) throws Exception;

	/**
	 *新增记录
	 * @param caseExamApproval
	 * @return
	 */
	public int insert(CaseExamApproval caseExamApproval) throws Exception;

	/**
	 * 更新记录
	 * @param caseExamApproval
	 * @return
	 */
	public int update(CaseExamApproval caseExamApproval) throws Exception;

}
