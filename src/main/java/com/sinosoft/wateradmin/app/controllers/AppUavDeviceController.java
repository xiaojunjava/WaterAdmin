package com.sinosoft.wateradmin.app.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.AppUavDevice;
import com.sinosoft.wateradmin.app.service.IAppUavDeviceService;
import com.sinosoft.wateradmin.common.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：无人机设备
 *
 * @author lvzhixue
 * @date 2017-11-24 16:44
 */
@Controller
@RequestMapping("/uavDevice")
public class AppUavDeviceController {

    //--无人机设备服务接口
    @Autowired
    private IAppUavDeviceService appUavDeviceService;

    //--跳转到无人机设备管理页面
    @RequestMapping(value = "/goToUAVPage")
    public String goToUAVPage() throws Exception {
        return "app/appUavDevice";
    }

    /**
     * 查询无人机设备
     *
     * @param appUavDevice
     * @return
     */
    @RequestMapping(value = "/getUavDeviceData", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BasePage getUavDeviceData(@RequestParam(required = true, defaultValue = "1") Integer page,
                              @RequestParam(required = true, defaultValue = "10") Integer rows,
                              AppUavDevice appUavDevice) throws Exception {
        PageHelper.startPage(page, rows);
        List<AppUavDevice> appUavDeviceList = this.appUavDeviceService.selectDatas(appUavDevice);
        BasePage<AppUavDevice> pi = new BasePage<AppUavDevice>(appUavDeviceList);
        return pi;
    }

    /**
     * 新增1架无人机信息
     *
     * @param appUavDevice
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveUAV", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody Object saveUAV(@RequestBody AppUavDevice appUavDevice) throws Exception {
        Map map = new HashMap();

        /***条件异常判断***/
        if (appUavDevice == null) {
            map.put("tag", "false");
            map.put("message", "未获取参数");
            return map;
        }
        int count = appUavDeviceService.insert(appUavDevice);
        if (count > 0) {
            map.put("tag", "true");
            map.put("message", "操作成功");
        } else {
            map.put("tag", "false");
            map.put("message", "DB操作异常");
        }
        return map;
    }

    @RequestMapping(value = "/updateUAV", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object updateUAV(@RequestBody AppUavDevice appUavDevice, HttpServletRequest request) throws Exception {
        Map m = new HashMap();
        Integer uavId = Integer.valueOf(request.getParameter("uavId"));
        appUavDevice.setUavId(uavId);

        /***条件异常判断***/
        if (appUavDevice == null || (appUavDevice != null && appUavDevice.getUavId() <= 0)) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int count = appUavDeviceService.update(appUavDevice);
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
     * @param uavId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delUAV", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object delUAV(int uavId) throws Exception {
        Map m = new HashMap();

        /***条件异常判断***/
        if (uavId <= 0) {
            m.put("tag", "false");
            m.put("message", "参数获取异常");
            return m;
        }
        int ee = this.appUavDeviceService.deleteByPrimaryKey(uavId);
        if (ee > 0) {
            m.put("tag", "true");
            m.put("message", "操作成功");
        } else {
            m.put("tag", "false");
            m.put("message", "DB操作异常");
        }
        return m;
    }

    /**
     * 得到一个AppUavDevice对象
     *
     * @param uavId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUAV", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    AppUavDevice getUAV(int uavId) throws Exception {
        if (uavId <= 0) return null;
        AppUavDevice appUavDevice = this.appUavDeviceService.selectByPrimaryKey(uavId);
        return appUavDevice;
    }

}