package com.sinosoft.wateradmin.app.dao;

import com.sinosoft.wateradmin.app.bean.CaseAttachments;
import com.sinosoft.wateradmin.common.MyBatisDao;

/**
 * 案件调查取证记录_附件_dao
 *
 * @author lvzhixue
 * @create 2017-11-02 15:06
 */
@MyBatisDao
public interface ICaseAttachmentsDAO {

    int deleteByPrimaryKey(Integer ecaId);

    int insert(CaseAttachments record);

    int insertSelective(CaseAttachments record);

    CaseAttachments selectByPrimaryKey(Integer ecaId);

    int updateByPrimaryKeySelective(CaseAttachments record);

    int updateByPrimaryKey(CaseAttachments record);
}