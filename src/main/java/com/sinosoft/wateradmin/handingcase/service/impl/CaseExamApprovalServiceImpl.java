package com.sinosoft.wateradmin.handingcase.service.impl;

import com.sinosoft.wateradmin.handingcase.bean.CaseExamApproval;
import com.sinosoft.wateradmin.handingcase.dao.ICaseExamApprovalDAO;
import com.sinosoft.wateradmin.handingcase.service.ICaseExamApprovalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 无人机设备  Service实现类
 * @author lvzhixue
 *
 */
@Service
@Transactional
public class CaseExamApprovalServiceImpl implements ICaseExamApprovalService{

	@Resource
	private ICaseExamApprovalDAO caseExamApprovalDAO;


	/**
	 * 根据主键查数据
	 *
	 * @param ceaId
	 * @return
	 */
	@Override
	public CaseExamApproval selectByPrimaryKey(int ceaId) throws Exception {
		return this.caseExamApprovalDAO.selectByPrimaryKey(ceaId);
	}

	/**
	 * 根据条件查数据
	 *
	 * @param caseExamApproval
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CaseExamApproval> selectDatas(CaseExamApproval caseExamApproval) throws Exception {
		return this.caseExamApprovalDAO.selectDatas(caseExamApproval);
	}

	/**
	 * 根据主键删除1条数据
	 *
	 * @param ceaId
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(int ceaId) throws Exception {
		return this.caseExamApprovalDAO.deleteByPrimaryKey(ceaId);
	}

	/**
	 * 新增记录
	 *
	 * @param caseExamApproval
	 * @return
	 */
	@Override
	public int insert(CaseExamApproval caseExamApproval) throws Exception {
		return this.caseExamApprovalDAO.insertSelective(caseExamApproval);
	}

	/**
	 * 更新记录
	 *
	 * @param caseExamApproval
	 * @return
	 */
	@Override
	public int update(CaseExamApproval caseExamApproval) throws Exception {
		return this.caseExamApprovalDAO.updateByPrimaryKeySelective(caseExamApproval);
	}
}
