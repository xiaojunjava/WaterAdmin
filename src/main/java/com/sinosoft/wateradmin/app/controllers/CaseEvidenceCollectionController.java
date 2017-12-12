package com.sinosoft.wateradmin.app.controllers;

import com.sinosoft.wateradmin.app.bean.CaseAttachments;
import com.sinosoft.wateradmin.app.bean.CaseEvidenceCollection;
import com.sinosoft.wateradmin.app.service.ICaseAttachmentsService;
import com.sinosoft.wateradmin.app.service.ICaseEvidenceCollectionService;
import com.sinosoft.wateradmin.common.DateConvert;
import com.sinosoft.wateradmin.common.PropertiesUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 调查取证_controller
 * Created by lvzhixue on 2017/11/2.
 */
@Controller
@RequestMapping("/app")
public class CaseEvidenceCollectionController {

    private final static Logger logger = Logger.getLogger(CaseEvidenceCollectionController.class);

    @Autowired
    private ICaseEvidenceCollectionService caseEvidenceCollectionService;

    @Autowired
    private ICaseAttachmentsService caseAttachmentsService;

    /**
     * "现场取证"-保存功能
     * @author lvzhixue
     * @datetime 2017-11-03
     * 步骤：1、客户端先将基本信息发送到后台进行保存，保存后将生成的现场取证记录主键返回给客户端；
     *      2、客户端收到现场取证记录id后，将对应的图片、录音、视频等内容发送给服务端进行保存；
     *      3、服务端收到客户端发送的图片、录音和视频保存请求后将内容保存到图片服务器上，同时保存现场取证附件表，完成现场取证的保存功能。
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveEvidence")
    @ResponseBody
    public String saveEvidence(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //--获取现场取证参数
        //--登陆人
        String loginName = request.getParameter("loginName").toString();

        //--案件序号	ci_id
        Integer ciId = Integer.valueOf(request.getParameter("ciId").toString());

        //--记录名称	ec_name
        String ecName = request.getParameter("ecName").toString();

        //--记录时间	ec_datetime
        String ecDatetime = request.getParameter("ecDatetime").toString();

        //--记录地点	ec_place
        String ecPlace = request.getParameter("ecPlace").toString();

        //--记录内容	ec_content
        String ecContent = request.getParameter("ecContent").toString();

        //--记录人	ec_rec_person
        String ecRecPerson = request.getParameter("ecRecPerson").toString();

        //--记录类型	ec_type
        String ecType = request.getParameter("ecType").toString();

        //--备注	remark
        String remark = request.getParameter("remark").toString();

        //--调试信息
        logger.info(ciId);
        logger.info(ecName);
        logger.info(ecDatetime);
        logger.info(ecPlace);
        logger.info(ecContent);
        logger.info(ecRecPerson);
        logger.info(ecType);
        logger.info(remark);

        //--将现场取证记录保存到数据库
        CaseEvidenceCollection caseCollection = new CaseEvidenceCollection();
        caseCollection.setCiId(ciId);//--案件序号
        caseCollection.setEcName(ecName);//--记录名称
        caseCollection.setEcDatetime(DateConvert.StrToDate("2017-01-01 10:10:10"));//--记录时间
        caseCollection.setEcPlace(ecPlace);//--地点
        caseCollection.setEcContent(ecContent);//--内容
        caseCollection.setEcRecPerson(ecRecPerson);//--记录人
        caseCollection.setEcType(ecType);//--记录类型
        caseCollection.setRemark(remark);//--备注

        caseEvidenceCollectionService.saveEvidence(caseCollection);
        logger.info(caseCollection.getEcId());

        //--获取附件信息，保存附件
        List<CaseAttachments> attachMentsList = (List<CaseAttachments>) request.getSession().getAttribute(ciId+"_"+loginName);
        if (attachMentsList != null){//--说明有附件
            for (int i = 0; i < attachMentsList.size(); i++) {
                CaseAttachments caseAttachments = attachMentsList.get(i);
                caseAttachments.setEcId(caseCollection.getEcId());
                this.caseAttachmentsService.saveAttachments(caseAttachments);
            }
        }

        request.getSession().setAttribute(ciId+"_"+loginName,null);

        return "ok";
    }

    /**
     * 上传资料证据,并暂时将附件记录保存到session中
     * @author lvzhixue
     * @time 2017-11-03 15:57     *
     * @updatetime 2017-11-09 13:41     *
     */
    @RequestMapping(value = "app/uploadEvidence")
    @ResponseBody
    public String uploadEvidence(
            @RequestParam MultipartFile photoFile, HttpServletRequest request,
            HttpServletResponse response, ModelMap map) {

        Integer ciId = Integer.parseInt(request.getParameter("ciId").toString()); //--案件序号
        String loginName = request.getParameter("loginName").toString();//--登陆人员

        Date ecaDatetime = new Date();//--附件上传时间
        String ecaPosition = "";//--附件存放位置
        String remark = "";//--备注


        String fileName;
        Map<String, Object> reslut = new HashMap<String, Object>();
        // --文件名
        String orgFileName = photoFile.getOriginalFilename();

        // --后缀
        String suffix = orgFileName.substring(orgFileName.lastIndexOf(".")).toLowerCase();
        if (!suffix.equals(".jpg") && !suffix.equals(".bmp") && !suffix.equals(".png") && !suffix.equals(".mp4")  && !suffix.equals(".mp3")) {//--！！！文件类型为图片，录音，录像
            reslut.put("code", "1");
            reslut.put("message", "文件格式不正确");
            return "format error";
        } else {
            // --文件路径
            String tmpPath = PropertiesUtils.getString("caseAttachmentsUploadDir")
                    + File.separatorChar + ciId;//--一个案件一个取证目录

            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            String ext = orgFileName.substring(
                    orgFileName.lastIndexOf(".") + 1, orgFileName.length());
            // 对扩展名进行小写转换
            ext = ext.toLowerCase();
            fileName = new Date().getTime() + "." + ext;

            String tmpFileName = tmpPath + File.separatorChar + fileName;
            ecaPosition = tmpFileName;//--保存位置

            File tempFile = new File(tmpFileName);
            try {
                InputStream inputStream = photoFile.getInputStream();
                int available = inputStream.available() / 1024 / 1024;
                if (available > 300) {//--文件大小判断
                    reslut.put("code", "2");
                    reslut.put("message", "文件大小超过300MB");
                    return "file too large";
                }
                FileUtils.copyInputStreamToFile(photoFile.getInputStream(),
                        tempFile);

            } catch (IOException e) {
                reslut.put("code", "3");
                reslut.put("message", "文件上传失败");
                return "exception";
            }
        }

        List<CaseAttachments> attachMentsList = (List<CaseAttachments>) request.getSession().getAttribute(ciId+"_"+loginName);
        if (attachMentsList == null){//--session中没有值，说明是第一次上传，需要创建该对象
            attachMentsList = new ArrayList<CaseAttachments>();
        }
        //--设置附件信息，然后添加到list中，并保存到session中。
        CaseAttachments caseAttachments = new CaseAttachments();
        caseAttachments.setName(fileName);
        caseAttachments.setEcaDatetime(ecaDatetime);
        caseAttachments.setEcaPosition(ecaPosition);
        caseAttachments.setRemark(remark);

        attachMentsList.add(caseAttachments);

        request.getSession().setAttribute(ciId+"_"+loginName,attachMentsList);

        logger.info(caseAttachments.getName());

        reslut.put("code", "0");
        reslut.put("message", "upload done!");

        return "ok";
    }
}

