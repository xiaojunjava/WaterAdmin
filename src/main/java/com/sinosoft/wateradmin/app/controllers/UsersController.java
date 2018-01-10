package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.FunctionalModule;
import com.sinosoft.wateradmin.app.bean.Role;
import com.sinosoft.wateradmin.app.bean.UserRoleKey;
import com.sinosoft.wateradmin.app.bean.Users;
import com.sinosoft.wateradmin.app.service.IFunctionalModuleService;
import com.sinosoft.wateradmin.app.service.IUserRoleService;
import com.sinosoft.wateradmin.app.service.IUserService;
import com.sinosoft.wateradmin.common.BasePage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

/**
 * 用户管理_Controller
 * Created by lvzhixue on 2017/11/6.
 */
@Controller
@RequestMapping("/login")
public class UsersController {

    private final static Logger loger = Logger.getLogger(UsersController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IFunctionalModuleService functionalModuleService;


    //--跳转到用户管理页面
    @RequestMapping(value = "/gotoUsersPage")
    public String gotoUsersPage(HttpServletRequest request) throws Exception {
        return "office/users";
    }

    /**
     *  手机app，用户登录
     *  Created by lvzhixue on 2017/11/9.
     */
    @RequestMapping("/appUserLogin")
    @ResponseBody
    public Map<String, Object> appUserLogin(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();

        //--获取用户登录名、登陆密码以及登陆类型
        String userLoginName = request.getParameter("loginName");
        String userPsw =  request.getParameter("userPsw");

        //-如果用户名或密码为空，则停止登录，并返回提示
        if ((userLoginName == null) || userLoginName.equals("") || (userPsw == null) || userPsw.equals("")){
            result.put("code", "1");
            result.put("message", "用户名或密码为空!");
            return result;
        }

        //--判断用户名和密码是否正确，如果不正确，则停止登陆并返回提示
        Map map = new HashMap();
        map.put("loginName",userLoginName);
        map.put("userPsw", userPsw);
        if (userService.isRightIDAndPassword(map)>0){//--如果用户名和密码正确，则返回值为1
            //--获取当前用户的基本信息
            Users user = this.userService.getUserByLoginName(userLoginName);

            result.put("code", "0");
            result.put("message", "登录成功！");
            result.put("user", user);

            //--将登陆的用户保存到session中，将权限保存到session中
            request.getSession().setAttribute("user",user);

            return result;
        }else{//--用户名和密码错误
            result.put("code", "2");
            result.put("message", "用户名或密码错误!");
            return result;
        }
    }


    /**
     *  pc端，用户登录
     *  Created by lvzhixue on 2017/11/9.
     */
    @RequestMapping("/pcUserLogin")
    @ResponseBody
    public Map<String,Object> pcUserLogin(HttpServletRequest request,HttpServletResponse response){
        //--获取用户登录名、登陆密码以及登陆类型
        String userLoginName = request.getParameter("loginName");
        String userPsw =  request.getParameter("userPsw");

        Map<String, Object> result = new HashMap<String, Object>();

        //-如果用户名或密码为空，则停止登录，并返回提示
        if ((userLoginName == null) || userLoginName.equals("") || (userPsw == null) || userPsw.equals("")){
            result.put("code", "1");
            result.put("message", "用户名或密码为空!");
            return result;
        }

        //--判断用户名和密码是否正确，如果不正确，则停止登陆并返回提示
        Map map = new HashMap();
        map.put("loginName",userLoginName);
        map.put("userPsw", userPsw);
        if (userService.isRightIDAndPassword(map)>0){//--如果用户名和密码正确，则返回值为1
            //--登陆成功，获取角色、权限，进入首页面
            //--获取用户的角色
            Users user = this.userService.selectUserAndRoleByLoginName(userLoginName);
            List<Role> roleList = user.getRoleList();//--一个用户可能有多种角色,根据用户获取角色

            //--获取所有角色id，并拼装字符串
            List roleIdArray = new ArrayList();
            for (int i = 0; i < roleList.size(); i++) {
                roleIdArray.add(roleList.get(i).getRoleId());
            }

            //--根据角色获取角色所能操作的系统
			FunctionalModule fm=new FunctionalModule();
            fm.setFmStatus(1);
            List<FunctionalModule> moduleList = this.functionalModuleService.selectDatas(fm);

            //--将登陆的用户保存到session中，将权限保存到session中
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("systemList",moduleList);

            result.put("code", "0");
            result.put("message", "登录成功！");
            result.put("systemList", moduleList);

            return result;
        }else{//--用户名和密码错误
            result.put("code", "2");
            result.put("message", "用户名或密码错误!");
            return result;
        }
    }

    /**
     *  pc端，admin登录
     *  Created by lvzhixue on 2017/11/20.
     */
    @RequestMapping("/pcAdminUserLogin")
    @ResponseBody
    public Map<String,Object> pcAdminUserLogin(HttpServletRequest request,HttpServletResponse response){
        //--获取用户登录名、登陆密码
        String userPsw =  request.getParameter("userPsw");
        String userLoginName = request.getParameter("loginName");

        Map<String, Object> result = new HashMap<String, Object>();

        //-如果用户名或密码为空，则停止登录，并返回提示
        if ((userLoginName == null) || userLoginName.equals("") || (userPsw == null) || userPsw.equals("")){
            result.put("code", "1");
            result.put("message", "用户名或密码为空!");
            return result;
        }

        //--判断用户名和密码是否正确，如果不正确，则停止登陆并返回提示
        Map map = new HashMap();
        map.put("loginName",userLoginName);
        map.put("userPsw", userPsw);
        if (userService.isRightIDAndPassword(map)>0){//--如果用户名和密码正确，并且该用户是系统管理员
            if (userService.isAdmin(map)>0){//--是系统管理员
                //--获取当前用户的基本信息
                Users user = this.userService.getUserByLoginName(userLoginName);

                List menuList = new ArrayList();
                Map mapMenu = new HashMap();
                mapMenu.put("fmType", 4);//1-app,2-指挥中心，3-普通操作员能操作的模块，4-系统管理员能操作的模块
                //--登陆成功，获取系统管理员所具有的权限，进入管理后台页面
                //--1、根据系统管理员角色，获取该角色所能操作的功能模块
                //--1.1 获取系统列表
                List<FunctionalModule> systemList = this.functionalModuleService.selectChildNodeByfmId(0);
                for (int i = 0; i < systemList.size(); i++) {
                    Map<FunctionalModule,List<FunctionalModule>> mapModule = new HashMap<>();

                    FunctionalModule system = systemList.get(i);//--系统名称
                    //--1.2根据系统名称获取一级菜单
                    List<FunctionalModule> firstMenuList = this.functionalModuleService.selectChildNodeByfmId(system.getFmId());
                    List<FunctionalModule> finalMenuList = new ArrayList<>();
                    for (int j = 0; j < firstMenuList.size(); j++) {//--获取二级菜单列表
                        mapMenu.put("fmId", firstMenuList.get(j).getFmId());
                        List<FunctionalModule> secondMenuList = this.functionalModuleService.selectChildNodeByfmIdAndType(mapMenu);
                        finalMenuList.addAll(secondMenuList);
                    }

                    mapModule.put(system,finalMenuList);
                    menuList.add(mapModule);
                }

                //--将登陆的用户保存到session中，将权限保存到session中
                request.getSession().setAttribute("user",user);
                request.getSession().setAttribute("menuList",menuList);

                result.put("code", "0");
                result.put("message", "登录成功！");
                result.put("menuList", menuList);

                return result;
            }else{//--非系统管理员
                result.put("code", "3");
                result.put("message", "您不是系统管理员，禁止登陆后台管理系统！");
                return result;
            }
        }else{//--用户名和密码错误
            result.put("code", "2");
            result.put("message", "用户名或密码错误!");
            return result;
        }
    }

    /**
     * 查询用户列表
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/getUsersData", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BasePage getUsersData(@RequestParam(required = true, defaultValue = "1") Integer page,
                                     @RequestParam(required = true, defaultValue = "10") Integer rows,
                                     Users user) throws Exception {
        PageHelper.startPage(page, rows);
        List<Users> userList = this.userService.getUserList(user);
        BasePage<Users> pi = new BasePage<Users>(userList);
        return pi;
    }

    /**
     * 新增1个用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Object saveUser(@RequestBody Users user,HttpServletRequest request) throws Exception {
        Map map = new HashMap();

        Integer orgId = Integer.valueOf(request.getParameter("orgId"));
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));

        user.setOrgId(orgId);

        /***条件异常判断***/
        if (user == null) {
            map.put("tag", "false");
            map.put("message", "未获取参数");
            return map;
        }
        int count = this.userService.addUser(user);
        if (count > 0) {//--操作成功后，还需将用户的角色保存起来
            //--保存用户的角色，本应该在service中进行保存的，以保证事务的一致性，但为了快速完成，暂时先放在这。
            UserRoleKey userRole = new UserRoleKey();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            if (this.userRoleService.addUserRole(userRole)>0){
                map.put("tag", "true");
                map.put("message", "操作成功！");
            }else{
                map.put("tag", "false");
                map.put("message", "用户角色保存失败！");
            }

        } else {
            map.put("tag", "false");
            map.put("message", "DB操作异常");
        }
        return map;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object updateUser(@RequestBody Users user, HttpServletRequest request) throws Exception {
        Map m = new HashMap();
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        user.setUserId(userId);

        Integer orgId = Integer.valueOf(request.getParameter("orgId"));
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));

        user.setOrgId(orgId);


        /***条件异常判断***/
        if (user == null || (user != null && user.getUserId() <= 0)) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int count = this.userService.update(user);
        if (count > 0) {//--操作成功后，还需将用户的角色保存起来
            //--保存用户的角色，本应该在service中进行保存的，以保证事务的一致性，但为了快速完成，暂时先放在这。
            UserRoleKey userRole = new UserRoleKey();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            if (this.userRoleService.addUserRole(userRole)>0){
                m.put("tag", "true");
                m.put("message", "操作成功！");
            }else{
                m.put("tag", "false");
                m.put("message", "用户角修改存失败！");
            }
        } else {
            m.put("tag", "false");
            m.put("message", "DB操作异常");
        }
        return m;
    }

    /**
     * 删除1条记录
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object delUser(int userId) throws Exception {
        Map m = new HashMap();

        /***条件异常判断***/
        if (userId <= 0) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int ee = this.userService.deleteByPrimaryKey(userId);
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
