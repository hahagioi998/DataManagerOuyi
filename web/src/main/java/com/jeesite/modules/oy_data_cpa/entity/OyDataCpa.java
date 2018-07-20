/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_data_cpa.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * oy_data_cpaEntity
 * @author lhy
 * @version 2018-07-16
 */
@Table(name="oy_data_cmpp", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="phone_num", attrName="phoneNum", label="手机号"),
//		@Column(name="crop", attrName="crop", label="手机运营商"),
		@Column(name="client_name", attrName="clientName", label="关联客户表的客户名称", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class OyDataCpa extends DataEntity<OyDataCpa> {
	
	private static final long serialVersionUID = 1L;
	private String phoneNum;		// 手机号
	private String crop;		// 手机运营商
	private String clientName;		// 关联客户表的客户名称
	
	public OyDataCpa() {
		this(null);
	}

	public OyDataCpa(String id){
		super(id);
	}
	
	@Length(min=0, max=11, message="手机号长度不能超过 11 个字符")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=32, message="手机运营商长度不能超过 32 个字符")
	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}
	
	@Length(min=0, max=64, message="关联客户表的客户名称长度不能超过 64 个字符")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
}