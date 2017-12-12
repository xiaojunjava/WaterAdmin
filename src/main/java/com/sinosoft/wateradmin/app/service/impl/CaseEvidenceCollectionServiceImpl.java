package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.CaseEvidenceCollection;
import com.sinosoft.wateradmin.app.dao.ICaseEvidenceCollectionDAO;
import com.sinosoft.wateradmin.app.service.ICaseEvidenceCollectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 证据采集——service
 * Created by lvzhixue on 2017/11/3.
 */
@Service
@Transactional
public class CaseEvidenceCollectionServiceImpl implements ICaseEvidenceCollectionService {

    @Resource
    private ICaseEvidenceCollectionDAO caseEvidenceCollectionMapper;

    @Override
    public Integer saveEvidence(CaseEvidenceCollection caseCollection) {
        return caseEvidenceCollectionMapper.insertRecord(caseCollection);
    }
}
