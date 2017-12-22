package com.sinosoft.wateradmin.app.dao;

import com.sinosoft.wateradmin.app.bean.CaseInfo;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 案件信息_dao
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */
@MyBatisDao
public interface ICaseInfoDAO {
    int deleteByPrimaryKey(Integer ciId);

    int insert(CaseInfo record);

    int insertSelective(CaseInfo record);

    CaseInfo selectByPrimaryKey(Integer ciId);

    int updateByPrimaryKeySelective(CaseInfo record);

    int updateByPrimaryKey(CaseInfo record);

    //--获取未处理的案件信息列表  ，added by lvzhixue 2017-11-6 11:06
    List<CaseInfo> getUntreatedCaseInfoList();

    /**
     * 获取待受理的案件记录
     */
    List<CaseInfo> getUnDisposeCaseList(CaseInfo caseInfo);


    List<CaseInfo> getEvidenceCaseList(CaseInfo caseInfo);

    List<CaseInfo> getApprovalCaseList(CaseInfo caseInfo);
}