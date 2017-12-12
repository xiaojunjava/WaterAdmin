package com.sinosoft.wateradmin.office.service;

import com.sinosoft.wateradmin.app.bean.RoleModuleKey;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */
public interface IRoleModuleKeyService {

    int deleteByPrimaryKey(RoleModuleKey key);

    int insert(RoleModuleKey record);

    int insertSelective(RoleModuleKey record);


    List<RoleModuleKey> getRoleModuleKeyListByRoleId(Integer roleId);

    boolean setRoleModule(Integer roleId, String[] moduleIdArray);
}
