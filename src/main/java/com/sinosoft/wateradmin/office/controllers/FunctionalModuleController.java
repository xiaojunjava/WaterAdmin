package com.sinosoft.wateradmin.office.controllers;

import com.sinosoft.wateradmin.app.bean.FunctionalModule;
import com.sinosoft.wateradmin.app.service.IFunctionalModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：功能模块
 *
 * @author lvzhixue
 * @date 2017-11-29 9:50
 */
@Controller
@RequestMapping("/module")
public class FunctionalModuleController {

    //--功能模块服务接口
    @Autowired
    private IFunctionalModuleService functionalModuleService;

    //--跳转到功能模块页面
    @RequestMapping(value = "/gotoFunctionalModule")
    public String gotoFunctionalModule(HttpServletRequest request) throws Exception {

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

        request.getSession().setAttribute("functionModule",systemList);

        return "office/functionalModule";
    }

    /**
     * 新增模块
     *
     * @param functionalModule
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveFunctionalModule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object saveFunctionalModule(@RequestBody FunctionalModule functionalModule, HttpServletRequest request) throws Exception {
        Map map = new HashMap();

        Integer parFmId = Integer.valueOf(request.getParameter("parFmId"));
        Integer fmDepth = Integer.valueOf(request.getParameter("fmDepth"));

        functionalModule.setParFmId(parFmId);
        functionalModule.setFmDepth(fmDepth);

        /***条件异常判断***/
        if (functionalModule == null) {
            map.put("tag", "false");
            map.put("message", "未获取参数");
            return map;
        }
        int count = this.functionalModuleService.insert(functionalModule);
        if (count > 0) {
            map.put("tag", "true");
            map.put("message", "操作成功");
            map.put("functionalModule",functionalModule);
        } else {
            map.put("tag", "false");
            map.put("message", "DB操作异常");
        }
        return map;
    }

    @RequestMapping(value = "/updateFunctionalModule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object updateFunctionalModule(@RequestBody FunctionalModule functionalModule, HttpServletRequest request) throws Exception {
        Map m = new HashMap();
        Integer parFmId = Integer.valueOf(request.getParameter("parFmId"));
        Integer fmDepth = Integer.valueOf(request.getParameter("fmDepth"));
        Integer fmId = Integer.valueOf(request.getParameter("fmId"));

        functionalModule.setFmId(fmId);
        functionalModule.setParFmId(parFmId);
        functionalModule.setFmDepth(fmDepth);

        /***条件异常判断***/
        if (functionalModule == null || (functionalModule != null && functionalModule.getFmId() <= 0)) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int count = this.functionalModuleService.updateByPrimaryKeySelective(functionalModule);
        if (count > 0) {
            m.put("tag", "true");
            m.put("message", "操作成功");
            m.put("functionalModule",functionalModule);
        } else {
            m.put("tag", "false");
            m.put("message", "DB操作异常");
        }
        return m;
    }

    /**
     * 删除1条记录
     *
     * @param fmId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delFunctionalModule", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object delFunctionalModule(int fmId) throws Exception {
        Map m = new HashMap();

        /***条件异常判断***/
        if (fmId <= 0) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int ee = this.functionalModuleService.deleteByPrimaryKey(fmId);
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