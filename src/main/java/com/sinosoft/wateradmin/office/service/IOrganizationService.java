package com.sinosoft.wateradmin.office.service;

import com.sinosoft.wateradmin.app.bean.Organization;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */
public interface IOrganizationService {

    int deleteByPrimaryKey(Integer orgId);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer orgId);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    List<Organization> getChildByOrgId(Integer orgId);

    Integer hasChild(Integer orgId);
}
