package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.CaseInfo;
import com.sinosoft.wateradmin.app.dao.ICaseInfoDAO;
import com.sinosoft.wateradmin.app.service.ICaseInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 案件信息——service
 * Created by lvzhixue on 2017/11/6.
 */
@Service
@Transactional
public class CaseInfoServiceImpl implements ICaseInfoService {

    @Resource
    private ICaseInfoDAO caseInfoDAO;


    @Override
    public List<CaseInfo> getUntreatedCaseInfoList() {

        return caseInfoDAO.getUntreatedCaseInfoList();
    }

    @Override
    public int deleteByPrimaryKey(Integer ciId) {
        return caseInfoDAO.deleteByPrimaryKey(ciId);
    }

    @Override
    public int insert(CaseInfo record) {
        return caseInfoDAO.insert(record);
    }

    @Override
    public int insertSelective(CaseInfo record) {
        return caseInfoDAO.insertSelective(record);
    }

    @Override
    public CaseInfo selectByPrimaryKey(Integer ciId) {
        return caseInfoDAO.selectByPrimaryKey(ciId);
    }

    @Override
    public int updateByPrimaryKeySelective(CaseInfo record) {
        return caseInfoDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CaseInfo record) {
        return caseInfoDAO.updateByPrimaryKey(record);
    }

    /**
     * 获取待受理的案件记录
     */
    @Override
    public List<CaseInfo> getUnDisposeCaseList(CaseInfo caseInfo) {
        return this.caseInfoDAO.getUnDisposeCaseList(caseInfo);
    }

    @Override
    public List<CaseInfo> getEvidenceCaseList(CaseInfo caseInfo) {
        return this.caseInfoDAO.getEvidenceCaseList(caseInfo);
    }


    @Override
    public List<CaseInfo> getApprovalCaseList(CaseInfo caseInfo) {
        return this.caseInfoDAO.getApprovalCaseList(caseInfo);
    }
}
