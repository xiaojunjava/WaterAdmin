package com.sinosoft.wateradmin.cmd.service.impl;

import com.sinosoft.wateradmin.cmd.bean.ShipArchives;
import com.sinosoft.wateradmin.cmd.dao.IShipArchivesDAO;
import com.sinosoft.wateradmin.cmd.service.IShipArchivesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * 车船档案  Service实现类
 * @author lkj
 *
 */
@Service
@Transactional
public class ShipArchivesServiceImpl implements IShipArchivesService{
	@Resource
	private IShipArchivesDAO shipArchivesDAO;
	/**
	 * 根据主键查数据
	 * @param saId
	 * @return
	 */
	public ShipArchives selectByPrimaryKey(int saId) throws Exception{
		return shipArchivesDAO.selectByPrimaryKey(saId);
	}

	/**
	 * 根据条件查数据
	 * @param sa
	 * @return
	 * @throws Exception
	 */
	public List<ShipArchives> selectDatas(ShipArchives sa) throws Exception{
		return shipArchivesDAO.selectDatas(sa);
	}

	/**
	 * 根据主键删除1条数据
	 * @param saId
	 * @return
	 */
	public int deleteByPrimaryKey(int saId) throws Exception{
		return shipArchivesDAO.deleteByPrimaryKey(saId);
	}

	/**
	 *新增记录
	 * @param sa
	 * @return
	 */
	public int insert(ShipArchives sa) throws Exception{
		return shipArchivesDAO.insert(sa);
	}

	/**
	 * 更新记录
	 * @param sa
	 * @return
	 */
	public int update(ShipArchives sa) throws Exception{
		return shipArchivesDAO.update(sa);
	}

}
