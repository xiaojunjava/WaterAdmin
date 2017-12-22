package com.sinosoft.wateradmin.app.dao;

import com.sinosoft.wateradmin.app.bean.CaseEvidenceCollection;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * 案件调查取证记录_dao
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */
@MyBatisDao
public interface ICaseEvidenceCollectionDAO {
    int deleteByPrimaryKey(BigDecimal ecId);

    int insert(CaseEvidenceCollection record);

    int insertRecord(CaseEvidenceCollection record);

    int insertSelective(CaseEvidenceCollection record);

    CaseEvidenceCollection selectByPrimaryKey(BigDecimal ecId);

    int updateByPrimaryKeySelective(CaseEvidenceCollection record);

    int updateByPrimaryKey(CaseEvidenceCollection record);

    List<CaseEvidenceCollection> getEvidenceCollectionList(CaseEvidenceCollection caseEvidenceCollection);
}