package com.sinosoft.wateradmin.app.controllers;

import com.sinosoft.wateradmin.app.bean.PatrolReportAttachments;
import com.sinosoft.wateradmin.app.service.IPatrolReportAttachmentsService;
import com.sinosoft.wateradmin.common.PropertiesUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 控制层：巡查上报附件
 * <p>Restful形式实现</p>
 *@author lkj
 */
@Controller
@RequestMapping("/app")
public class PatrolReportAttachmentsController {
	@Autowired
	private IPatrolReportAttachmentsService patrolReportAttachmentsService;

	/**
	 *获取巡查上报附件（查询记录）
	 * @param pra
	 * @return
	 */
	@RequestMapping(value="/getPatrolReportAttachmentsData",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody List<PatrolReportAttachments> getPatrolReportDatas(PatrolReportAttachments pra) throws Exception{
		List<PatrolReportAttachments>  ls=patrolReportAttachmentsService.selectDatas(pra);
		return ls;
	}
	/**
	 * 巡查上报的附件上传
	 * @param photoFiles 上传文件
	 * @param prId 上报记录id
	 * @return
	 */
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object uploadFile(@RequestParam MultipartFile[] photoFiles ,int prId,String remark) throws Exception{
		//定义返回对象
		Map reslut=new HashMap();
		if(prId<=0){
			//photoFile不选文件貌似也不为空，所以不判断文件了
			reslut.put("tag","false");
			reslut.put("message","未获取参数");
			return reslut;
		}
		if(photoFiles!=null&&photoFiles.length>0){
			List<PatrolReportAttachments> ls=new ArrayList<PatrolReportAttachments>();
			MultipartFile photoFile=null;
			for(int i=0;i<photoFiles.length;i++){
				//定义部分当前附件属性信息
				int praType=1;//志学定义？
				String position="";
				photoFile=photoFiles[i];

				String orgFileName = photoFile.getOriginalFilename();// 文件名
				if(orgFileName==null||(orgFileName!=null&&"".equals(orgFileName))){
					reslut.put("tag","false");
					reslut.put("message","未获取文件");
					return reslut;
				}
				// --后缀
				String suffix = orgFileName.substring(orgFileName.lastIndexOf(".")).toLowerCase();
				if (!suffix.equals(".jpg") &&!suffix.equals(".jpeg")&& !suffix.equals(".bmp") && !suffix.equals(".png") && !suffix.equals(".mp4")) {//--！！！文件类型为图片，录音，录像
					reslut.put("tag", "false");
					reslut.put("message", "文件格式不正确");
					return reslut;
				} else {
					// --文件路径
					String tmpPath = PropertiesUtils.getString("patrolReportAttachmentsUploadDir") + File.separatorChar + prId;

					File file = new File(tmpPath);
					if (!file.exists()) {
						file.mkdirs();
					}
					//扩展名
					String ext = orgFileName.substring( orgFileName.lastIndexOf(".") + 1, orgFileName.length());
					// 对扩展名进行小写转换
					ext = ext.toLowerCase();
					String fileName = new Date().getTime() + "." + ext;

					String tmpFileName = tmpPath + File.separatorChar + fileName;
					//获取保存路径
					position=tmpFileName;

					File tempFile = new File(tmpFileName);
					try {
						InputStream inputStream = photoFile.getInputStream();
						int available = inputStream.available() / 1024 / 1024;
						if (available > 300) {//--文件大小判断
							reslut.put("code", "false");
							reslut.put("message", "文件大小超过300MB");
							return reslut;
						}
						FileUtils.copyInputStreamToFile(photoFile.getInputStream(), tempFile);

					} catch (IOException e) {
						reslut.put("code", "false");
						reslut.put("message", "文件上传失败");
						return reslut;
					}
				}

				//--文件保存成功后，将附件记录保存到数据库
				PatrolReportAttachments pra = new PatrolReportAttachments();
				pra.setPrId(prId);
				pra.setPraType(praType);
				pra.setName(orgFileName);
				pra.setPosition(position);
				pra.setPraAcquisitionTime(new Date());
				pra.setRemark(remark);

				ls.add(pra);
			}
			if(ls.size()>0){
				int return_int=patrolReportAttachmentsService.insertBatch(ls);

				if(return_int>0){
					reslut.put("tag","true");
					reslut.put("message","上传成功");
				}else{
					reslut.put("tag","false");
					reslut.put("message","上传时，DB异常");
				}
			}
		}else{
			reslut.put("tag","false");
			reslut.put("message","未获取上传文件");
		}
		return reslut;
	}

	/**
	 * 删除1条“巡查上报附件记录”
	 * @param praId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deletePra",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object uploadEvidence(@RequestParam int praId) throws Exception{
		Map reslut=new HashMap();
		int return_int=patrolReportAttachmentsService.deleteByPrimaryKey(praId);
		//TODO 后期可能加上，对应附件实体的删除（获取路径、删之）
		if(return_int>0){
			reslut.put("tag","true");
			reslut.put("message","删除成功");
			reslut.put("praId",return_int);
		}else{
			reslut.put("tag","false");
			reslut.put("message","删除异常");
		}
		return reslut;
	}

	}