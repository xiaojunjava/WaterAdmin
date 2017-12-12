package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.UserRoleKey;
import com.sinosoft.wateradmin.app.dao.IUserRoleDAO;
import com.sinosoft.wateradmin.app.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户管理——service
 * Created by lvzhixue on 2017/11/8.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements IUserRoleService {

    @Resource
    private IUserRoleDAO userRoleDAO;


    /**
     * 添加用户角色
     *
     * @param userRoleKey
     * @return
     */
    @Override
    public int addUserRole(UserRoleKey userRoleKey) {
        this.deleteByUserId(userRoleKey.getUserId());

        return this.userRoleDAO.insert(userRoleKey);
    }

    /**
     * 按指定UserId删除某个用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteByUserId(int userId) {
        return this.userRoleDAO.deleteByUserId(userId);
    }
}
