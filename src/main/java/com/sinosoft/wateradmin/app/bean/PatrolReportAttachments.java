package com.sinosoft.wateradmin.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 巡查上报附件表(app_patrol_report_attachments)对应bean
 *
 * @author lkj.
 * @create 2017-11-7
 */
public class PatrolReportAttachments extends BaseBean {
	//上报记录附件序号
	private int praId;
	//上报记录序号
	private int prId;
	//附件名称
	private String name;
	//附件类型
	private int praType;
	//存放位置
	private String position;
	//采集时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//直接用过来的json字符的日期
	private Date praAcquisitionTime;
	//备注
	private String remark;
	/****下面为：查询专用****/
	private String startTime;
	private String endTime;

	public int getPraId() {
		return praId;
	}

	public void setPraId(int praId) {
		this.praId = praId;
	}

	public int getPrId() {
		return prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPraType() {
		return praType;
	}

	public void setPraType(int praType) {
		this.praType = praType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getPraAcquisitionTime() {
		return praAcquisitionTime;
	}

	public void setPraAcquisitionTime(Date praAcquisitionTime) {
		this.praAcquisitionTime = praAcquisitionTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
