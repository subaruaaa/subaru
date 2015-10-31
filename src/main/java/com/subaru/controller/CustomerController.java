package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subaru.constants.FIELDS;
import com.subaru.models.Customer;
import com.subaru.services.CustomerService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;

	// 获取客户的静态信息
	@RequestMapping("/getCustomer.php")
	public ResponseEntity<String> getCustomer(
			@RequestParam(value = "customerTel", required = true) String customerTel,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		Customer customer = customerService.getCustomer(customerTel);
		if (customer != null) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_SUCCESS, "customer", customer),
					callback);
		} else {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE,
							"不存在此客户"), callback);
		}
	}

	@RequestMapping("/getCustomerById.php")
	public ResponseEntity<String> getCustomer(
			@RequestParam(value = "customerId", required = true) Integer customerId,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		Customer customer = customerService.getCustomer(customerId);
		if (customer != null) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_SUCCESS, "customer", customer),
					callback);
		} else {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE,
							"不存在此客户"), callback);
		}
	}

	// 创建客户信息
	@RequestMapping("/createCustomer.php")
	public ResponseEntity<String> createCustomer(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "customerTel", required = true) String customerTel,
			@RequestParam(value = "occupation", required = true, defaultValue = "") String occupation,
			@RequestParam(value = "identityCard", required = true, defaultValue = "") String identityCard,
			@RequestParam(value = "birthday", required = true, defaultValue = "") String birthday,
			@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "note", required = true, defaultValue = "") String note,
			@RequestParam(value = "introducerTypeId", required = true, defaultValue = "0") Integer introducerTypeId,
			@RequestParam(value = "introducer", required = true, defaultValue = "") String introducer,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		if (!StringUtils.hasText(name)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NAME_EMPTY, FIELDS.MESSAGE,
							FIELDS.NAME_EMPTY), callback);
		}

		if (!StringUtils.hasText(customerTel)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_TEL_EMPTY, FIELDS.MESSAGE,
							FIELDS.TEL_EMPTY), callback);
		}
		if (StringUtils.hasText(birthday)
				&& !Common.isDate("yyyy-MM-dd", birthday)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_DATE, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_DATE), callback);
		}
		if (!Common.isMobile(customerTel)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_DATE, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_TEL), callback);
		}

		if (StringUtils.hasText(email) && !Common.isEmail(email)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_EMAIL, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_EMAIL), callback);
		}

		Customer customerExist = customerService.getCustomer(customerTel);
		if (customerExist != null) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_CUSTOMER_EXIST, FIELDS.MESSAGE,
							FIELDS.CUSTOMER_EXIST), callback);
		}

		customerService
				.createCustomer(name, customerTel, occupation, identityCard,
						birthday, email, note, introducerTypeId, introducer);
		Customer customer = customerService.getCustomer(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customer", customer), callback);
	}

	// 修改客户静态信息
	@RequestMapping("/modifyCustomer.php")
	public ResponseEntity<String> modifyCustomer(
			@RequestParam(value = "customerId", required = true) Integer customerId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "customerTel", required = true) String customerTel,
			@RequestParam(value = "occupation", required = true, defaultValue = "") String occupation,
			@RequestParam(value = "identityCard", required = true, defaultValue = "") String identityCard,
			@RequestParam(value = "birthday", required = true, defaultValue = "") String birthday,
			@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "note", required = true, defaultValue = "") String note,
			@RequestParam(value = "introducerTypeId", required = true, defaultValue = "0") Integer introducerTypeId,
			@RequestParam(value = "introducer", required = true, defaultValue = "") String introducer,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		if (!StringUtils.hasText(name)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NAME_EMPTY, FIELDS.MESSAGE,
							FIELDS.NAME_EMPTY), callback);
		}

		if (!StringUtils.hasText(customerTel)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_TEL_EMPTY, FIELDS.MESSAGE,
							FIELDS.TEL_EMPTY), callback);
		}
		if (StringUtils.hasText(birthday)
				&& !Common.isDate("yyyy-MM-dd", birthday)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_DATE, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_DATE), callback);
		}
		if (!Common.isMobile(customerTel)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_DATE, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_TEL), callback);
		}

		if (StringUtils.hasText(email) && !Common.isEmail(email)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_EMAIL, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_EMAIL), callback);
		}

		Customer customerExist = customerService.getCustomer(customerId);
		if (customerExist == null) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE,
							"不存在此客户"), callback);
		}

		customerExist = customerService.getCustomer(customerTel);
		if (customerExist != null && customerExist.getCusomerId() != customerId) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_CUSTOMER_EXIST, FIELDS.MESSAGE,
							FIELDS.CUSTOMER_EXIST), callback);
		}

		// 电话是key，一定要传
		customerService.modifyCustomer(customerId, name, customerTel,
				occupation, identityCard, birthday, email, note,
				introducerTypeId, introducer);
		Customer customer = customerService.getCustomer(customerId);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customer", customer), callback);
	}

	// 删除客户静态信息
	@RequestMapping("/delCustomer.php")
	public ResponseEntity<String> delCustomer(String customerTel,
			Integer customerId, String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		if (customerId != null) {
			customerService.delCustomer(customerId);
		}
		if (StringUtils.hasText(customerTel)) {
			customerService.delCustomer(customerTel);
		}

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "删除成功"), callback);
	}

}
