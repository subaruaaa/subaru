package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subaru.constants.FIELDS;
import com.subaru.models.Customer;
import com.subaru.services.CustomerService;
import com.subaru.utils.LoginHelper;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	//获取客户的静态信息
	@RequestMapping("/getCustomer.php")
	public ResponseEntity<String> getCustomer(@RequestParam(value = "customerTel", required = true)String customerTel,
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
	
	//修改客户静态信息
	@RequestMapping("/modifyCustomer.php")
	public ResponseEntity<String> modifyCustomer(String name, @RequestParam(value = "customerTel", required = true)String customerTel,
			String occupation, String identityCard, String birthday,
			String email, String note,String introducerType,String introducer, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		
		//电话是key，一定要传
		customerService.saveCustomer(name, customerTel, occupation, identityCard,
				birthday, email, note, introducerType, introducer);
		Customer customer = customerService.getCustomer(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customer", customer), callback);
	}
	
	//删除客户静态信息
	@RequestMapping("/delCustomer.php")
	public ResponseEntity<String> delCustomer(@RequestParam(value = "customerTel", required = true)String customerTel, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		
		customerService.delCustomer(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "删除成功"), callback);
	}

	// //TODO 获取某人的访问列表，动态列表
	// @RequestMapping("/visit.php")
	// public ResponseEntity<String> getVisit(Long telephone, String callback,
	// HttpServletRequest request,HttpServletResponse response) {
	//
	// return jsonpEntity("", callback);
	// }
}
