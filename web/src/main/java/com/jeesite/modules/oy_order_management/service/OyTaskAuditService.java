/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_order_management.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.oy_order_management.entity.OyTaskAudit;
import com.jeesite.modules.oy_order_management.dao.OyTaskAuditDao;

/**
 * oy_task_auditService
 * @author chf
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly=true)
public class OyTaskAuditService extends CrudService<OyTaskAuditDao, OyTaskAudit> {
	
	/**
	 * 获取单条数据
	 * @param oyTaskAudit
	 * @return
	 */
	@Override
	public OyTaskAudit get(OyTaskAudit oyTaskAudit) {
		return super.get(oyTaskAudit);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param oyTaskAudit
	 * @return
	 */
	@Override
	public Page<OyTaskAudit> findPage(Page<OyTaskAudit> page, OyTaskAudit oyTaskAudit) {
		return super.findPage(page, oyTaskAudit);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param oyTaskAudit
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OyTaskAudit oyTaskAudit) {
		super.save(oyTaskAudit);
	}
	
	/**
	 * 更新状态
	 * @param oyTaskAudit
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OyTaskAudit oyTaskAudit) {
		super.updateStatus(oyTaskAudit);
	}
	
	/**
	 * 删除数据
	 * @param oyTaskAudit
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OyTaskAudit oyTaskAudit) {
		super.delete(oyTaskAudit);
	}
	
}