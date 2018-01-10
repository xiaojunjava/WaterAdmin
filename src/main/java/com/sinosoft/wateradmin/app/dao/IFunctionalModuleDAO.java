package com.sinosoft.wateradmin.app.dao;


import com.sinosoft.wateradmin.app.bean.FunctionalModule;
import com.sinosoft.wateradmin.common.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 功能模块_dao
 * @author lvzhixue.
 * @create 2017/11/8
 */
@MyBatisDao
public interface IFunctionalModuleDAO {

    int deleteByPrimaryKey(Integer fmId);

    int insert(FunctionalModule record);

    int insertSelective(FunctionalModule record);

    FunctionalModule selectByPrimaryKey(Integer fmId);

    int updateByPrimaryKeySelective(FunctionalModule record);

    int updateByPrimaryKey(FunctionalModule record);

    /**
     * 根据角色id字符串获取所有能操作的系统
     * @param roleIdArray
     * @return
     */
    List<FunctionalModule> selectSystemListByRoleId(Object[] roleIdArray);

	/**
	 * 根据条件查询
	 * @param functionalModule
	 * @return
	 */
	List<FunctionalModule> selectDatas(FunctionalModule functionalModule);
    /**
     * 根据给定的fm_id查询该节点及其所有子节点
     */
    List<FunctionalModule> selectChildNodeByfmId(Integer fmId);

    /**
     * 根据给定的fm_id递归查询该节点及其所有子节点
     * @param fmId
     * @return
     */
    List<FunctionalModule> selectAllChildNodeByfmId(Integer fmId);

    //--根据给定的fm_id,fm_Type查询该节点下其所有子节点
    List<FunctionalModule> selectChildNodeByfmIdAndType(Map map);

	/**
	 * 根据fmId、rolId查子项
	 * @param map
	 * @return
	 */
	List<FunctionalModule> selectChildNodes(Map map);
}