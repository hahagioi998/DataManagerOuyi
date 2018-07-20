/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_order_management.entity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.oy_client.entity.OyClient;
import com.jeesite.modules.oy_send_account.entity.OySendAccount;
import org.hibernate.validator.constraints.Length;

/**
 * oy_task_auditEntity
 * @author chf
 * @version 2018-07-11
 */
@Table(name="oy_task_audit", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="client_id", attrName="clientId", label="客户关联id"),
		@Column(name="data_size", attrName="dataSize", label="数据量"),
		@Column(name="crop", attrName="crop", label="运营商", queryType=QueryType.LIKE),
		@Column(name="audit_status", attrName="auditStatus", label="审核状态", comment="审核状态（0 未审核，1 审核不通过，2审核通过）", queryType=QueryType.LIKE),
		@Column(name="acount_id", attrName="acountId", label="发送账号关联id", queryType=QueryType.LIKE),
		@Column(name="audit_reason", attrName="auditReason", label="审核不通过原因"),
		@Column(name="message_text", attrName="messageText", label="短信内容", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
}, orderBy="a.update_date DESC"
)
public class OyTaskAudit extends DataEntity<OyTaskAudit> {

	private static final long serialVersionUID = 1L;
	private String clientId;		// 客户关联id
	private Integer dataSize;		// 数据量
	private String crop;		// 运营商
	private String auditStatus;		// 审核状态（0 未审核，1 审核不通过，2审核通过）
	private String acountId;		// 发送账号关联id
	private OySendAccount oySendAccount; // 发送账号实体类
	private OyClient oyClient; // 客户实体类
	private String auditReason;		// 审核不通过原因
	private String messageText;		// 短信内容

	public OyClient getOyClient() {
		return oyClient;
	}

	public void setOyClient(OyClient oyClient) {
		this.oyClient = oyClient;
	}

	public OySendAccount getOySendAccount() {
		return oySendAccount;
	}

	public void setOySendAccount(OySendAccount oySendAccount) {
		this.oySendAccount = oySendAccount;
	}

	public OyTaskAudit() {
		this(null);
	}

	public OyTaskAudit(String id){
		super(id);
	}

	@Length(min=0, max=64, message="客户关联id长度不能超过 64 个字符")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getDataSize() {
		return dataSize;
	}

	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

	@Length(min=0, max=32, message="运营商长度不能超过 32 个字符")
	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	@Length(min=0, max=64, message="审核状态长度不能超过 64 个字符")
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Length(min=0, max=64, message="发送账号关联id长度不能超过 64 个字符")
	public String getAcountId() {
		return acountId;
	}

	public void setAcountId(String acountId) {
		this.acountId = acountId;
	}

	@Length(min=0, max=64, message="审核不通过原因长度不能超过 64 个字符")
	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	@Length(min=0, max=70, message="短信内容长度不能超过 70 个字符")
	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

}