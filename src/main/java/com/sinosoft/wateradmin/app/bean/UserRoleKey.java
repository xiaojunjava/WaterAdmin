package com.sinosoft.wateradmin.app.bean;

/**
 * 用户-角色对应关系_bean
 * added by lvzhixue 2017-11-08
 */
public class UserRoleKey {

    private Integer userId;

    private Integer roleId;

    private Users user;

    private Role role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}