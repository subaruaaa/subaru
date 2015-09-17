package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.constants.FIELDS;
import com.subaru.models.Customer;
import com.subaru.models.Visit;
import com.subaru.services.SalesService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

import freemarker.log.SLF4JLoggerFactory;

@Controller
public class SalesController {
	@Autowired
	SalesService salesService;

	// 获取接待的所有客户
	// TODO 加入翻页
	@RequestMapping("/getCustomersAll.php")
	public ResponseEntity<String> getVisitorAll(String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);

		// TODO 获取接待的所有客户
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

	// 获取已完成的客户
	@RequestMapping("/getCustomersDone.php")
	public ResponseEntity<String> getVisitorDone(String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		// 获取所有已经完成的来访人员
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

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS,"customers", customers),
				callback);
	}

	// 近期在跟
	@RequestMapping("/getCustomersRecently.php")
	public ResponseEntity<String> getVisitRecently(String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		// 获取最近的来人员
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

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS,"customers", customers),
				callback);
	}
	
	// 获取某客户的
	@RequestMapping("/getVisitByCustomerTel.php")
	public ResponseEntity<String> getVisitByCustomerTel(String customerTel,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		
		salesService.getVisitByCustomerTel(customerTel);
		String employeeTel = Common.getLoginTel(request);
		// TODO 该客户的信息
		List<Visit> list = new ArrayList<Visit>();
		Visit visit = new Visit(employeeTel);
		list.add(visit);
		

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
				FIELDS.CODE_SUCCESS, "list", list),
				callback);
	}
	
	@RequestMapping("/modifyVisitByCustomerTel.php")
	public ResponseEntity<String> modifyVisitByCustomerTel(String customerTel,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		// TODO 该客户的信息
		List<Visit> list = new ArrayList<Visit>();
		Visit visit = new Visit(employeeTel);
		list.add(visit);

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS,FIELDS.CODE,
				FIELDS.CODE_SUCCESS, "list", list),
				callback);
	}
}
