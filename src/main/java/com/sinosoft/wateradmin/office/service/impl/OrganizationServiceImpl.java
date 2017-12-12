package com.sinosoft.wateradmin.office.service.impl;

import com.sinosoft.wateradmin.app.bean.Organization;
import com.sinosoft.wateradmin.app.dao.IOrganizationDAO;
import com.sinosoft.wateradmin.office.service.IOrganizationService;
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
public class OrganizationServiceImpl implements IOrganizationService {

    @Resource
    private IOrganizationDAO organizationDAO;

    @Override
    public int deleteByPrimaryKey(Integer orgId) {
        return organizationDAO.deleteByPrimaryKey(orgId);
    }

    @Override
    public int insert(Organization record) {
        return organizationDAO.insert(record);
    }

    @Override
    public int insertSelective(Organization record) {
        return organizationDAO.insertSelective(record);
    }

    @Override
    public Organization selectByPrimaryKey(Integer orgId) {
        return organizationDAO.selectByPrimaryKey(orgId);
    }

    @Override
    public int updateByPrimaryKeySelective(Organization record) {
        return organizationDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Organization record) {
        return organizationDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<Organization> getChildByOrgId(Integer orgId) {
        return organizationDAO.getChildByOrgId(orgId);
    }

    @Override
    public Integer hasChild(Integer orgId) {
        return organizationDAO.hasChild(orgId);
    }
}
