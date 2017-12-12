package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.Users;

import java.util.List;
import java.util.Map;

/**
 * 用户管理——接口
 * Created by lvzhixue on 2017/11/8.
 */
public interface IUserService {

    /**
     * 登录名和密码是否正确
     * @param map
     * @return
     */
    int isRightIDAndPassword(Map map);


    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(Users user);

    /**
     * 用户登录名是否存在
     * @param userLoginname
     * @return
     */
    int isUserLoginnameExist(String userLoginname);

    /**
     * 根据用户登录名获取该用户信息
     * @param userLoginName
     * @return
     */
    Users getUserByLoginName(String userLoginName);

    /**
     * 根据user表中的LoginName查询用户和角色信息
     * lvzhixue
     */

    Users selectUserAndRoleByLoginName(String userLoginName);

    /**
     * 判断是不是管理员
     * @param map
     * @return
     */
    int isAdmin(Map map);

    /**
     * 获取用户列表
     * @param user
     * @return
     */
    List<Users> getUserList(Users user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int update(Users user);

    /**
     * 按指定UserId删除某个用户
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(int userId);
}
