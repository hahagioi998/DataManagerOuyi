/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_data_cpa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.oy_data_cpa.entity.OyDataCpa;
import com.jeesite.modules.oy_data_cpa.dao.OyDataCpaDao;

/**
 * oy_data_cpaService
 * @author lhy
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly=false)
public class OyDataCpaService extends CrudService<OyDataCpaDao, OyDataCpa> {

	@Autowired
	OyDataCpaDao oyDataCpaDao;


	/**
	 * 获取单条数据
	 * @param oyDataCpa
	 * @return
	 */
	@Override
	public OyDataCpa get(OyDataCpa oyDataCpa) {
		return super.get(oyDataCpa);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param oyDataCpa
	 * @return
	 */
	@Override
	public Page<OyDataCpa> findPage(Page<OyDataCpa> page, OyDataCpa oyDataCpa) {
		return super.findPage(page, oyDataCpa);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param oyDataCpa
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OyDataCpa oyDataCpa) {
		super.save(oyDataCpa);
	}
	
	/**
	 * 更新状态
	 * @param oyDataCpa
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OyDataCpa oyDataCpa) {
		super.updateStatus(oyDataCpa);
	}
	
	/**
	 * 删除数据
	 * @param oyDataCpa
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OyDataCpa oyDataCpa) {
		super.delete(oyDataCpa);
	}


	/**
	 * 查询不超过7天，不包含某客户名称的数据
	 * @param map
	 */
	public List<String> findData(Map<String,Object> map){
		return oyDataCpaDao.findListByClientNameAndUpdateDate(map);
	}

	/**
	 * 批量更新数据 更新client_name字段
	 * @param map
	 * @return
	 */
	public int batchUpdate(Map<String,Object> map){
		return oyDataCpaDao.batchUpdate(map);
	}

	/**
	 * 统计条数
	 * @param map
	 * @return
	 */
	public int countTotal(Map<String,Object> map){ return oyDataCpaDao.countTotal(map); }
}