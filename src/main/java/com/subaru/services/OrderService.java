package com.subaru.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;
import com.subaru.models.Customer;
import com.subaru.models.Discount;
import com.subaru.models.Employee;
import com.subaru.models.Order;
import com.subaru.models.Payment;
import com.subaru.models.VehicleStyle;

@Component
public class OrderService {
	@Autowired
	SubaruDao subaruDao;

	@Autowired
	CustomerService customerService;

	@Autowired
	EmployeeService employeeService;

	public List<Order> getOrdersByEmployee(Integer employeeId) {
		List<Map<String, Object>> infos = subaruDao
				.getOrdersByEmployee(employeeId);
		if (infos == null || infos.isEmpty()) {
			return new ArrayList<Order>();
		}
		List<Order> list = new ArrayList<Order>();
		Employee employee = employeeService.getEmployee(employeeId);
		for (Map<String, Object> info : infos) {
			Integer orderId = Integer.valueOf(info.get("orderId").toString());
			Integer customerId = Integer.valueOf(info.get("customerId")
					.toString());
			Customer customer = customerService.getCustomer(customerId);
			String orderDate = info.get("orderDate").toString();
			Integer vehicleStyleId = Integer.valueOf(info.get("vehicleStyleId")
					.toString());
			String vehicleIdentificationNumber = info.get(
					"vehicleIdentificationNumber").toString();
			Float price = Float.valueOf(info.get("price").toString());
			Float invoicePrice = Float.valueOf(info.get("invoicePrice")
					.toString());
			Integer purchaseQuantity = Integer.valueOf(info.get(
					"purchaseQuantity").toString());
			Integer discountId = Integer.valueOf(info.get("discountId")
					.toString());
			String quota = info.get("quota").toString();
			Integer paymentTypeId = Integer.valueOf(info.get("paymentTypeId")
					.toString());
			Integer mortgageBankId = Integer.valueOf(info.get("mortgageBankId")
					.toString());
			Float mortgageAmount = Float.valueOf(info.get("mortgageAmount")
					.toString());

			Order order = new Order(orderId, customer, orderDate,
					vehicleStyleId, vehicleIdentificationNumber, price,
					invoicePrice, purchaseQuantity, employee, discountId,
					quota, paymentTypeId, mortgageBankId, mortgageAmount);
			list.add(order);
		}

		return list;
	}
	
	public List<Map<String, Object>> getOrderByOrderId(Integer orderId){
		List<Map<String, Object>> orders  = subaruDao.getByOrderId(orderId);
		return orders;
	}

	public List<Order> gerOrders(Integer customerId) {
		List<Map<String, Object>> infos = subaruDao.getOrdersByCustomerId(customerId);
		if (infos == null || infos.isEmpty()) {
			return new ArrayList<Order>();
		}
		List<Order> list = new ArrayList<Order>();
		Customer customer = customerService.getCustomer(customerId);
		for (Map<String, Object> info : infos) {
			Integer orderId = Integer.valueOf(info.get("orderId").toString());
			String orderDate = info.get("orderDate").toString();
			Integer vehicleStyleId = Integer.valueOf(info.get("vehicleStyleId")
					.toString());
			String vehicleIdentificationNumber = info.get(
					"vehicleIdentificationNumber").toString();
			Float price = Float.valueOf(info.get("price").toString());
			Float invoicePrice = Float.valueOf(info.get("invoicePrice")
					.toString());
			Integer purchaseQuantity = Integer.valueOf(info.get(
					"purchaseQuantity").toString());
			Integer employeeId = Integer.valueOf(info.get("employeeId")
					.toString());
			Employee employee = employeeService.getEmployee(employeeId);
			Integer discountId = Integer.valueOf(info.get("discountId")
					.toString());
			String quota = info.get("quota").toString();
			Integer paymentTypeId = Integer.valueOf(info.get("paymentTypeId")
					.toString());
			Integer mortgageBankId = Integer.valueOf(info.get("mortgageBankId")
					.toString());
			Float mortgageAmount = Float.valueOf(info.get("mortgageAmount")
					.toString());

			Order order = new Order(orderId, customer, orderDate,
					vehicleStyleId, vehicleIdentificationNumber, price,
					invoicePrice, purchaseQuantity, employee, discountId,
					quota, paymentTypeId, mortgageBankId, mortgageAmount);

			list.add(order);
		}

		return list;
	}

	public void createOrder(String orderDate, Integer customerId,
			int vehicleStyleId, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Integer discountId, String quota,
			int purchaseQuantity, Integer employeeId, Integer paymentTypeId,
			Integer mortgageBankId, Float mortgageAmount) {
		subaruDao.addOrder(orderDate, customerId, vehicleStyleId,
				vehicleIdentificationNumber, price, invoicePrice, discountId,
				quota, purchaseQuantity, employeeId, paymentTypeId,
				mortgageBankId, mortgageAmount);

	}

	public void modifyOrder(Integer orderId, Integer customerId,
			int vehicleStyleId, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Integer discountId, String quota,
			int purchaseQuantity, Integer employeeId, Integer paymentTypeId,
			Integer mortgageBankId, Float mortgageAmount) {
		subaruDao.modifyOrder(orderId, customerId,
				vehicleStyleId, vehicleIdentificationNumber,
				price, invoicePrice, discountId, quota,
				purchaseQuantity, employeeId, paymentTypeId,
				mortgageBankId, mortgageAmount);

	}

	public void delOrder(Integer orderId) {
		subaruDao.delOrder(orderId);
	}
}
