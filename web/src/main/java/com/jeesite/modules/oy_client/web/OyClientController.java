/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_client.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.oy_client.entity.OyClient;
import com.jeesite.modules.oy_client.service.OyClientService;

/**
 * oy_clientController
 * @author lhy
 * @version 2018-07-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oyclient/oyClient")
public class OyClientController extends BaseController {

	@Autowired
	private OyClientService oyClientService;

	@Autowired
	private OySendAccountService oySendAccountService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OyClient get(String id, boolean isNewRecord) {
		return oyClientService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("oy_client:oyClient:view")
	@RequestMapping(value = {"list", ""})
	public String list(OyClient oyClient, Model model) {
		model.addAttribute("oyClient", oyClient);
		OySendAccount oySendAccount = new OySendAccount();
		model.addAttribute("oySendAccountList",oySendAccountService.findList(oySendAccount));
		return "modules/oy_client/oyClientList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("oy_client:oyClient:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OyClient> listData(OyClient oyClient, HttpServletRequest request, HttpServletResponse response) {
		Page<OyClient> page = oyClientService.findPage(new Page<OyClient>(request, response), oyClient);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("oy_client:oyClient:view")
	@RequestMapping(value = "form")
	public String form(OyClient oyClient, Model model) {
		model.addAttribute("oyClient", oyClient);
		OySendAccount oySendAccount = new OySendAccount();
		model.addAttribute("oySendAccount",oySendAccountService.findList(oySendAccount));
		return "modules/oy_client/oyClientForm";
	}

	/**
	 * 保存oy_client
	 */
	@RequiresPermissions("oy_client:oyClient:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OyClient oyClient) {
		oyClientService.save(oyClient);
		return renderResult(Global.TRUE, "保存oy_client成功！");
	}
	
	/**
	 * 删除oy_client
	 */
	@RequiresPermissions("oy_client:oyClient:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OyClient oyClient) {
		oyClientService.delete(oyClient);
		return renderResult(Global.TRUE, "删除oy_client成功！");
	}
	
}