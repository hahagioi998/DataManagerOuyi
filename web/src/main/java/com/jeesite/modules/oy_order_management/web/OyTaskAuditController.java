/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_order_management.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.oy_client.entity.OyClient;
import com.jeesite.modules.oy_client.service.OyClientService;
import com.jeesite.modules.oy_order_management.entity.AuditStatus;
import com.jeesite.modules.oy_order_management.entity.Carrieroperator;
import com.jeesite.modules.oy_order_management.entity.OyTaskAudit;
import com.jeesite.modules.oy_order_management.service.OyTaskAuditService;
import com.jeesite.modules.oy_order_management.service.SendMessageService;
import com.jeesite.modules.oy_send_account.entity.OySendAccount;
import com.jeesite.modules.oy_send_account.service.OySendAccountService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * oy_task_auditController
 *
 * @author chf
 * @version 2018-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/oy_order_management/oyTaskAudit")
public class OyTaskAuditController extends BaseController {

	@Autowired
	private OyTaskAuditService oyTaskAuditService;

	@Autowired
	private OySendAccountService oySendAccountService;

	@Autowired
	private OyClientService oyClientService;

	@Autowired
	private SendMessageService sendMessageService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OyTaskAudit get(String id, boolean isNewRecord) {
		return oyTaskAuditService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:view")
	@RequestMapping(value = {"list", ""})
	public String list(OyTaskAudit oyTaskAudit, Model model) {
		model.addAttribute("corpList", getCorpList());
		model.addAttribute("oyTaskAudit", oyTaskAudit);
		return "modules/oy_order_management/oyTaskAuditList";
	}


	/**
	 * 审核列表
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:view")
	@RequestMapping(value = {"listCheck", ""})
	public String listCheck(OyTaskAudit oyTaskAudit, Model model) {
		model.addAttribute("corpList", getCorpList());
		model.addAttribute("oyTaskAudit", oyTaskAudit);
		return "modules/oy_order_management/oyTaskAuditCheck";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OyTaskAudit> listData(OyTaskAudit oyTaskAudit, HttpServletRequest request, HttpServletResponse response) {
		Page<OyTaskAudit> page = oyTaskAuditService.findPage(new Page<OyTaskAudit>(request, response), oyTaskAudit);
		for (OyTaskAudit oy:page.getList()) {
			String cid=oy.getClientId();  //发送客户id
			String aci=oy.getAcountId();  //发送账号id
			oy.setOyClient(oyClientService.get(cid));
			oy.setOySendAccount(oySendAccountService.get(aci));
		}
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:view")
	@RequestMapping(value = "form")
	public String form(OyTaskAudit oyTaskAudit, Model model){
		model.addAttribute("corpList", getCorpList());
		model.addAttribute("oyTaskAudit", oyTaskAudit);

		OyClient oyClient=new OyClient();
		OySendAccount oySendAccount = new OySendAccount();

		model.addAttribute("oyClient",oyClientService.findList(oyClient));
		model.addAttribute("oySendAccount",oySendAccountService.findList(oySendAccount));
		return "modules/oy_order_management/oyTaskAuditForm";
	}

	/**
	 * 查看审核表单
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:view")
	@RequestMapping(value = "formCheck")
	public String formCheck(OyTaskAudit oyTaskAudit, Model model) {
		model.addAttribute("corpList", getCorpList());
		model.addAttribute("statusList", getStatusList());
		model.addAttribute("oyTaskAudit", oyTaskAudit);
		return "modules/oy_order_management/oyTaskAuditCheckForm";
	}

	/**
	 * 保存订单
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OyTaskAudit oyTaskAudit) {
		if (null == oyTaskAudit.getAuditStatus()) {
			oyTaskAudit.setAuditStatus("未审核");
		}
		if (oyTaskAudit.getAuditStatus().equals("审核通过")) {
			oyTaskAudit.setAuditReason("短信正在发送。");
		}
		oyTaskAuditService.save(oyTaskAudit);
		return renderResult(Global.TRUE, "保存订单成功！");
	}


	/**
	 * 审核订单
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:edit")
	@PostMapping(value = "saveCheck")
	@ResponseBody
	public String saveCheck(@Validated OyTaskAudit oyTaskAudit){
		String cid=oyTaskAudit.getClientId();  //发送客户id
		String aci=oyTaskAudit.getAcountId();  //发送账号id
		oyTaskAudit.setOyClient(oyClientService.get(cid));
		oyTaskAudit.setOySendAccount(oySendAccountService.get(aci));

		if (oyTaskAudit.getAuditStatus().equals("审核通过")) {
			oyTaskAudit.setAuditReason("短信正在发送");
			sendMessageService.startSend(oyTaskAudit);
		}
		oyTaskAuditService.save(oyTaskAudit);
		return renderResult(Global.TRUE, "订单审核成功！");
	}

	/**
	 * 删除订单
	 */
	@RequiresPermissions("oy_order_management:oyTaskAudit:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OyTaskAudit oyTaskAudit) {
		oyTaskAuditService.delete(oyTaskAudit);
		return renderResult(Global.TRUE, "删除订单成功！");
	}

	/**
	 * 封装运营商信息到集合中
	 */
	public List getCorpList() {
		Carrieroperator c1 = new Carrieroperator(1, "中国移动");
		Carrieroperator c2 = new Carrieroperator(2, "中国联通");
		Carrieroperator c3 = new Carrieroperator(3, "中国电信");
		List corpList = new ArrayList();
		corpList.add(c1);
		corpList.add(c2);
		corpList.add(c3);
		return corpList;
	}

	/**
	 * 封装审核状态到集合中
	 */
	public List getStatusList() {
		AuditStatus a1 = new AuditStatus("审核通过");
		AuditStatus a2 = new AuditStatus("审核不通过");
		List statusList = new ArrayList();
		statusList.add(a1);
		statusList.add(a2);
		return statusList;
	}

}