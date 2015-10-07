package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subaru.constants.FIELDS;
import com.subaru.models.Customer;
import com.subaru.models.Discount;
import com.subaru.models.Employee;
import com.subaru.models.Order;
import com.subaru.models.Visit;
import com.subaru.services.CustomerService;
import com.subaru.services.EmployeeService;
import com.subaru.services.OrderService;
import com.subaru.services.VisitService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

import static com.subaru.types.functions.subList;

@Controller
public class SalesController {
	@Autowired
	VisitService visitService;

	@Autowired
	OrderService orderService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	CustomerService customerService;

	// 获取接待的所有客户
	@RequestMapping("/getCustomersAll.php")
	public ResponseEntity<String> getVisitorAll(
			@RequestParam(value = "page_size", defaultValue = FIELDS.DEFAULT_PAGE_SIZE_STR) Integer page_size,
			@RequestParam(value = "page_num", defaultValue = FIELDS.DEFAULT_PAGE_NUM_STR) Integer page_num,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);

		List<Order> orders = orderService.getOrdersByEmployee(employeeTel);

		// TODO 获取接待的所有客户
		List<Customer> customers = new ArrayList<Customer>();
		for (Order order : orders) {
			if (!customers.contains(order.getCustomer())) {
				customers.add(order.getCustomer());
			}
		}

		// TODO 加入访问的客户信息
		List<Customer> customerOnePage = subList(customers, page_size
				* (page_num - 1), page_size * page_num);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customers", customers), callback);
	}

	// 获取已完成的客户
	@RequestMapping("/getCustomersDone.php")
	public ResponseEntity<String> getVisitorDone(
			@RequestParam(value = "page_size", defaultValue = FIELDS.DEFAULT_PAGE_SIZE_STR) Integer page_size,
			@RequestParam(value = "page_num", defaultValue = FIELDS.DEFAULT_PAGE_NUM_STR) Integer page_num,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);

		List<Order> orders = orderService.getOrdersByEmployee(employeeTel);

		// TODO 获取接待的所有客户
		List<Customer> customers = new ArrayList<Customer>();
		for (Order order : orders) {
			if (!customers.contains(order.getCustomer())) {
				customers.add(order.getCustomer());
			}
		}

		// TODO 加入访问的客户信息
		List<Customer> customerOnePage = subList(customers, page_size
				* (page_num - 1), page_size * page_num);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customers", customers), callback);
	}

	// 近期在跟
	@RequestMapping("/getCustomersRecently.php")
	public ResponseEntity<String> getVisitRecently(
			@RequestParam(value = "page_size", defaultValue = FIELDS.DEFAULT_PAGE_SIZE_STR) Integer page_size,
			@RequestParam(value = "page_num", defaultValue = FIELDS.DEFAULT_PAGE_NUM_STR) Integer page_num,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		List<Order> orders = orderService.getOrdersByEmployee(employeeTel);

		// TODO 获取接待的所有客户
		List<Customer> customers = new ArrayList<Customer>();
		for (Order order : orders) {
			if (!customers.contains(order.getCustomer())) {
				customers.add(order.getCustomer());
			}
		}

		// TODO 加入访问的客户信息
		List<Customer> customerOnePage = subList(customers, page_size
				* (page_num - 1), page_size * page_num);
		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customers", customers), callback);
	}

	// 获取某客户的
	@RequestMapping("/getVisitByCustomerTel.php")
	public ResponseEntity<String> getVisitByCustomerTel(
			@RequestParam(value = "customerTel", required = true) String customerTel,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		// TODO 该客户的信息
		List<Visit> list = visitService.getVisitByCustomerTel(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "list", list), callback);
	}

	@RequestMapping("/addVisit.php")
	public ResponseEntity<String> addVisit(
			@RequestParam(value = "customerTel", required = true) String customerTel,
			@RequestParam(value = "intentionVehicleStyleId", required = true) Integer intentionVehicleStyleId,
			@RequestParam(value = "price", required = true) Float price,
			Integer discountType, Float quota, Integer expecteDiscountType,
			Float expectedQuota, String note, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		visitService.addVisit(intentionVehicleStyleId, customerService
				.getCustomer(customerTel), employeeService
				.getEmployee(employeeTel), price, new Discount(discountType,
				quota), new Discount(expecteDiscountType, expectedQuota), note,
				"2015-10-07 00:31");

		// TODO 该客户的信息
		List<Visit> list = visitService.getVisitByCustomerTel(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "list", list), callback);
	}

	// TODO 修改进店信息
	@RequestMapping("/modifyVisit.php")
	public ResponseEntity<String> modifyVisitByCustomerTel(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "customerTel", required = true) String customerTel,
			@RequestParam(value = "intentionVehicleStyleId", required = true) Integer intentionVehicleStyleId,
			@RequestParam(value = "price", required = true) Float price,
			Integer discountType, Float quota, Integer expecteDiscountType,
			Float expectedQuota, String note, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		visitService.modifyVisit(id, intentionVehicleStyleId, customerService
				.getCustomer(customerTel), employeeService
				.getEmployee(employeeTel), price, new Discount(discountType,
				quota), new Discount(expecteDiscountType, expectedQuota), note,
				"2015-09-30");
		Visit visit = visitService.getVisitById(id);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "visit", visit), callback);
	}

	@RequestMapping("/delVisit.php")
	public ResponseEntity<String> delVisit(Integer id, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		visitService.delVisit(id);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "删除成功"), callback);
	}
}
