package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.Organization;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 组织机构_dao
 * @author lvzhixue.
 * @create 2017/11/8
 */
@MyBatisDao
public interface IOrganizationDAO {

    int deleteByPrimaryKey(Integer orgId);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer orgId);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);


    List<Organization> getChildByOrgId(Integer orgId);

    Integer hasChild(Integer orgId);
}