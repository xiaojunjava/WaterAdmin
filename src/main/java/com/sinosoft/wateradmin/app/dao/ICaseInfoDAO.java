package com.sinosoft.wateradmin.app.dao;

import com.sinosoft.wateradmin.app.bean.CaseInfo;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * 案件信息_dao
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */
@MyBatisDao
public interface ICaseInfoDAO {
    int deleteByPrimaryKey(BigDecimal ciId);

    int insert(CaseInfo record);

    int insertSelective(CaseInfo record);

    CaseInfo selectByPrimaryKey(BigDecimal ciId);

    int updateByPrimaryKeySelective(CaseInfo record);

    int updateByPrimaryKey(CaseInfo record);

    //--获取未处理的案件信息列表  ，added by lvzhixue 2017-11-6 11:06
    List<CaseInfo> getUntreatedCaseInfoList();
}