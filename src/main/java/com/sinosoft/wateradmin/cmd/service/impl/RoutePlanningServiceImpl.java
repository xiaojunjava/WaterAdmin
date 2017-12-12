package com.sinosoft.wateradmin.cmd.service.impl;

import com.sinosoft.wateradmin.cmd.bean.RoutePlanning;
import com.sinosoft.wateradmin.cmd.dao.IRoutePlanningDAO;
import com.sinosoft.wateradmin.cmd.service.IRoutePlanningService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *  路线规划  Service实现类
 * @author lkj
 *
 */
@Service
@Transactional
public class RoutePlanningServiceImpl implements IRoutePlanningService {
	@Resource
	private IRoutePlanningDAO routePlanningDAO;
	/**
	 * 根据主键查数据
	 * @param rpId
	 * @return
	 */
	public RoutePlanning selectByPrimaryKey(int rpId) throws Exception{
		return routePlanningDAO.selectByPrimaryKey(rpId);
	}

	/**
	 * 根据条件查数据
	 * @param rp
	 * @return
	 * @throws Exception
	 */
	public List<RoutePlanning> selectDatas(RoutePlanning rp) throws Exception{
		return routePlanningDAO.selectDatas(rp);
	}

	/**
	 * 根据主键删除1条数据
	 * @param rpId
	 * @return
	 */
	public int deleteByPrimaryKey(int rpId) throws Exception{
		return routePlanningDAO.deleteByPrimaryKey(rpId);
	}

	/**
	 *新增记录
	 * @param rp
	 * @return
	 */
	public int insert(RoutePlanning rp) throws Exception{
		return routePlanningDAO.insert(rp);
	}

	/**
	 * 更新记录
	 * @param rp
	 * @return
	 */
	public int update(RoutePlanning rp) throws Exception{
		return routePlanningDAO.update(rp);
	}

}
