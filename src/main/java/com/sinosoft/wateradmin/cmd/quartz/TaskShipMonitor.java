package com.sinosoft.wateradmin.cmd.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 车船坐标任务
 *
 * @author lkj.
 * @create 2017-11-16 11:30
 */
public class TaskShipMonitor {

	//cron任务触发器运行的任务
	public void doCronTask(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("···········调用GIS服务，获取坐标···"+sdf.format(new Date()));
		//TODO 调用GIS服务，获取坐标
	}
}
