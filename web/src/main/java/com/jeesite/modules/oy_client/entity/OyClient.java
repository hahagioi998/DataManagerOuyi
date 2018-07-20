/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_client.entity;


import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.modules.oy_send_account.entity.OySendAccount;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * oy_clientEntity
 * @author lhy
 * @version 2018-07-10
 */
@Table(name="oy_client", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="客户名称", queryType=QueryType.LIKE),
		@Column(name="acount_id", attrName="accountId", label="发送账号关联id"),
		@Column(name="link_man", attrName="linkMan", label="联系人"),
		@Column(name="link_phone", attrName="linkPhone", label="联系电话"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable= {
		@JoinTable(type = JoinTable.Type.LEFT_JOIN, entity = OySendAccount.class, attrName = "oySendAccount", alias = "o",
				on = "o.id = a.acount_id", columns = {
				@Column(name="id", attrName="id", label="id", isPK=true),
				@Column(name="account", attrName="account", label="账号", queryType=QueryType.LIKE),
				@Column(name="pwd", attrName="pwd", label="密码"),
				@Column(name="ip_address", attrName="ipAddress", label="ip地址"),
				@Column(name="platform", attrName="platform", label="发送账号所属平台", queryType=QueryType.LIKE),
				@Column(includeEntity=DataEntity.class),
		}),
	},		orderBy="a.update_date DESC"
)
public class OyClient extends DataEntity<OyClient> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 客户名称
	private String accountId;		// 发送账号关联id
	private String linkMan;		// 联系人
	private String linkPhone;		// 联系电话
	private OySendAccount oySendAccount;

	public OySendAccount getOySendAccount() {
		return oySendAccount;
	}

	public void setOySendAccount(OySendAccount oySendAccount) {
		this.oySendAccount = oySendAccount;
	}

	public OyClient() {
		this(null);
	}

	public OyClient(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="客户名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="发送账号关联id长度不能超过 64 个字符")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="联系人长度不能超过 64 个字符")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	
	@Length(min=0, max=11, message="联系电话长度不能超过 11 个字符")
	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

}