package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.PatrolReport;
import com.sinosoft.wateradmin.app.dao.IPatrolReportDAO;
import com.sinosoft.wateradmin.app.service.IPatrolReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 巡查上报记录  Service实现
 * @author lkj
 */
@Service
@Transactional
public class PatrolReportServiceImpl implements IPatrolReportService{
	@Resource
	private IPatrolReportDAO patrolReportDAO;
	public PatrolReport selectByPrimaryKey(int prId) throws Exception{
		return patrolReportDAO.selectByPrimaryKey(prId);
	}

	public List<PatrolReport> selectDatas(PatrolReport pr) throws Exception{
		return   patrolReportDAO.selectDatas(pr);
	}
	public List<PatrolReport> selectSome(int limitNum) throws Exception{
		return   patrolReportDAO.selectSome(limitNum);
	}
	public int deleteByPrimaryKey(int prId) throws Exception{
		return patrolReportDAO.deleteByPrimaryKey(prId);
	}

	/**
	 *新增记录
	 * @param patrolReport
	 * @return
	 */
	public int insert(PatrolReport patrolReport) throws Exception{
		return patrolReportDAO.insert(patrolReport);
	}

	/**
	 * 更新记录
	 * @param patrolReport
	 * @return
	 */
	public int updateByPrimaryKey(PatrolReport patrolReport) throws Exception{
		return patrolReportDAO.updateByPrimaryKey(patrolReport);
	}

}
