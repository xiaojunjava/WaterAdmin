package com.sinosoft.wateradmin.office.controllers;

import com.sinosoft.wateradmin.app.bean.Organization;
import com.sinosoft.wateradmin.app.bean.Users;
import com.sinosoft.wateradmin.app.dao.IOrganizationDAO;
import com.sinosoft.wateradmin.app.service.IFunctionalModuleService;
import com.sinosoft.wateradmin.app.service.IRoleService;
import com.sinosoft.wateradmin.app.service.IUserService;
import com.sinosoft.wateradmin.common.ComboTreeModel;
import com.sinosoft.wateradmin.common.DateConvert;
import com.sinosoft.wateradmin.office.service.IOrganizationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 机构管理_Controller
 * Created by lvzhixue on 2017/11/6.
 */
@Controller
@RequestMapping("/org")
public class OrganizationController {

    private final static Logger loger = Logger.getLogger(OrganizationController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IFunctionalModuleService functionalModuleService;

    @Autowired
    private IOrganizationService organizationService;


    //--跳转到机构管理页面
    @RequestMapping(value = "/gotoOrgPage")
    public String gotoUsersPage(HttpServletRequest request) throws Exception {
        return "office/org";
    }

    /**
     * 查询机构树列表
     *
     * @param r
     * @return
     */
    @RequestMapping(value = "/getOrgTreeList")
    @ResponseBody
    public List<ComboTreeModel> getOrgTreeList(HttpServletRequest request, HttpServletResponse response, ComboTreeModel treeModel){

        List<ComboTreeModel> list = getComboTreeNode(0);

        return list;
    }

    /**
     * 递归查询机构树
     * @return
     */
    private List<ComboTreeModel> getComboTreeNode(Integer orgId){

        //--获取当前节点的所有子节点
        List<Organization> orgList = this.organizationService.getChildByOrgId(orgId);
        List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();//--用于保存orgList查询的结果
        for (int i = 0; i < orgList.size(); i++) {
            Organization org = orgList.get(i);
            ComboTreeModel model = new ComboTreeModel();
            model.setId(String.valueOf(org.getOrgId()));
            model.setText(org.getOrgName());
            if (this.organizationService.hasChild(org.getOrgId())>0){
                List<ComboTreeModel> childList = getComboTreeNode(org.getOrgId());
                model.setChildren(childList);
            }
            list.add(model);
        }
        return list;
    }


    /**
     * 增加机构
     * Created by lvzhixue on 2017/11/6.
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Map<String, Object> addUser(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();

        //--获取界面传过来的参数
        Integer orgId = Integer.parseInt(request.getParameter("orgId").toString());

        String userLoginname = request.getParameter("userLoginname").toString();

        String userPsw = request.getParameter("userPsw").toString();

        String userName = request.getParameter("userName").toString();

        String sex = request.getParameter("sex").toString();

        String phoneNumber = request.getParameter("phoneNumber").toString();

        Date birthday = DateConvert.StrToDate(request.getParameter("birthday").toString());

        //--判断机构登录名是否存在，不存在才可以添加，存在则提示操作员
        if (this.userService.isUserLoginnameExist(userLoginname)>0){//--已存在
            result.put("code", "2");
            result.put("message", "机构已存在！");
            return result;
        }else{//--不存在
            //--不存在时，添加机构
            Users user = new Users();
            user.setUserLoginname(userLoginname);//--登录名
            user.setUserName(userName);//--机构名
            user.setUserPsw(userPsw);//--密码
            user.setSex(sex);//--性别
            user.setOrgId(orgId);//--所属机构
            user.setPhoneNumber(phoneNumber);//--电话号码
            user.setBirthday(birthday);//--出生日期


            if (this.userService.addUser(user)>0){//--返回值大于0，代表添加成功
                result.put("code", "0");
                result.put("message", "添加成功！");
                return result;
            }else{//--添加失败
                result.put("code", "1");
                result.put("message", "添加失败！");
                return result;
            }
        }
    }

    /**
     * 新增1个机构
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Object saveUser(@RequestBody Users user) throws Exception {
        Map map = new HashMap();

        /***条件异常判断***/
        if (user == null) {
            map.put("tag", "false");
            map.put("message", "未获取参数");
            return map;
        }
        int count = this.userService.addUser(user);
        if (count > 0) {
            map.put("tag", "true");
            map.put("message", "操作成功");
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

        /***条件异常判断***/
        if (user == null || (user != null && user.getUserId() <= 0)) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int count = this.userService.update(user);
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
