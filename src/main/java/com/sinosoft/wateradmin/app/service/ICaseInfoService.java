package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.CaseInfo;

import java.util.List;

/**
 * 案件信息——接口
 * Created by lvzhixue on 2017/11/6.
 */
public interface ICaseInfoService {

    public List<CaseInfo> getUntreatedCaseInfoList();

}
