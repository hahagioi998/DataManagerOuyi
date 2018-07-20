/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_order_management.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.oy_order_management.entity.OyTaskAudit;

import java.util.List;

/**
 * oy_task_auditDAO接口
 * @author chf
 * @version 2018-07-12
 */
@MyBatisDao
public interface OyTaskAuditDao extends CrudDao<OyTaskAudit> {

}