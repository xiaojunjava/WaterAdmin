package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.CaseInfo;

import java.util.List;

/**
 * 案件信息——接口
 * Created by lvzhixue on 2017/11/6.
 */
public interface ICaseInfoService {

    public List<CaseInfo> getUntreatedCaseInfoList();

    int deleteByPrimaryKey(Integer ciId);

    int insert(CaseInfo record);

    int insertSelective(CaseInfo record);

    CaseInfo selectByPrimaryKey(Integer ciId);

    int updateByPrimaryKeySelective(CaseInfo record);

    int updateByPrimaryKey(CaseInfo record);

    //获取待受理的案件记录
    List<CaseInfo> getUnDisposeCaseList(CaseInfo caseInfo);

    //获取待取证的案件记录
    List<CaseInfo> getEvidenceCaseList(CaseInfo caseInfo);

    //获取待审批案件记录
    List<CaseInfo> getApprovalCaseList(CaseInfo caseInfo);
}
