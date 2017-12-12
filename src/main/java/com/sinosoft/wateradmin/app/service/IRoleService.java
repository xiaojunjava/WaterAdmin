package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.Role;

import java.util.List;

/**
 * 角色管理——接口
 * Created by lvzhixue on 2017/11/16.
 */
public interface IRoleService {

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据角色id字符串获取所有能操作的系统
     * @param roleIdArray
     * @return
     */
    List<Role> selectSystemListByRoleId(Object[] roleIdArray);

    List<Role> getRoleList(Role role);

    int addRole(Role role);

    int update(Role role);
}
