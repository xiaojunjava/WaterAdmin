package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.RoleModuleKey;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;

/**
 * 角色_模块_dao
 * @author lvzhixue.
 * @create 2017/11/8
 */
@MyBatisDao
public interface IRoleModuleDAO {

    int deleteByPrimaryKey(RoleModuleKey key);

    int insert(RoleModuleKey record);

    int insertSelective(RoleModuleKey record);

    List<RoleModuleKey> getRoleModuleKeyListByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}