package com.hutter.master.mvc.rest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hutter.master.base.exceptions.BaseException;
import com.hutter.master.data.domain.User;
import com.hutter.master.data.form.SettingsForm;
import com.hutter.master.mvc.base.BaseRest;
import com.hutter.master.mvc.base.Response;
import com.hutter.master.service.UserService;

@RestController
@Scope("prototype")
@RequestMapping("api/users")
public class UserRest extends BaseRest {

	Logger logger = LoggerFactory.getLogger(UserRest.class);
	
	@Autowired
	private UserService userS;
	
	@RequiresUser
	@RequestMapping(value = "settings", method = RequestMethod.POST)
	public ResponseEntity<Response> settings(@Validated SettingsForm form, BindingResult bindingResult) {
		logger.info("Update user [{}] settings: {}", getUserName(), JSON.toJSONString(form));
		Response body = new Response();
		try {
			this.checkAssert(bindingResult);
			User record = userS.settings(getUserId(), form);
			body.setSuccess(true);
			body.setResult(record);
			body.setMessage("保存成功");
			// 重置Session数据
			super.flushSession(record);
		} catch (BaseException e) {
			body.setMessage(e.getErrorCode().getValue());
			logger.info("保存失败：{}", e.getErrorCode().toString());
			e.printStackTrace();
		} catch (Exception e) {
			body.setMessage("请求参数验证错误");
			logger.info("保存失败：{}", e.getMessage());
			e.printStackTrace();
		}
		
		return buildResponse(body);
	}
}
