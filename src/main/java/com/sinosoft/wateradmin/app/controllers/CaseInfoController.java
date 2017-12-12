package com.sinosoft.wateradmin.app.controllers;

import com.sinosoft.wateradmin.app.bean.CaseInfo;
import com.sinosoft.wateradmin.app.service.ICaseInfoService;
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
 * 案件信息_controller
 * Created by lvzhixue on 2017/11/6.
 */
@Controller
@RequestMapping("/app")
public class CaseInfoController {

    private final static Logger logger = Logger.getLogger(CaseInfoController.class);

    //--案件信息Service
    @Autowired
    private ICaseInfoService caseInfoService;


    /**
     * "获取待取证案件的列表"
     * @author lvzhixue
     * @datetime 2017-11-06
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getCaseInfoList")
    @ResponseBody
    public List<CaseInfo> getCaseInfoList(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //--获取未处理的案件列表
        List<CaseInfo>  caseInfos = caseInfoService.getUntreatedCaseInfoList();

        return caseInfos;
    }
}

