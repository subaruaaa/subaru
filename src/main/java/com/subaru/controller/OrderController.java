package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.constants.FIELDS;
import com.subaru.models.Order;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

@Controller
public class OrderController {
	@RequestMapping("/getOrder.php")
	public ResponseEntity<String> getCustomer(String customerTel,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		String employeeTel = Common.getLoginTel(request);

		// getOrder by customerTel,employeeTel, 支持其中一个没有的情况
		List<Order> list = new ArrayList<Order>();
		Order order = new Order(customerTel, employeeTel);
		Order order2 = new Order(customerTel, employeeTel);

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
				FIELDS.CODE_SUCCESS, "list", list),
				callback);
	}

}
