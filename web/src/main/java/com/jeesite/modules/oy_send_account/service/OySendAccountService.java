/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_send_account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.oy_send_account.entity.OySendAccount;
import com.jeesite.modules.oy_send_account.dao.OySendAccountDao;

/**
 * oy_send_accountService
 * @author lhy
 * @version 2018-06-29
 */
@Service
@Transactional(readOnly=true)
public class OySendAccountService extends CrudService<OySendAccountDao, OySendAccount> {
	
	/**
	 * 获取单条数据
	 * @param oySendAccount
	 * @return
	 */
	@Override
	public OySendAccount get(OySendAccount oySendAccount) {
		return super.get(oySendAccount);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param oySendAccount
	 * @return
	 */
	@Override
	public Page<OySendAccount> findPage(Page<OySendAccount> page, OySendAccount oySendAccount) {
		return super.findPage(page, oySendAccount);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param oySendAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OySendAccount oySendAccount) {
		super.save(oySendAccount);
	}
	
	/**
	 * 更新状态
	 * @param oySendAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OySendAccount oySendAccount) {
		super.updateStatus(oySendAccount);
	}
	
	/**
	 * 删除数据
	 * @param oySendAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OySendAccount oySendAccount) {
		super.delete(oySendAccount);
	}
	
}