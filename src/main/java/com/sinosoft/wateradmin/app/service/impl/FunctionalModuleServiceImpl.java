package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.FunctionalModule;
import com.sinosoft.wateradmin.app.dao.IFunctionalModuleDAO;
import com.sinosoft.wateradmin.app.service.IFunctionalModuleService;
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
public class FunctionalModuleServiceImpl implements IFunctionalModuleService {

    @Resource
    private IFunctionalModuleDAO functionalModuleDAO;


    /**
     * 根据角色id字符串获取所有能操作的系统
     *
     * @param roleIdArray
     * @return
     */
    @Override
    public List<FunctionalModule> selectSystemListByRoleId(Object[] roleIdArray) {
        return functionalModuleDAO.selectSystemListByRoleId(roleIdArray);
    }

    /**
     * 根据给定的fm_id查询该节点及其所有子节点
     *
     * @param fmId
     */
    @Override
    public List<FunctionalModule> selectChildNodeByfmId(Integer fmId) {
        return functionalModuleDAO.selectChildNodeByfmId(fmId);
    }

    /**
     * 根据给定的fm_id递归查询该节点及其所有子节点
     *
     * @param fmId
     * @return
     */
    @Override
    public List<FunctionalModule> selectAllChildNodeByfmId(Integer fmId) {
        return functionalModuleDAO.selectAllChildNodeByfmId(fmId);
    }

    /**
     * 根据给定的fm_id,fm_Type查询该节点下其所有子节点
     *
     * @param map
     * @return
     */
    @Override
    public List<FunctionalModule> selectChildNodeByfmIdAndType(Map map) {
        return functionalModuleDAO.selectChildNodeByfmIdAndType(map);
    }

    /**
     * 添加新模块
     *
     * @param record
     * @return
     */
    @Override
    public int insert(FunctionalModule record) {
        return this.functionalModuleDAO.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer fmId) {
        return this.functionalModuleDAO.deleteByPrimaryKey(fmId);
    }

    @Override
    public int updateByPrimaryKeySelective(FunctionalModule record) {
        return this.functionalModuleDAO.updateByPrimaryKeySelective(record);
    }
}
