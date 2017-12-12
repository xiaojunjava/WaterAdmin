package com.sinosoft.wateradmin.cmd.controllers;

import com.github.pagehelper.PageHelper;
import com.sinosoft.wateradmin.cmd.bean.ShipArchives;
import com.sinosoft.wateradmin.cmd.service.IShipArchivesService;
import com.sinosoft.wateradmin.common.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层：车船档案
 *@author lkj
 */
@Controller
@RequestMapping("/sa")
public class ShipArchivesController {
	@Autowired
	private IShipArchivesService shipArchivesService;
	@RequestMapping(value = "/goToListSa")
	public String goToListSa()throws Exception{
		return "cmd/ship_archives_list";
	}
	/**
	 *查询车船信息
	 * @param sa
	 * @return
	 */
	@RequestMapping(value="/getSADatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody BasePage getSADatas(@RequestParam(required=true,defaultValue="1") Integer page,
											  @RequestParam(required=true,defaultValue="10") Integer rows,
											 ShipArchives sa) throws Exception{
		PageHelper.startPage(page, rows);
		List<ShipArchives>  ls=shipArchivesService.selectDatas(sa);

		BasePage<ShipArchives> pi=new BasePage<ShipArchives>(ls);

		return pi;
	}

	/**
	 * 指挥调度页面所用车船列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAllSADatas",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody List<ShipArchives> getAllSADatas(ShipArchives sa) throws Exception{
		List<ShipArchives>  ls=shipArchivesService.selectDatas(sa);

		return ls;
	}
	/**
	 * 新增1条车船信息
	 * @param sa
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveSa",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object saveSa(ShipArchives sa)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(sa==null){
			m.put("tag","false");
			m.put("message","未获取参数");
			return m;
		}
		int ee=shipArchivesService.insert(sa);
		if(ee>0){
			m.put("tag","true");
			m.put("message","操作成功");
		}else{
			m.put("tag","false");
			m.put("message","DB操作异常");
		}
		return m;
	}
	@RequestMapping(value="/updateSa",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object updateSa(ShipArchives sa)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(sa==null||(sa!=null&&sa.getSaId()<=0)){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=shipArchivesService.update(sa);
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
	 * @param saId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delSa",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
	public @ResponseBody Object delSa(int saId)throws Exception{
		Map m=new HashMap();
		/***条件异常判断***/
		if(saId<=0){
			m.put("tag","false");
			m.put("message","参数获取异常");
			return m;
		}
		int ee=shipArchivesService.deleteByPrimaryKey(saId);
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
	 * 得到一个sa对象
	 * @param saId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getSa",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody ShipArchives getSa(int saId) throws Exception{
		if(saId<=0) return null;
		ShipArchives sa=shipArchivesService.selectByPrimaryKey(saId);
		return sa;
	}

}