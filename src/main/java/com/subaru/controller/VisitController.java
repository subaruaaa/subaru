package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subaru.constants.FIELDS;
import com.subaru.models.Customer;
import com.subaru.models.Employee;
import com.subaru.models.Visit;
import com.subaru.services.CustomerService;
import com.subaru.services.EmployeeService;
import com.subaru.services.OrderService;
import com.subaru.services.SearchService;
import com.subaru.services.VisitService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

import static com.subaru.types.functions.subList;

@Controller
public class VisitController {
	@Autowired
	VisitService visitService;

	@Autowired
	OrderService orderService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	CustomerService customerService;
	@Autowired
	SearchService searchService;

	// // 获取接待的所有客户
	@RequestMapping("/getCustomersAll.php")
	public ResponseEntity<String> getCustomersAll(
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
		Employee employee = employeeService.getEmployee(employeeTel);

		List<Map<String, Object>> list = searchService
				.searchVisitCustomerByEmployee(employee);

		List<Map<String, Object>> customerOnePage = subList(list, page_size
				* (page_num - 1), page_size * page_num);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customerOnePage", customerOnePage),
				callback);
	}

	// 获取已完成的客户
	@RequestMapping("/getCustomersDone.php")
	public ResponseEntity<String> getCustomersDone(
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
		Employee employee = employeeService.getEmployee(employeeTel);

		List<Map<String, Object>> list = searchService
				.searchVisitCustomerByEmployee(employee);
		List<Map<String, Object>> visitDone = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> l : list) {
			if (Integer.valueOf(l.get("isDone").toString()).equals(1)) {
				visitDone.add(l);
			}
		}

		List<Map<String, Object>> customerOnePage = subList(visitDone, page_size
				* (page_num - 1), page_size * page_num);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customerOnePage", customerOnePage),
				callback);
	}
	
	// 获取已完成的客户
	@RequestMapping("/getCustomersRecently.php")
	public ResponseEntity<String> getCustomersRecently(
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
		Employee employee = employeeService.getEmployee(employeeTel);

		List<Map<String, Object>> list = searchService
				.searchVisitCustomerByEmployee(employee);
		List<Map<String, Object>> visitDone = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> l : list) {
			if (Integer.valueOf(l.get("isDone").toString()).equals(0)) {
				visitDone.add(l);
			}
		}

		List<Map<String, Object>> customerOnePage = subList(visitDone, page_size
				* (page_num - 1), page_size * page_num);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "customerOnePage", customerOnePage),
				callback);
	}
	
	// // 近期在跟
	// @RequestMapping("/getCustomersRecently.php")
	// public ResponseEntity<String> getVisitRecently(
	// @RequestParam(value = "page_size", defaultValue =
	// FIELDS.DEFAULT_PAGE_SIZE_STR) Integer page_size,
	// @RequestParam(value = "page_num", defaultValue =
	// FIELDS.DEFAULT_PAGE_NUM_STR) Integer page_num,
	// String callback, HttpServletRequest request,
	// HttpServletResponse response) {
	// if (!LoginHelper.isLogin(request)) {
	// return jsonpEntity(
	// map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
	// FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
	// FIELDS.NOT_LOGIN), callback);
	// }
	//
	// String employeeTel = Common.getLoginTel(request);
	// List<Order> orders = orderService.getOrdersByEmployee(employeeTel);
	//
	// // TODO 获取接待的所有客户
	// List<Customer> customers = new ArrayList<Customer>();
	// for (Order order : orders) {
	// if (!customers.contains(order.getCustomer())) {
	// customers.add(order.getCustomer());
	// }
	// }
	//
	// // TODO 加入访问的客户信息
	// List<Customer> customerOnePage = subList(customers, page_size
	// * (page_num - 1), page_size * page_num);
	// return jsonpEntity(
	// map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
	// FIELDS.CODE_SUCCESS, "customers", customers), callback);
	// }

	// 获取某客户的
	@RequestMapping("/getVisitByCustomerId.php")
	public ResponseEntity<String> getVisitByCustomerId(
			@RequestParam(value = "customerId", required = true) Integer customerId,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE,
							"不存在此客户"), callback);
		}

		// TODO 该客户的信息
		List<Visit> list = visitService.getVisitByCustomerId(customerId);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "list", list), callback);
	}

	@RequestMapping("/addVisit.php")
	public ResponseEntity<String> addVisit(
			@RequestParam(value = "customerId", required = true) Integer customerId,
			@RequestParam(value = "intentionVehicleStyleId", required = true) Integer intentionVehicleStyleId,
			@RequestParam(value = "price", required = true, defaultValue = "0") Float price,
			@RequestParam(value = "discountId", required = true, defaultValue = "0") String discountId,
			@RequestParam(value = "quota", required = true, defaultValue = "0") String quota,
			@RequestParam(value = "expectedDisCountId", required = true, defaultValue = "0") String expectedDisCountId,
			@RequestParam(value = "expectedQuota", required = true, defaultValue = "0") String expectedQuota,
			@RequestParam(value = "note", required = true) String note,
			@RequestParam(value = "installationId", required = true, defaultValue = "0") Integer installationId,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		visitService.addVisit(intentionVehicleStyleId,
				customerService.getCustomer(customerId),
				employeeService.getEmployee(employeeTel), price, discountId,
				quota, expectedDisCountId, expectedQuota, note,
				Common.GetNowDate(), installationId);

		// TODO 该客户的信息
		List<Visit> list = visitService.getVisitByCustomerId(customerId);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "visit", list.get(0)), callback);
	}

	// TODO 修改进店信息
	@RequestMapping("/modifyVisit.php")
	public ResponseEntity<String> modifyVisitByCustomerId(
			@RequestParam(value = "visitId", required = true) Integer visitId,
			@RequestParam(value = "customerId", required = true) Integer customerId,
			@RequestParam(value = "intentionVehicleStyleId", required = true) Integer intentionVehicleStyleId,
			@RequestParam(value = "price", required = true) Float price,
			@RequestParam(value = "discountId", required = true) String discountId,
			@RequestParam(value = "quota", required = true) String quota,
			@RequestParam(value = "expectedDisCountId", required = true) String expectedDisCountId,
			@RequestParam(value = "expectedQuota", required = true) String expectedQuota,
			@RequestParam(value = "note", required = true) String note,
			@RequestParam(value = "installationId", required = true) Integer installationId,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE,
							"不存在此客户"), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		visitService.modifyVisit(visitId, intentionVehicleStyleId, customer,
				employeeService.getEmployee(employeeTel), price, discountId,
				quota, expectedDisCountId, expectedQuota, note,
				Common.GetNowDate(), installationId);
		Visit visit = visitService.getVisitById(visitId);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "visit", visit), callback);
	}

	@RequestMapping("/delVisit.php")
	public ResponseEntity<String> delVisit(
			@RequestParam(value = "visitId", required = true) Integer visitId,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		visitService.delVisit(visitId);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "删除成功"), callback);
	}
}
