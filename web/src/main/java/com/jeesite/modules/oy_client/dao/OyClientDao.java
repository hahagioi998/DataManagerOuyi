/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_client.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.oy_client.entity.OyClient;

/**
 * oy_clientDAO接口
 * @author lhy
 * @version 2018-07-10
 */
@MyBatisDao
public interface OyClientDao extends CrudDao<OyClient> {
	
}