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
import com.subaru.models.Customer;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

@Controller
public class SearchController {
	
	@RequestMapping("/searchCustomer.php")
	public ResponseEntity<String> searchVisitor(Long customerTel, String callback,
			HttpServletRequest request,HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		
		String employeeTel = Common.getLoginTel(request);
		//对拉黑的人进行额外的标注
		//显示客户信息
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer("5201314");
		Customer customer2 = new Customer("5201314");
		Customer customer3 = new Customer("5201314");
		Customer customer4 = new Customer("5201314");
		Customer customer5 = new Customer("5201314");
		customers.add(customer);
		customers.add(customer2);
		customers.add(customer3);
		customers.add(customer4);
		customers.add(customer5);
		
		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
				FIELDS.CODE_SUCCESS,"customers", customers),
		callback);
	}
}
