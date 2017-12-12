package com.sinosoft.wateradmin.cmd.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.app.bean.Users;
import com.sinosoft.wateradmin.cmd.bean.InstructionIssued;
import com.sinosoft.wateradmin.cmd.service.IInstructionIssuedService;
import com.sinosoft.wateradmin.common.BasePage;
import com.sinosoft.wateradmin.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：下发指令
 *@author lkj
 */
@Controller
@RequestMapping("/ii")
public class InstructionIssuedController {
	@Autowired
	private IInstructionIssuedService instructionIssuedService;

	/**
	 * 跳转动作
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goToListIi")
	public String goToListIi()throws Exception{
		return "cmd/instruction_issued_list";
	}

	/**
	 * 查询-分页
	 * @param page 当前页码
	 * @param rows 每页数据量
	 * @param ii 传参实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getIiDatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody BasePage<InstructionIssued> getIiDatas(@RequestParam(required=true,defaultValue="1") Integer page,
															@RequestParam(required=true,defaultValue="10") Integer rows, InstructionIssued ii) throws Exception{
		PageHelper.startPage(page, rows);
		List<InstructionIssued>  ls=instructionIssuedService.selectDatas(ii);

		BasePage<InstructionIssued> pi=new BasePage<InstructionIssued>(ls);

		return pi;
	}
	/**
	 *根据id得到1个 路线规划 对象
	 * @param iiId
	 * @return
	 */
	@RequestMapping(value="/getIi",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody InstructionIssued getSm(int iiId) throws Exception{
		InstructionIssued ii=instructionIssuedService.selectByPrimaryKey(iiId);
		return ii;
	}
	/**
	 * 新增
	 * @param ii
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addIi",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object addIi(InstructionIssued ii)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(ii==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		ii.setSendTime(new Date());//发送时间
		Users user = CommonUtil.getLoginUser();
		ii.setIiOperator(user.getUserLoginname());

		int ee=instructionIssuedService.insert(ii);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}

	/**
	 * 更新1条路线规划记录
	 * @param ii
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateIi",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object updateIi(InstructionIssued ii)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(ii==null||(ii!=null&&ii.getIiId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=instructionIssuedService.update(ii);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}

	/**
	 * 删除1条记录
	 * @param iiId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delIi",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object delIi(int iiId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(iiId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=instructionIssuedService.deleteByPrimaryKey(iiId);
		if(ee>0){
			m.put("tag","true");
			m.put("message","删除成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}

}