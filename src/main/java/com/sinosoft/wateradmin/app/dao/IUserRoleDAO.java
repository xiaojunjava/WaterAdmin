package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.UserRoleKey;
import com.sinosoft.wateradmin.common.MyBatisDao;

/**
 * 用户角色_dao
 * @author lvzhixue.
 * @create 2017/11/8
 */
@MyBatisDao
public interface IUserRoleDAO {

    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    int deleteByUserId(int userId);
}