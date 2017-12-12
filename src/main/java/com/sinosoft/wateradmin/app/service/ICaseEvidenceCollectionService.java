package com.sinosoft.wateradmin.app.service;

import com.sinosoft.wateradmin.app.bean.CaseEvidenceCollection;

/**
 * 证据采集——接口
 * Created by lvzhixue on 2017/11/3.
 */
public interface ICaseEvidenceCollectionService {

    public Integer saveEvidence(CaseEvidenceCollection caseCollection);
}
