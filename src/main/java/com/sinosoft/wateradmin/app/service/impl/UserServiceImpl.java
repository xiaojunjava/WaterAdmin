package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.Users;
import com.sinosoft.wateradmin.app.dao.IUserRoleDAO;
import com.sinosoft.wateradmin.app.dao.IUsersDAO;
import com.sinosoft.wateradmin.app.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户管理——service
 * Created by lvzhixue on 2017/11/8.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private IUsersDAO usersDAO;

    @Resource
    private IUserRoleDAO userRoleDAO;


    /**
     * 登录名和密码是否正确
     *
     * @param map
     * @return
     */
    @Override
    public int isRightIDAndPassword(Map map) {
        return usersDAO.isRightIDAndPassword(map);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public int addUser(Users user) {
        return usersDAO.insert(user);
    }

    /**
     * 用户登录名是否存在
     *
     * @param userLoginname
     * @return
     */
    @Override
    public int isUserLoginnameExist(String userLoginname) {
        return 0;
    }

    /**
     * 根据用户登录名获取该用户信息
     *
     * @param userLoginName
     * @return
     */
    @Override
    public Users getUserByLoginName(String userLoginName) {
        return this.usersDAO.getUserByLoginName(userLoginName);
    }

    /**
     * 根据user表中的LoginName查询用户和角色信息
     * lvzhixue
     *
     * @param loginName
     */
    @Override
    public Users selectUserAndRoleByLoginName(String loginName) {
        return this.usersDAO.selectUserAndRoleByLoginName(loginName);
    }

    @Override
    public int isAdmin(Map map) {
        return this.usersDAO.isAdmin(map);
    }

    /**
     * 获取用户列表
     *
     * @param user
     * @return
     */
    @Override
    public List<Users> getUserList(Users user) {
        return this.usersDAO.getUserList(user);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Override
    public int update(Users user) {
        return this.usersDAO.updateByPrimaryKey(user);
    }

    /**
     * 按指定UserId删除某个用户
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int userId) {
        //--优先删除用户对应的角色关系
        this.userRoleDAO.deleteByUserId(userId);

        //--再删除本用户记录
        return this.usersDAO.deleteByPrimaryKey(userId);
    }


}
