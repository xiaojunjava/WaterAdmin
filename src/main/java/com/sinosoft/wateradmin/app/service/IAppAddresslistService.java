package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.AppShipmasterAddresslist;
import com.sinosoft.wateradmin.app.bean.AppTipstaffAddresslist;
import com.sinosoft.wateradmin.app.bean.AppWateradminAddresslist;

import java.util.List;

/**
 * app通讯录_接口
 * Created by lvzhixue on 2017/11/6.
 */
public interface IAppAddresslistService {

    /**
     * 水政单位通讯录
     * @return
     */
    List<AppWateradminAddresslist> getAppWateradminAddresslist();

    /**
     * 执法人员通讯录
     * @return
     */
    List<AppTipstaffAddresslist> getAppTipstaffAddresslist();

    /**
     * 采沙船主通讯录
     * @return
     */
    List<AppShipmasterAddresslist> getCaiShipmasterAddresslist();

    /**
     * 运沙船主通讯录
     * @return
     */
    List<AppShipmasterAddresslist> getYunShipmasterAddresslist();
}
