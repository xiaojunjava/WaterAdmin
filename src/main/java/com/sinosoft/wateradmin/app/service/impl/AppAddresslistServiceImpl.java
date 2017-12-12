package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppShipmasterAddresslist;
import com.sinosoft.wateradmin.app.bean.AppTipstaffAddresslist;
import com.sinosoft.wateradmin.app.bean.AppWateradminAddresslist;
import com.sinosoft.wateradmin.app.dao.IAppShipmasterAddresslistDAO;
import com.sinosoft.wateradmin.app.dao.IAppTipstaffAddresslistDAO;
import com.sinosoft.wateradmin.app.dao.IAppWateradminAddresslistDAO;
import com.sinosoft.wateradmin.app.service.IAppAddresslistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * app通讯录——service
 * Created by lvzhixue on 2017/11/6.
 */
@Service
@Transactional
public class AppAddresslistServiceImpl  implements IAppAddresslistService{

    @Resource
    private IAppShipmasterAddresslistDAO appShipmasterAddresslistDAO;

    @Resource
    private IAppTipstaffAddresslistDAO appTipstaffAddresslistDAO;

    @Resource
    private IAppWateradminAddresslistDAO appWateradminAddresslistDAO;


    /**
     * 水政单位通讯录
     *
     * @return
     */
    @Override
    public List<AppWateradminAddresslist> getAppWateradminAddresslist() {

        return appWateradminAddresslistDAO.getAppWateradminAddresslist();
    }

    /**
     * 执法人员通讯录
     *
     * @return
     */
    @Override
    public List<AppTipstaffAddresslist> getAppTipstaffAddresslist() {

        return appTipstaffAddresslistDAO.getAppTipstaffAddresslist();
    }

    /**
     * 采沙船主通讯录
     *
     * @return
     */
    @Override
    public List<AppShipmasterAddresslist> getCaiShipmasterAddresslist() {

        return appShipmasterAddresslistDAO.getCaiShipmasterAddresslist();
    }

    /**
     * 运沙船主通讯录
     *
     * @return
     */
    @Override
    public List<AppShipmasterAddresslist> getYunShipmasterAddresslist() {

        return appShipmasterAddresslistDAO.getYunShipmasterAddresslist();
    }
}
