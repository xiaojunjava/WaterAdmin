package com.sinosoft.wateradmin.cmd.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sinosoft.wateradmin.common.BaseBean;
import com.sinosoft.wateradmin.common.CustomDateTimeDeserializer;
import com.sinosoft.wateradmin.common.CustomDateTimeSerializer;

import java.util.Date;

/**
 * 下发指令表(cmd_instruction_issued)对应bean
 *
 * @author lkj.
 * @create 2017-11-28 14:22
 */
public class InstructionIssued extends BaseBean {
	//指令序号
	private Integer iiId;
	//车船档案序号
	private Integer saId;
	//指令内容
	private String iiContent;
	//发送时间
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Date sendTime;
	//紧急程度
	private String iiPriorityLevel;
	//操作人
	private String iiOperator;

	//****以下为“查询专用”****/
	private String startTime;//开始时间
	private String endTime;//结束时间

	public Integer getIiId() {
		return iiId;
	}

	public void setIiId(Integer iiId) {
		this.iiId = iiId;
	}

	public Integer getSaId() {
		return saId;
	}

	public void setSaId(Integer saId) {
		this.saId = saId;
	}

	public String getIiContent() {
		return iiContent;
	}

	public void setIiContent(String iiContent) {
		this.iiContent = iiContent;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getIiPriorityLevel() {
		return iiPriorityLevel;
	}

	public void setIiPriorityLevel(String iiPriorityLevel) {
		this.iiPriorityLevel = iiPriorityLevel;
	}

	public String getIiOperator() {
		return iiOperator;
	}

	public void setIiOperator(String iiOperator) {
		this.iiOperator = iiOperator;
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
