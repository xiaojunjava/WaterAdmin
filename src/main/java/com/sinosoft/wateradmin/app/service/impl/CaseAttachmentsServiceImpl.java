package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.CaseAttachments;
import com.sinosoft.wateradmin.app.dao.ICaseAttachmentsDAO;
import com.sinosoft.wateradmin.app.service.ICaseAttachmentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 证据采集——service
 * Created by lvzhixue on 2017/11/3.
 */
@Service
@Transactional
public class CaseAttachmentsServiceImpl implements ICaseAttachmentsService {

    @Resource
    private ICaseAttachmentsDAO caseAttachmentsMapper;

    @Override
    public Integer saveAttachments(CaseAttachments caseAttachments) {
        return caseAttachmentsMapper.insert(caseAttachments);
    }
}
