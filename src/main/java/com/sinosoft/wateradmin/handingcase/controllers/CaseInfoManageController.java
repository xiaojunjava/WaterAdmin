package com.sinosoft.wateradmin.handingcase.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.CaseInfo;
import com.sinosoft.wateradmin.app.bean.PatrolReport;
import com.sinosoft.wateradmin.app.service.ICaseInfoService;
import com.sinosoft.wateradmin.app.service.IPatrolReportService;
import com.sinosoft.wateradmin.common.BasePage;
import com.sinosoft.wateradmin.common.DateConvert;
import com.sinosoft.wateradmin.handingcase.bean.CaseExamApproval;
import com.sinosoft.wateradmin.handingcase.service.ICaseExamApprovalService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 案件管理_Controller
 * Created by lvzhixue on 2017/11/6.
 */
@Controller
@RequestMapping("/caseinfo")
public class CaseInfoManageController {

    private final static Logger loger = Logger.getLogger(CaseInfoManageController.class);

    //--巡查上报Service
    @Autowired
    private IPatrolReportService patrolReportService;

    //--案件信息Service
    @Autowired
    private ICaseInfoService caseInfoService;

    //--立案审批表Service
    @Autowired
    private ICaseExamApprovalService caseExamApprovalService;

    //--跳转到立案申请页面
    @RequestMapping(value = "/gotoFilingApplicationPage")
    public String gotoFilingApplicationPage(HttpServletRequest request) throws Exception {

        return "handingcase/filingapplication";
    }

    //--跳转到案件受理页面
    @RequestMapping(value = "/gotoCaseacceptance")
    public String gotoCaseacceptance(HttpServletRequest request) throws Exception {

        return "handingcase/caseacceptance";
    }

    //--跳转到调查取证页面
    @RequestMapping(value = "/gotoObtainEvidence")
    public String gotoObtainEvidence(HttpServletRequest request) throws Exception {

        return "handingcase/obtain_evidence";
    }

    //--跳转到案件审批页面
    @RequestMapping(value = "/gotoCaseApproval")
    public String gotoCaseApproval(HttpServletRequest request) throws Exception {

        return "handingcase/case_approval";
    }

    //--跳转到执法范围页面
    @RequestMapping(value = "/gotoScopeOfLawenforcement")
    public String gotoScopeOfLawenforcement(HttpServletRequest request) throws Exception {

        return "handingcase/scope_of_lawenforcement";
    }

    //--跳转到职责页面
    @RequestMapping(value = "/gotoDuty")
    public String gotoDuty(HttpServletRequest request) throws Exception {

        return "handingcase/duty";
    }

    //--跳转到权限页面
    @RequestMapping(value = "/gotoPower")
    public String gotoPower(HttpServletRequest request) throws Exception {

        return "handingcase/power";
    }

    //--跳转到时限页面
    @RequestMapping(value = "/gotoTimelimit")
    public String gotoTimelimit(HttpServletRequest request) throws Exception {

        return "handingcase/timelimit";
    }

    //--跳转到程序页面
    @RequestMapping(value = "/gotoOrder")
    public String gotoOrder(HttpServletRequest request) throws Exception {

        return "handingcase/order";
    }

    //--跳转到结果页面
    @RequestMapping(value = "/gotoResult")
    public String gotoResult(HttpServletRequest request) throws Exception {

        return "handingcase/result";
    }

    //--跳转到执法文书页面
    @RequestMapping(value = "/gotoInformingDocument")
    public String gotoInformingDocument(HttpServletRequest request) throws Exception {

        return "handingcase/Informing_document";
    }

    //--跳转到案件复核页面
    @RequestMapping(value = "/gotoCaseReview")
    public String gotoCaseReview(HttpServletRequest request) throws Exception {

        return "handingcase/case_review";
    }

    //--跳转到处罚处理页面
    @RequestMapping(value = "/gotoPunishHandling")
    public String gotoPunishHandling(HttpServletRequest request) throws Exception {

        return "handingcase/punish_handling";
    }

    //--跳转到强制执行页面
    @RequestMapping(value = "/gotoEnforcement")
    public String gotoEnforcement(HttpServletRequest request) throws Exception {

        return "handingcase/Enforcement";
    }

    //--跳转到结案归档页面
    @RequestMapping(value = "/gotoPlaceOnFile")
    public String gotoPlaceOnFile(HttpServletRequest request) throws Exception {

        return "handingcase/place_on_file";
    }

    //--跳转到处罚对象页面
    @RequestMapping(value = "/gotoObjectOfPunishment")
    public String gotoObjectOfPunishment(HttpServletRequest request) throws Exception {

        return "handingcase/object_of_punishment";
    }

    //--跳转到简易巡查事件页面
    @RequestMapping(value = "/gotoSimpleFilingEvent")
    public String gotoSimpleFilingEvent(HttpServletRequest request) throws Exception {

        return "handingcase/simple_filing_event";
    }

    //--跳转到案件基本情况页面
    @RequestMapping(value = "/gotoCaseBasicSituation")
    public String gotoCaseBasicSituation(HttpServletRequest request) throws Exception {

        return "handingcase/case_basic_situation";
    }

    //--跳转到进度显示页面
    @RequestMapping(value = "/gotoProcessingProgress")
    public String gotoProcessingProgress(HttpServletRequest request) throws Exception {

        return "handingcase/case_processing_progress";
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //--立案申请
    @RequestMapping(value="/applyCase",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody
    Object applyCase(@RequestParam(required = true)int prId, @RequestParam(required = true)String applyReason)throws Exception{
        Map m=new HashMap();

        try {
            //--立案申请，根据巡检上报记录，优先写案件信息表
            PatrolReport patrolReport = this.patrolReportService.selectByPrimaryKey(prId);
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setCiName(DateConvert.date2Str(patrolReport.getPrReportTime()));
            caseInfo.setCiDatetime(patrolReport.getPrReportTime());
            caseInfo.setCiType("0");//--暂无定义
            caseInfo.setCiStatus("1");//--1-立案申请
            caseInfo.setCiContent(patrolReport.getPrSiteDescription());
            caseInfo.setCiPlace(patrolReport.getPrPosition());
            caseInfo.setRemark(patrolReport.getRemark());
            caseInfo.setPrId(patrolReport.getPrId());
            caseInfo.setApplyReason(applyReason);
            this.caseInfoService.insert(caseInfo);

            //--案件信息成功后，修改巡检上报记录的状态为已立案申请
            patrolReport.setStatus(1);//--立案申请
            this.patrolReportService.updateByPrimaryKey(patrolReport);


            m.put("tag", "true");
            m.put("message", "立案申请成功！");
            m.put("prId", patrolReport.getPrId());
        }catch (Exception e){
            m.put("tag", "false");
            m.put("message", "操作失败");
        }

        return m;
    }

    /**
     * 获取待受理的案件记录（查询记录）,getUnDisposeCaseList
     * 立案受理
     * @param caseInfo
     * @return
     * @remark added by lvzhixue, 2017-12-14 16:16
     */
    @RequestMapping(value = "/getUnDisposeCaseList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BasePage getUnDisposeCaseList(@RequestParam(required = true, defaultValue = "1") Integer page,
                                         @RequestParam(required = true, defaultValue = "10") Integer rows,
                                         CaseInfo caseInfo) throws Exception {
        PageHelper.startPage(page, rows);
        List<CaseInfo> ls= this.caseInfoService.getUnDisposeCaseList(caseInfo);
        BasePage<CaseInfo> pi = new BasePage<CaseInfo>(ls);
        return pi;
    }

    /**
     * 获取待取证的案件记录（查询记录）,getEvidenceCaseList
     * 调查取证记录
     * @param caseInfo
     * @return
     * @remark added by lvzhixue, 2017-12-18 10:55
     */
    @RequestMapping(value = "/getEvidenceCaseList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BasePage getEvidenceCaseList(@RequestParam(required = true, defaultValue = "1") Integer page,
                                         @RequestParam(required = true, defaultValue = "10") Integer rows,
                                         CaseInfo caseInfo) throws Exception {
        PageHelper.startPage(page, rows);
        List<CaseInfo> ls= this.caseInfoService.getEvidenceCaseList(caseInfo);
        BasePage<CaseInfo> pi = new BasePage<CaseInfo>(ls);
        return pi;
    }


    /**
     * 获取待取证的案件记录（查询记录）,getApprovalCaseList
     * 调查取证记录
     * @param caseInfo
     * @return
     * @remark added by lvzhixue, 2017-12-18 10:55
     */
    @RequestMapping(value = "/getApprovalCaseList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BasePage getApprovalCaseList(@RequestParam(required = true, defaultValue = "1") Integer page,
                                        @RequestParam(required = true, defaultValue = "10") Integer rows,
                                        CaseInfo caseInfo) throws Exception {
        PageHelper.startPage(page, rows);
        List<CaseInfo> ls= this.caseInfoService.getApprovalCaseList(caseInfo);
        BasePage<CaseInfo> pi = new BasePage<CaseInfo>(ls);
        return pi;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 立案受理-通过
     * @param prId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/auditAdopt",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody
    Object auditAdopt(@RequestParam(required = true)int ciId,@RequestParam(required = true)int prId)throws Exception{
        Map m=new HashMap();

        try {
            //--修改案件状态为立案通过
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setCiId(ciId);
            caseInfo.setCiStatus(String.valueOf(2));//1-立案申请，2--案件处理中，3-案件结案，4-无效案件,5-驳回
            this.caseInfoService.updateByPrimaryKeySelective(caseInfo);

            //--修改巡查上报状态为立案通过
            PatrolReport patrolReport = new PatrolReport();
            patrolReport.setPrId(prId);
            patrolReport.setStatus(2);//0--巡查上报 1--立案申请 2--立案通过 3--立案驳回
            this.patrolReportService.updateByPrimaryKey(patrolReport);

            m.put("tag", "true");
            m.put("message", "立案受理通过成功！");
        }catch (Exception e){
            m.put("tag", "false");
            m.put("message", "操作失败");
        }

        return m;
    }

    /**
     * 立案受理-驳回
     * @param prId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/auditRefuse",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody
    Object auditRefuse(@RequestParam(required = true)int ciId,@RequestParam(required = true)int prId)throws Exception{
        Map m=new HashMap();

        try {
            //--删除立案申请记录
            this.caseInfoService.deleteByPrimaryKey(ciId);

            //--修改巡查上报状态为立案驳回
            PatrolReport patrolReport = new PatrolReport();
            patrolReport.setPrId(prId);
            patrolReport.setStatus(3);//0--巡查上报 1--立案申请 2--立案通过 3--立案驳回
            this.patrolReportService.updateByPrimaryKey(patrolReport);

            m.put("tag", "true");
            m.put("message", "立案受理驳回成功！");
        }catch (Exception e){
            m.put("tag", "false");
            m.put("message", "操作失败");
        }

        return m;
    }


    /**
     * 调查取证-分配取证人

     * @return
     * @throws Exception
     */
    @RequestMapping(value="/specifyUser")
    public @ResponseBody
    Object specifyUser(HttpServletRequest request, HttpServletResponse response)throws Exception{

        int ciId = Integer.parseInt(request.getParameter("ciId"));
        int prId = Integer.parseInt(request.getParameter("prId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");


        Map m=new HashMap();

        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setCiId(ciId);
            caseInfo.setUserId(userId);
            caseInfo.setUserName(userName);

            this.caseInfoService.updateByPrimaryKeySelective(caseInfo);

            m.put("tag", "true");
            m.put("message", "分配取证人成功！");
        }catch (Exception e){
            m.put("tag", "false");
            m.put("message", "操作失败");
        }

        return m;
    }


    /**
     * 案件审批-通过
     * @param prId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/approvalCaseAdopt",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody
    Object approvalCaseAdopt(@RequestParam(required = true)int ciId,@RequestParam(required = true)int prId)throws Exception{
        Map m=new HashMap();

        try {
            //--修改案件状态为案件审核通过
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setCiId(ciId);
            caseInfo.setCiStatus(String.valueOf(6));//1-立案申请，2--案件处理中，3-案件结案，4-无效案件,5-立案申请驳回，6-案件审核通过，7--案件审核驳回
            this.caseInfoService.updateByPrimaryKeySelective(caseInfo);

            /*//--修改巡查上报状态为立案通过
            PatrolReport patrolReport = new PatrolReport();
            patrolReport.setPrId(prId);
            patrolReport.setStatus(2);//0--巡查上报 1--立案申请 2--立案通过 3--立案驳回
            this.patrolReportService.updateByPrimaryKey(patrolReport);*/

            m.put("tag", "true");
            m.put("message", "立案受理通过成功！");
        }catch (Exception e){
            m.put("tag", "false");
            m.put("message", "操作失败");
        }

        return m;
    }

    /**
     * 案件审批-驳回
     * @param prId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/approvalCaseRefuse",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    public @ResponseBody
    Object approvalCaseRefuse(@RequestParam(required = true)int ciId,@RequestParam(required = true)int prId)throws Exception{
        Map m=new HashMap();

        try {
            //--删除案件记录，如需立案，重新走立案申请流程。注意：可能需要先删除取证记录。
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setCiId(ciId);
            caseInfo.setCiStatus(String.valueOf(7));//1-立案申请，2--案件处理中，3-案件结案，4-无效案件,5-立案申请驳回，6-案件审核通过，7--案件审核驳回
            this.caseInfoService.updateByPrimaryKeySelective(caseInfo);
            //this.caseInfoService.deleteByPrimaryKey(ciId);

            //--修改巡查上报状态为立案驳回
            PatrolReport patrolReport = new PatrolReport();
            patrolReport.setPrId(prId);
            patrolReport.setStatus(4);//0--巡查上报 1--立案申请 2--立案通过 3--立案驳回 4--案件审批驳回
            this.patrolReportService.updateByPrimaryKey(patrolReport);

            m.put("tag", "true");
            m.put("message", "案件审批驳回成功！");
        }catch (Exception e){
            m.put("tag", "false");
            m.put("message", "操作失败");
        }

        return m;
    }

    /**
     * 立案申请--提交立案审批表
     */
    @RequestMapping(value = "/submitExamApprovalCase", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    Object submitExamApprovalCase(@RequestParam(required = true) int prId, @RequestBody CaseExamApproval caseExamApproval) throws Exception {
        Map map = new HashMap();

        /***条件异常判断***/
        if (caseExamApproval == null) {
            map.put("tag", "false");
            map.put("message", "未获取参数");
            return map;
        }
        //--第一步、写立案审批表，然后获取立案审批的id编号
        caseExamApproval.setPrid(prId);//--巡检上报记录
        caseExamApproval.setStatus("0");//--状态
        this.caseExamApprovalService.insert(caseExamApproval);

        //--第二步、立案，写案件信息表
        //--立案申请，根据巡检上报记录，优先写案件信息表
        PatrolReport patrolReport = this.patrolReportService.selectByPrimaryKey(prId);
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setCiName(DateConvert.date2Str(patrolReport.getPrReportTime()));
        caseInfo.setCiDatetime(patrolReport.getPrReportTime());
        caseInfo.setCiType("0");//--暂无定义
        caseInfo.setCiStatus("1");//--1-立案申请
        caseInfo.setCiContent(patrolReport.getPrSiteDescription());
        caseInfo.setCiPlace(patrolReport.getPrPosition());
        caseInfo.setRemark(patrolReport.getRemark());
        caseInfo.setPrId(patrolReport.getPrId());
        caseInfo.setApplyReason("");
        caseInfo.setCeaId(caseExamApproval.getCeaId());
        this.caseInfoService.insert(caseInfo);

        //--第三步、案件信息成功后，修改巡检上报记录的状态为已立案申请
        patrolReport.setStatus(1);//--立案申请
        int count = this.patrolReportService.updateByPrimaryKey(patrolReport);

        if (count > 0) {
            map.put("tag", "true");
            map.put("message", "操作成功");
        } else {
            map.put("tag", "false");
            map.put("message", "DB操作异常");
        }
        return map;
    }

}
