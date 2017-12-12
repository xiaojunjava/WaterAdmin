package com.sinosoft.wateradmin.app.bean;

import java.util.Date;

/**
 * 巡查台账记录表（APP_PATROL_LEDGER）对应bean
 *
 * @author hanjie.
 * @create 2017/11/2 17:22
 */
public class AppPatrolLedger {
    //巡查台账记录号
    private Integer plId;
    //用户记录号
    private Integer userId;
    //--巡检人员
    private String userName;
    //巡查台账名称
    private String plLedgerName;
    //巡查开始时间
    private Date plBeginTime;
    //巡查结束时间
    private Date plEndTime;
    //巡查结果
    private String plResult;
    //巡查轨迹
    private String plTarjectory;

    //查询专用
	private String startTime;
	private String endTime;

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

	public Integer getPlId() {
        return plId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPlLedgerName() {
        return plLedgerName;
    }

    public Date getPlBeginTime() {
        return plBeginTime;
    }

    public Date getPlEndTime() {
        return plEndTime;
    }

    public String getPlResult() {
        return plResult;
    }

    public String getPlTarjectory() {
        return plTarjectory;
    }

    public void setPlId(Integer plId) {
        this.plId = plId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPlLedgerName(String plLedgerName) {
        this.plLedgerName = plLedgerName;
    }

    public void setPlBeginTime(Date plBeginTime) {
        this.plBeginTime = plBeginTime;
    }

    public void setPlEndTime(Date plEndTime) {
        this.plEndTime = plEndTime;
    }

    public void setPlResult(String plResult) {
        this.plResult = plResult;
    }

    public void setPlTarjectory(String plTarjectory) {
        this.plTarjectory = plTarjectory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
