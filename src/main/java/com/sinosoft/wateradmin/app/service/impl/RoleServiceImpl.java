package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.Role;
import com.sinosoft.wateradmin.app.dao.IRoleDAO;
import com.sinosoft.wateradmin.app.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理——service
 * Created by lvzhixue on 2017/11/8.
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDAO roleDAO;


    /**
     * 根据角色id字符串获取所有能操作的系统
     *
     * @param roleIdArray
     * @return
     */
    @Override
    public List<Role> selectSystemListByRoleId(Object[] roleIdArray) {
        return roleDAO.selectSystemListByRoleId(roleIdArray);
    }

    @Override
    public List<Role> getRoleList(Role role) {
        return this.roleDAO.getRoleList(role);
    }

    @Override
    public int addRole(Role role) {
        return roleDAO.insert(role);
    }

    @Override
    public int update(Role role) {
        return roleDAO.updateByPrimaryKey(role);
    }

    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return roleDAO.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(Role record) {
        return roleDAO.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleDAO.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        return roleDAO.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleDAO.updateByPrimaryKey(record);
    }
}
