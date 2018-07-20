/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_data_cpa.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.oy_data_cpa.entity.OyDataCpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * oy_data_cpaDAO接口
 * @author lhy
 * @version 2018-07-16
 */

@MyBatisDao
public interface OyDataCpaDao extends CrudDao<OyDataCpa> {

    /**
     * 查询超过7天，不包含某客户的数据
     * @param map
     * @return
     */
    List<String> findListByClientNameAndUpdateDate(Map<String,Object> map);

    /**
     * 批量更新
     * @param map
     * @return
     */
    int batchUpdate(Map<String,Object> map);

    /**
     * 查询条数
     * @param map
     * @return
     */
    int countTotal(Map<String,Object> map);
}