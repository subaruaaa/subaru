package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.types.functions.subList;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.List;
import java.util.Map;

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
import com.subaru.models.Employee;
import com.subaru.models.Order;
import com.subaru.services.CustomerService;
import com.subaru.services.EmployeeService;
import com.subaru.services.OrderService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	CustomerService customerService;

	// 获取订单（结单信息）
	@RequestMapping("/getOrders.php")
	public ResponseEntity<String> getOrders(@RequestParam(value = "customerId", required = true) Integer customerId,
			@RequestParam(value = "page_size", defaultValue = FIELDS.DEFAULT_PAGE_SIZE_STR) Integer page_size,
			@RequestParam(value = "page_num", defaultValue = FIELDS.DEFAULT_PAGE_NUM_STR) Integer page_num, String callback, HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE, FIELDS.NOT_LOGIN), callback);
		}
		String employeeTel = Common.getLoginTel(request);

		// TODO getOrder by customerTel,employeeTel, 支持其中一个没有的情况
		List<Order> list = orderService.gerOrders(customerId);
		List<Order> OrderOnePage = subList(list, page_size * (page_num - 1), page_size * page_num);
		int totalPage = list.size() / page_size + (list.size() % page_size == 0 ? 0 : 1);

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_SUCCESS, "list", OrderOnePage, "totalPage", totalPage), callback);
	}

	@RequestMapping("/createOrder.php")
	public ResponseEntity<String> createOrder(@RequestParam(value = "customerId", required = true) Integer customerId, @RequestParam(value = "vehicleStyleId", required = true) Integer vehicleStyleId,
			@RequestParam(value = "vehicleIdentificationNumber", required = true) String vehicleIdentificationNumber, @RequestParam(value = "price", required = true) Float price,
			@RequestParam(value = "invoicePrice", required = true) Float invoicePrice, @RequestParam(value = "paymentTypeId", required = true) Integer paymentTypeId,
			@RequestParam(value = "mortgageBankId", defaultValue = "0") Integer mortgageBankId, @RequestParam(value = "mortgageAmount", defaultValue = "0") Float mortgageAmount,
			@RequestParam(value = "discountId", required = true) Integer discountId, @RequestParam(value = "quota", required = true) String quota,
			@RequestParam(value = "purchaseQuantity", required = true) Integer purchaseQuantity, String callback, HttpServletRequest request, HttpServletResponse response) {

		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE, FIELDS.NOT_LOGIN), callback);
		}

		String orderDate = Common.GetNowDate();
		String employeeTel = Common.getLoginTel(request);
		Employee employee = employeeService.getEmployee(employeeTel);
		if (customerId == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE, "不存在此客户"), callback);
		}
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE, "不存在此客户"), callback);
		}

		// 请选择付款方式
		if (paymentTypeId == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_PAYMENTTYPE_ERROR, FIELDS.MESSAGE, FIELDS.PAYMENTTYPE_ERROR), callback);
		}

		// 请填写开票价格
		if (invoicePrice == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_INVOICEPRICE_NULL, FIELDS.MESSAGE, FIELDS.INVOICEPRICE_NULL_ERROR), callback);
		}

		// 请填写车身价
		if (price == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_PRICE_NULL, FIELDS.MESSAGE, FIELDS.PRICE_NULL_ERROR), callback);
		}

		// 请填车架号
		if (!StringUtils.hasText(vehicleIdentificationNumber)) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_VEHICLEIDENTIFICATIONNUMBER_NULL, FIELDS.MESSAGE, FIELDS.VEHICLEIDENTIFICATIONNUMBER_NULL_ERROR), callback);
		}

		// 请选择车型
		if (vehicleStyleId == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_VEHICLESTYLEID_NULL, FIELDS.MESSAGE, FIELDS.VEHICLESTYLEID_NULL_ERROR), callback);
		}

		// 请填写购买数量
		if (purchaseQuantity == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_PURCHASEQUANTITY_NOT_EXIST, FIELDS.MESSAGE, FIELDS.PURCHASEQUANTITY_NOT_EXIST), callback);
		}

		if (mortgageBankId == null) {
			mortgageBankId = 0;
		}
		if (mortgageAmount == null) {
			mortgageAmount = 0.0f;
		}

		orderService.createOrder(orderDate, customerId, vehicleStyleId, vehicleIdentificationNumber, price, invoicePrice, discountId, quota, purchaseQuantity, employee.getEmployeeId(), paymentTypeId,
				mortgageBankId, mortgageAmount);

		List<Order> list = orderService.gerOrders(customerId);
		Order order = list.get(0);

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_SUCCESS, "order", order), callback);
	}

	@RequestMapping("/modifyOrder.php")
	public ResponseEntity<String> modifyOrder(@RequestParam(value = "orderId", required = true, defaultValue = "0") Integer orderId,
			@RequestParam(value = "customerId", required = true) Integer customerId, @RequestParam(value = "vehicleStyleId", required = true) Integer vehicleStyleId,
			@RequestParam(value = "vehicleIdentificationNumber", required = true) String vehicleIdentificationNumber, @RequestParam(value = "price", required = true) Float price,
			@RequestParam(value = "invoicePrice", required = true) Float invoicePrice, @RequestParam(value = "paymentTypeId", required = true) Integer paymentTypeId,
			@RequestParam(value = "mortgageBankId", defaultValue = "0") Integer mortgageBankId, @RequestParam(value = "mortgageAmount", defaultValue = "0") Float mortgageAmount,
			@RequestParam(value = "discountId", required = true) Integer discountId, @RequestParam(value = "quota", required = true) String quota,
			@RequestParam(value = "purchaseQuantity", required = true) Integer purchaseQuantity, String callback, HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE, FIELDS.NOT_LOGIN), callback);
		}

		List<Map<String, Object>> order = orderService.getOrderByOrderId(orderId);
		if (order.size() != 1) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_ORDER_NOT_EXIST, FIELDS.MESSAGE, FIELDS.ORDER_NOT_EXIST), callback);
		}

		String employeeTel = Common.getLoginTel(request);
		Employee employee = employeeService.getEmployee(employeeTel);
		if (customerId == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE, "不存在此客户"), callback);
		}
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_CUSTOMER_NOT_EXIST, FIELDS.MESSAGE, "不存在此客户"), callback);
		}

		// 请选择付款方式
		if (paymentTypeId == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_PAYMENTTYPE_ERROR, FIELDS.MESSAGE, FIELDS.PAYMENTTYPE_ERROR), callback);
		}

		// 请填写开票价格
		if (invoicePrice == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_INVOICEPRICE_NULL, FIELDS.MESSAGE, FIELDS.INVOICEPRICE_NULL_ERROR), callback);
		}

		// 请填写车身价
		if (price == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_PRICE_NULL, FIELDS.MESSAGE, FIELDS.PRICE_NULL_ERROR), callback);
		}

		// 请填车架号
		if (!StringUtils.hasText(vehicleIdentificationNumber)) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_VEHICLEIDENTIFICATIONNUMBER_NULL, FIELDS.MESSAGE, FIELDS.VEHICLEIDENTIFICATIONNUMBER_NULL_ERROR), callback);
		}

		// 请选择车型
		if (vehicleStyleId == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_VEHICLESTYLEID_NULL, FIELDS.MESSAGE, FIELDS.VEHICLESTYLEID_NULL_ERROR), callback);
		}

		// 请填写购买数量
		if (purchaseQuantity == null) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_PURCHASEQUANTITY_NOT_EXIST, FIELDS.MESSAGE, FIELDS.PURCHASEQUANTITY_NOT_EXIST), callback);
		}

		if (mortgageBankId == null) {
			mortgageBankId = 0;
		}
		if (mortgageAmount == null) {
			mortgageAmount = 0.0f;
		}
		orderService.modifyOrder(orderId, customerId, vehicleStyleId, vehicleIdentificationNumber, price, invoicePrice, discountId, quota, purchaseQuantity, employee.getEmployeeId(), paymentTypeId,
				mortgageBankId, mortgageAmount);
		//
		// // getOrder by customerTel,employeeTel, 支持其中一个没有的情况
		List<Order> list = orderService.gerOrders(customerId);

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_SUCCESS, "order", list.get(0)), callback);
	}

	// 删除结单信息
	@RequestMapping("/delOrder.php")
	public ResponseEntity<String> delOrder(@RequestParam(value = "orderId", required = true) Integer orderId, String callback, HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE, FIELDS.NOT_LOGIN), callback);
		}

		orderService.delOrder(orderId);

		return jsonpEntity(map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE, FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "订单已删除"), callback);
	}

}
