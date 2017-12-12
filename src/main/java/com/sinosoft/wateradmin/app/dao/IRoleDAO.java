package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.Role;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 角色_dao
 * @author lvzhixue.
 * @create 2017/11/8
 */
@MyBatisDao
public interface IRoleDAO {

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
}