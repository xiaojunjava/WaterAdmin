package com.sinosoft.wateradmin.app.service.impl;

import com.sinosoft.wateradmin.app.bean.AppPatrolLedger;
import com.sinosoft.wateradmin.app.dao.IAppPatrolLedgerDAO;
import com.sinosoft.wateradmin.app.service.IAppPatrolLedgerSerivice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 巡查台账_Service实现
 *
 * @author hanjie.
 * @create 2017/11/3
 */
@Service
@Transactional
public class AppPatrolLedgerSeriviceImpl implements IAppPatrolLedgerSerivice {

    @Resource
    private IAppPatrolLedgerDAO iAppPatrolLedgerDAO;


    @Override
    public int deleteByPrimaryKey(@Param(value = "prId") int plId) throws Exception {
        return this.iAppPatrolLedgerDAO.deleteByPrimaryKey(plId);
    }

    @Override
    public int insert(AppPatrolLedger appPatrolLedger) throws Exception {
        return this.iAppPatrolLedgerDAO.insertAppPatrolLedger(appPatrolLedger);
    }

    @Override
    public AppPatrolLedger selectByPrimaryKey(@Param(value = "plId") int plId) throws Exception {
        return this.iAppPatrolLedgerDAO.selectByPrimaryKey(plId);
    }

    @Override
    public int updateByPrimaryKey(AppPatrolLedger appPatrolLedger) throws Exception {
        return this.iAppPatrolLedgerDAO.updateByPrimaryKey(appPatrolLedger);
    }

    @Override
    public List<AppPatrolLedger> selectByUserId(@Param(value = "userId") int userId) throws Exception {
        return this.iAppPatrolLedgerDAO.selectByUserId(userId);
    }
    @Override
    public List<AppPatrolLedger> selectDatas(AppPatrolLedger appPatrolLedger) throws Exception{
		return this.iAppPatrolLedgerDAO.selectDatas(appPatrolLedger);
	}
    @Override
    public int insertSelective(AppPatrolLedger appPatrolLedger) {
        return this.iAppPatrolLedgerDAO.insertSelective(appPatrolLedger);
    }

    /**
     * 获取正在巡检的台账记录  added by lvzhixue 2017-11-13
     *
     * @return
     */
    @Override
    public List<AppPatrolLedger> getPatrolNow() {
        return this.iAppPatrolLedgerDAO.getPatrolNow();
    }
}
