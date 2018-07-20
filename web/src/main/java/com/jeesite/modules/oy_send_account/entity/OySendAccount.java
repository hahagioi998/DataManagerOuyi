/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_send_account.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * oy_send_accountEntity
 * @author lhy
 * @version 2018-06-29
 */
@Table(name="oy_send_account", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="account", attrName="account", label="账号", queryType=QueryType.LIKE),
		@Column(name="pwd", attrName="pwd", label="密码"),
		@Column(name="ip_address", attrName="ipAddress", label="ip地址"),
		@Column(name="platform", attrName="platform", label="发送账号所属平台", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class OySendAccount extends DataEntity<OySendAccount> {
	
	private static final long serialVersionUID = 1L;
	private String account;		// 账号
	private String pwd;		// 密码
	private String ipAddress;		// ip地址
	private String platform;		// 发送账号所属平台
	
	public OySendAccount() {
		this(null);
	}

	public OySendAccount(String id){
		super(id);
	}
	
	@NotBlank(message="账号不能为空")
	@Length(min=0, max=64, message="账号长度不能超过 64 个字符")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@NotBlank(message="密码不能为空")
	@Length(min=0, max=64, message="密码长度不能超过 64 个字符")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@NotBlank(message="ip地址不能为空")
	@Length(min=0, max=64, message="ip地址长度不能超过 64 个字符")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@NotBlank(message="发送账号所属平台不能为空")
	@Length(min=0, max=255, message="发送账号所属平台长度不能超过 255 个字符")
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
}