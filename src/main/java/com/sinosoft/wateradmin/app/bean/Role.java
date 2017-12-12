package com.sinosoft.wateradmin.app.bean;

import com.sinosoft.wateradmin.common.BaseBean;

import java.util.List;

/**
 * 角色_bean
 * added by lvzhixue 2017-11-08
 */
public class Role extends BaseBean {
    private Integer roleId;

    private String roleName;

    private String remark;

    private List<Users> userList;//--用户列表

    private List<FunctionalModule> moduleList;//--模块列表


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public List<FunctionalModule> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<FunctionalModule> moduleList) {
        this.moduleList = moduleList;
    }
}