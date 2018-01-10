package com.sinosoft.wateradmin.handingcase.dao;


import com.sinosoft.wateradmin.common.MyBatisDao;
import com.sinosoft.wateradmin.handingcase.bean.CaseExamApproval;

import java.util.List;

/**
 * 立案申请表_dao
 *
 * @author lvzhixue
 * @create 2018-1-04 15:37
 */
@MyBatisDao
public interface ICaseExamApprovalDAO {

    int insert(CaseExamApproval record);

    int insertSelective(CaseExamApproval record);

    CaseExamApproval selectByPrimaryKey(int ceaId);

    List<CaseExamApproval> selectDatas(CaseExamApproval caseExamApproval);

    int deleteByPrimaryKey(int ceaId);

    int updateByPrimaryKeySelective(CaseExamApproval caseExamApproval);
}