/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_client.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.oy_client.entity.OyClient;
import com.jeesite.modules.oy_client.dao.OyClientDao;

/**
 * oy_clientService
 * @author lhy
 * @version 2018-07-10
 */
@Service
@Transactional(readOnly=true)
public class OyClientService extends CrudService<OyClientDao, OyClient> {
	
	/**
	 * 获取单条数据
	 * @param oyClient
	 * @return
	 */
	@Override
	public OyClient get(OyClient oyClient) {
		return super.get(oyClient);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param oyClient
	 * @return
	 */
	@Override
	public Page<OyClient> findPage(Page<OyClient> page, OyClient oyClient) {
		return super.findPage(page, oyClient);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param oyClient
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OyClient oyClient) {
		super.save(oyClient);
	}
	
	/**
	 * 更新状态
	 * @param oyClient
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OyClient oyClient) {
		super.updateStatus(oyClient);
	}
	
	/**
	 * 删除数据
	 * @param oyClient
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OyClient oyClient) {
		super.delete(oyClient);
	}
	
}