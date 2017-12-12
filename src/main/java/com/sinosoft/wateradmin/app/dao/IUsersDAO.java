package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.Users;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 用户_dao
 * @author lvzhixue.
 * @create 2017/11/8
 */
@MyBatisDao
public interface IUsersDAO {

    int deleteByPrimaryKey(Integer userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    //--added by lvzhixue
    int isRightIDAndPassword(Map map);

    /**
     * 根据用户登录名获取该用户信息
     * added by lvzhixue 2017-11-15
     * @param userLoginName
     * @return
     */
    Users getUserByLoginName(String userLoginName);

    /**
     * 根据user表中的LoginName查询用户和角色信息
     * @param loginName
     * @return
     */
    Users selectUserAndRoleByLoginName(String loginName);

    /**
     * 是否是系统管理员
     * @param map
     * @return
     */
    int isAdmin(Map map);

    /**
     * 根据user模糊查询，获取用户列表
     * @param user
     * @return
     */
    List<Users> getUserList(Users user);
}