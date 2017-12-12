package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.PatrolReportAttachments;
import com.sinosoft.wateradmin.app.dao.IPatrolReportAttachmentsDAO;
import com.sinosoft.wateradmin.app.service.IPatrolReportAttachmentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 巡查上报附件  Service实现
 * @author lkj
 */
@Service
@Transactional
public class PatrolReportAttachmentsServiceImpl implements IPatrolReportAttachmentsService {
	@Resource
	private IPatrolReportAttachmentsDAO patrolReportAttachmentsDAO;
	/**
	 * 根据主键查数据
	 * @param praId
	 * @return
	 */
	public PatrolReportAttachments selectByPrimaryKey(int praId) throws Exception{
		return patrolReportAttachmentsDAO.selectByPrimaryKey(praId);
	}

	/**
	 * 查所有数据
	 * @return
	 */
	public List<PatrolReportAttachments> selectDatas(PatrolReportAttachments pra) throws Exception{
		return   patrolReportAttachmentsDAO.selectDatas(pra);
	}

	/**
	 * 根据主键删除1条数据
	 * @param praId
	 * @return
	 */
	public int deleteByPrimaryKey(int praId) throws Exception{
		return patrolReportAttachmentsDAO.deleteByPrimaryKey(praId);
	}

	/**
	 *新增记录
	 * @param pra
	 * @return
	 */
	public int insert(PatrolReportAttachments pra) throws Exception{
		return patrolReportAttachmentsDAO.insert(pra);
	}
	public int insertBatch(List<PatrolReportAttachments> pras) throws Exception{
		int e=-1;
		if(pras!=null&&pras.size()>0){
			for(PatrolReportAttachments pra:pras){
				e=patrolReportAttachmentsDAO.insert(pra);
				if(e<=0){
					throw new Exception("多条附件记录DB保存时异常");
				}
			}
		}
		return e;
	}

	/**
	 * 更新记录
	 * @param pra
	 * @return
	 */
	public int updateByPrimaryKey(PatrolReportAttachments pra) throws Exception{
		return patrolReportAttachmentsDAO.updateByPrimaryKey(pra);
	}

}
