package com.sinosoft.wateradmin.office.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.FunctionalModule;
import com.sinosoft.wateradmin.app.bean.Role;
import com.sinosoft.wateradmin.app.bean.RoleModuleKey;
import com.sinosoft.wateradmin.app.service.IFunctionalModuleService;
import com.sinosoft.wateradmin.app.service.IRoleService;
import com.sinosoft.wateradmin.common.BasePage;
import com.sinosoft.wateradmin.office.service.IRoleModuleKeyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理_Controller
 * Created by lvzhixue on 2017/11/6.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static Logger loger = Logger.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IFunctionalModuleService functionalModuleService;

    @Autowired
    private IRoleModuleKeyService roleModuleKeyService;


    //--跳转到角色管理页面
    @RequestMapping(value = "/gotoRolePage")
    public String gotoRolePage(HttpServletRequest request) throws Exception {
        getModuleTree(request);
        return "office/role";
    }

    //--查看权限
    @RequestMapping(value = "/getRoleModule")
    @ResponseBody
    public List<RoleModuleKey> getRoleModule(HttpServletRequest request) throws Exception {
        //--获取角色编号
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));

        //--根据角色编号获取该角色可以操作的模块
        List<RoleModuleKey> roleModuleKeyList = this.roleModuleKeyService.getRoleModuleKeyListByRoleId(roleId);

        return roleModuleKeyList;

    }


    //--设置权限
    @RequestMapping(value = "/setRoleModule")
    @ResponseBody
    public Object setRoleModule(HttpServletRequest request) throws Exception {
        Map map = new HashMap();

        //--获取角色编号
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        String moduleIds = request.getParameter("moduleIds");

        String[] moduleIdArray = moduleIds.split(",");

        if (this.roleModuleKeyService.setRoleModule(roleId,moduleIdArray)){
            map.put("tag", "true");
            map.put("message", "操作成功");
        }else {
            map.put("tag", "false");
            map.put("message", "DB操作异常");
        }
        return map;
    }


    /**
     * 获取模块树
     * @param request
     */
    private void getModuleTree(HttpServletRequest request){
        //--获取菜单列表，按照系统、一级菜单、二级菜单的模式进行组合
        //--1.1 获取系统列表
        List<FunctionalModule> systemList = this.functionalModuleService.selectChildNodeByfmId(0);
        for (int i = 0; i < systemList.size(); i++) {//--遍历系统，并获取其子节点

            FunctionalModule system = systemList.get(i);//--系统名称
            //--1.2根据系统名称获取一级菜单
            List<FunctionalModule> firstMenuList = this.functionalModuleService.selectChildNodeByfmId(system.getFmId());
            system.setChildNodeList(firstMenuList);

            for (int j = 0; j < firstMenuList.size(); j++) {//--获取二级菜单列表
                FunctionalModule firstMenu = firstMenuList.get(j);
                List<FunctionalModule> secondMenuList = this.functionalModuleService.selectChildNodeByfmId(firstMenu.getFmId());
                firstMenu.setChildNodeList(secondMenuList);
            }
        }

        request.getSession().setAttribute("popedomTree",systemList);
    }

    /**
     * 查询角色列表
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/getRoleList")
    @ResponseBody
    public BasePage getRoleList(@RequestParam(required = true, defaultValue = "1") Integer page,
                                 @RequestParam(required = true, defaultValue = "10") Integer rows,
                                 Role role,
                                HttpServletRequest request) throws Exception {
        PageHelper.startPage(page, rows);
        List<Role> roleList = this.roleService.getRoleList(role);
        BasePage<Role> pi = new BasePage<Role>(roleList);
        return pi;
    }    

    /**
     * 新增1个角色
     *
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveRole")
    public @ResponseBody Object saveRole(@RequestBody Role role) throws Exception {
        Map map = new HashMap();

        /***条件异常判断***/
        if (role == null) {
            map.put("tag", "false");
            map.put("message", "未获取参数");
            return map;
        }
        int count = this.roleService.addRole(role);
        if (count > 0) {
            map.put("tag", "true");
            map.put("message", "操作成功");
        } else {
            map.put("tag", "false");
            map.put("message", "DB操作异常");
        }
        return map;
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object updateRole(@RequestBody Role role, HttpServletRequest request) throws Exception {
        Map m = new HashMap();
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        role.setRoleId(roleId);

        /***条件异常判断***/
        if (role == null || (role != null && role.getRoleId() <= 0)) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int count = this.roleService.update(role);
        if (count > 0) {
            m.put("tag", "true");
            m.put("message", "操作成功");
        } else {
            m.put("tag", "false");
            m.put("message", "DB操作异常");
        }
        return m;
    }

    /**
     * 删除1条记录
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delRole")
    public @ResponseBody
    Object delRole(int roleId) throws Exception {
        Map m = new HashMap();

        /***条件异常判断***/
        if (roleId <= 0) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int ee = this.roleService.deleteByPrimaryKey(roleId);
        if (ee > 0) {
            m.put("tag", "true");
            m.put("message", "操作成功");
        } else {
            m.put("tag", "false");
            m.put("message", "DB操作异常");
        }
        return m;
    }
}
