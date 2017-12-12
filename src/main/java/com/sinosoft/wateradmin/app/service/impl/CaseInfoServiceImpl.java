package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.CaseInfo;
import com.sinosoft.wateradmin.app.dao.ICaseInfoDAO;
import com.sinosoft.wateradmin.app.service.ICaseInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 案件信息——service
 * Created by lvzhixue on 2017/11/6.
 */
@Service
@Transactional
public class CaseInfoServiceImpl implements ICaseInfoService {

    @Resource
    private ICaseInfoDAO caseInfoDAO;


    @Override
    public List<CaseInfo> getUntreatedCaseInfoList() {

        return caseInfoDAO.getUntreatedCaseInfoList();
    }
}
