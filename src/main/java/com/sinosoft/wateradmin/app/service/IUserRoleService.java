package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.UserRoleKey;

/**
 * 用户角色管理——接口
 * Created by lvzhixue on 2017/11/8.
 */
public interface IUserRoleService {

    /**
     * 添加用户角色
     * @param userRoleKey
     * @return
     */
    int addUserRole(UserRoleKey userRoleKey);


    /**
     * 按指定UserId删除某个用户角色
     * @param userId
     * @return
     */
    int deleteByUserId(int userId);
}
