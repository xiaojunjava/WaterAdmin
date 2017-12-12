package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.CaseAttachments;

/**
 * 证据采集附件——接口
 * Created by lvzhixue on 2017/11/3.
 */
public interface ICaseAttachmentsService {

    public Integer saveAttachments(CaseAttachments caseAttachments);
}
