package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subaru.constants.FIELDS;
import com.subaru.dao.SubaruDao;
import com.subaru.models.Discount;
import com.subaru.models.Order;
import com.subaru.models.Payment;
import com.subaru.services.OrderService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	// 获取订单（结单信息）
	@RequestMapping("/getOrder.php")
	public ResponseEntity<String> getOrder(
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

		// getOrder by customerTel,employeeTel, 支持其中一个没有的情况
		List<Order> list = orderService.gerOrder(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "list", list), callback);
	}

	@RequestMapping("/createOrder.php")
	public ResponseEntity<String> createOrder(
			@RequestParam(value = "customerTel", required = true) String customerTel,
			@RequestParam(value = "vehicleStyleId", required = true) Integer vehicleStyleId,
			@RequestParam(value = "vehicleIdentificationNumber", required = true) String vehicleIdentificationNumber,
			@RequestParam(value = "price", required = true) Float price,
			@RequestParam(value = "invoicePrice", required = true) Float invoicePrice,
			@RequestParam(value = "paymentType", required = true) Integer paymentType,
			@RequestParam(value = "mortgageBank", required = true) String mortgageBank,
			@RequestParam(value = "mortgageAmount", required = true) Float mortgageAmount,
			@RequestParam(value = "disCountType", required = true) Integer disCountType,
			@RequestParam(value = "quota", required = true) Float quota,
			@RequestParam(value = "purchaseQuantity", required = true) Integer purchaseQuantity,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String orderDate = Common.GetNowDate();
		Payment payment = new Payment(paymentType, mortgageBank, mortgageAmount);
		Discount discount = new Discount(disCountType, quota);
		String employeeTel = Common.getLoginTel(request);

		orderService.createOrder(orderDate, customerTel, vehicleStyleId,
				vehicleIdentificationNumber, price, invoicePrice, payment,
				discount, purchaseQuantity, employeeTel);

		// getOrder by customerTel,employeeTel, 支持其中一个没有的情况
		List<Order> list = orderService.gerOrder(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "list", list), callback);
	}

	@RequestMapping("/modifyOrder.php")
	public ResponseEntity<String> modifyOrder(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "customerTel", required = true) String customerTel,
			@RequestParam(value = "vehicleStyleId", required = true) Integer vehicleStyleId,
			@RequestParam(value = "vehicleIdentificationNumber", required = true) String vehicleIdentificationNumber,
			@RequestParam(value = "price", required = true) Float price,
			@RequestParam(value = "invoicePrice", required = true) Float invoicePrice,
			@RequestParam(value = "paymentType", required = true) Integer paymentType,
			@RequestParam(value = "mortgageBank", required = true) String mortgageBank,
			@RequestParam(value = "mortgageAmount", required = true) Float mortgageAmount,
			@RequestParam(value = "disCountType", required = true) Integer disCountType,
			@RequestParam(value = "quota", required = true) Float quota,
			@RequestParam(value = "purchaseQuantity", required = true) Integer purchaseQuantity,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		String orderDate = Common.GetNowDate();
		Payment payment = new Payment(paymentType, mortgageBank, mortgageAmount);
		Discount discount = new Discount(disCountType, quota);
		String employeeTel = Common.getLoginTel(request);

		orderService.modifyOrder(id, orderDate, customerTel, vehicleStyleId,
				vehicleIdentificationNumber, price, invoicePrice, payment,
				discount, purchaseQuantity, employeeTel);

		// getOrder by customerTel,employeeTel, 支持其中一个没有的情况
		List<Order> list = orderService.gerOrder(customerTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "list", list), callback);
	}

	// 删除结单信息
	@RequestMapping("/delOrder.php")
	public ResponseEntity<String> delOrder(
			@RequestParam(value = "id", required = true) Integer id,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		orderService.delOrder(id);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "id", id, FIELDS.MESSAGE, "订单已删除"),
				callback);
	}

}
