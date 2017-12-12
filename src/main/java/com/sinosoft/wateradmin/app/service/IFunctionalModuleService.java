package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.FunctionalModule;

import java.util.List;
import java.util.Map;

/**
 * 用户管理——接口
 * Created by lvzhixue on 2017/11/16.
 */
public interface IFunctionalModuleService {


    /**
     * 根据角色id字符串获取所有能操作的系统
     * @param roleIdArray
     * @return
     */
    List<FunctionalModule> selectSystemListByRoleId(Object[] roleIdArray);

    /**
     * 根据给定的fm_id查询该节点下其所有子节点
     */
    List<FunctionalModule> selectChildNodeByfmId(Integer fmId);

    /**
     * 根据给定的fm_id递归查询该节点及其所有子节点
     * @param fmId
     * @return
     */
    List<FunctionalModule> selectAllChildNodeByfmId(Integer fmId);

    /**
     * 根据给定的fm_id,fm_Type查询该节点下其所有子节点
     * @param map
     * @return
     */
    List<FunctionalModule> selectChildNodeByfmIdAndType(Map map);

    /**
     * 添加新模块
     * @param record
     * @return
     */
    int insert(FunctionalModule record);

    int deleteByPrimaryKey(Integer fmId);

    int updateByPrimaryKeySelective(FunctionalModule record);
}
