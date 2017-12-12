package com.sinosoft.wateradmin.app.controllers;

import com.sinosoft.wateradmin.app.bean.AppShipmasterAddresslist;
import com.sinosoft.wateradmin.app.bean.AppTipstaffAddresslist;
import com.sinosoft.wateradmin.app.bean.AppWateradminAddresslist;
import com.sinosoft.wateradmin.app.service.IAppAddresslistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * app通讯录_Controller
 * Created by lvzhixue on 2017/11/6.
 * 服务于手机app
 */
@Controller
@RequestMapping("/app")
public class AppAddresslistController {


    private final static Logger logger = Logger.getLogger(AppAddresslistController.class);

    //--案件信息Service
    @Autowired
    private IAppAddresslistService appAddresslistService;


    /**
     * "获取水政单位通讯录列表"
     * @author lvzhixue
     * @datetime 2017-11-06
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getAppWateradminAddresslist")
    @ResponseBody
    public List<AppWateradminAddresslist> getAppWateradminAddresslist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //--获取水政单位通讯录列表
        List<AppWateradminAddresslist>  appWateradminAddresslists = appAddresslistService.getAppWateradminAddresslist();

        return appWateradminAddresslists;
    }

    /**
     * "获取执法人员通讯录列表"
     * @author lvzhixue
     * @datetime 2017-11-06
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getAppTipstaffAddresslist")
    @ResponseBody
    public List<AppTipstaffAddresslist> getAppTipstaffAddresslist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //--获取执法人员通讯录列表
        List<AppTipstaffAddresslist>  appTipstaffAddresslists = appAddresslistService.getAppTipstaffAddresslist();

        return appTipstaffAddresslists;
    }

    /**
     * "获取采沙船主通讯录列表"
     * @author lvzhixue
     * @datetime 2017-11-06
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getCaiShipmasterAddresslist")
    @ResponseBody
    public List<AppShipmasterAddresslist> getCaiShipmasterAddresslist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //--获取采沙船主通讯录列表
        List<AppShipmasterAddresslist>  appShipmasterAddresslists = appAddresslistService.getCaiShipmasterAddresslist();

        return appShipmasterAddresslists;
    }
    /**
     * "获取运沙船主通讯录列表"
     * @author lvzhixue
     * @datetime 2017-11-06
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getYunShipmasterAddresslist")
    @ResponseBody
    public List<AppShipmasterAddresslist> getYunShipmasterAddresslist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //--获取未处理的案件列表
        List<AppShipmasterAddresslist>  appShipmasterAddresslists = appAddresslistService.getYunShipmasterAddresslist();

        return appShipmasterAddresslists;
    }
}
