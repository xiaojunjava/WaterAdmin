package com.sinosoft.wateradmin.office.service.impl;

import com.sinosoft.wateradmin.app.bean.RoleModuleKey;
import com.sinosoft.wateradmin.app.dao.IRoleModuleDAO;
import com.sinosoft.wateradmin.office.service.IRoleModuleKeyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 案件信息——service
 * Created by lvzhixue on 2017/11/6.
 */
@Service
@Transactional
public class RoleModuleKeyServiceImpl implements IRoleModuleKeyService {

    @Resource
    private IRoleModuleDAO roleModuleDAO;


    @Override
    public int deleteByPrimaryKey(RoleModuleKey key) {
        return this.roleModuleDAO.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(RoleModuleKey record) {
        return roleModuleDAO.insert(record);
    }

    @Override
    public int insertSelective(RoleModuleKey record) {
        return this.roleModuleDAO.insertSelective(record);
    }

    @Override
    public List<RoleModuleKey> getRoleModuleKeyListByRoleId(Integer roleId) {
        return this.roleModuleDAO.getRoleModuleKeyListByRoleId(roleId);
    }

    @Override
    public boolean setRoleModule(Integer roleId, String[] moduleIdArray) {
        try{
                this.roleModuleDAO.deleteByRoleId(roleId);

                for (int i = 0; i < moduleIdArray.length; i++) {
                    if (moduleIdArray[i]!=null && !moduleIdArray[i].equals("") && moduleIdArray[i]!="") {
                        RoleModuleKey roleModuleKey = new RoleModuleKey();
                        roleModuleKey.setRoleId(roleId);
                        roleModuleKey.setFmId(Integer.valueOf(moduleIdArray[i]));
                        this.roleModuleDAO.insert(roleModuleKey);
                    }
                }

                return true;
        }catch (Exception e){
            return false;
        }
    }
}
