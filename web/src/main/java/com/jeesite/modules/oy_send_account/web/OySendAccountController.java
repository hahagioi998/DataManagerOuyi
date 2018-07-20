/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.oy_send_account.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.oy_send_account.entity.OySendAccount;
import com.jeesite.modules.oy_send_account.service.OySendAccountService;

/**
 * oy_send_accountController
 * @author lhy
 * @version 2018-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/oysendaccount/oySendAccount")
public class OySendAccountController extends BaseController {

	@Autowired
	private OySendAccountService oySendAccountService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OySendAccount get(String id, boolean isNewRecord) {
		return oySendAccountService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("oy_send_account:oySendAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(OySendAccount oySendAccount, Model model) {
		model.addAttribute("oySendAccount", oySendAccount);
		return "modules/oy_send_account/oySendAccountList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("oy_send_account:oySendAccount:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OySendAccount> listData(OySendAccount oySendAccount, HttpServletRequest request, HttpServletResponse response) {
		Page<OySendAccount> page = oySendAccountService.findPage(new Page<OySendAccount>(request, response), oySendAccount); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("oy_send_account:oySendAccount:view")
	@RequestMapping(value = "form")
	public String form(OySendAccount oySendAccount, Model model) {
		model.addAttribute("oySendAccount", oySendAccount);
		return "modules/oy_send_account/oySendAccountForm";
	}

	/**
	 * 保存oy_send_account
	 */
	@RequiresPermissions("oy_send_account:oySendAccount:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OySendAccount oySendAccount) {
		oySendAccountService.save(oySendAccount);
		return renderResult(Global.TRUE, "保存oy_send_account成功！");
	}
	
	/**
	 * 删除oy_send_account
	 */
	@RequiresPermissions("oy_send_account:oySendAccount:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OySendAccount oySendAccount) {
		oySendAccountService.delete(oySendAccount);
		return renderResult(Global.TRUE, "删除oy_send_account成功！");
	}
	
}