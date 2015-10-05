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

	public List<Order> gerOrder(String customerTel) {
		List<Map<String, Object>> infos = subaruDao.getOrder(customerTel);
		if (infos == null || infos.isEmpty()) {
			return new ArrayList<Order>();
		}
		List<Order> list = new ArrayList<Order>();
		for (Map<String, Object> info : infos) {
			int id = Integer.valueOf(info.get("id").toString());
			Customer customer = customerService.getCustomer(info.get(
					"customerTel").toString());
			String orderDate = info.get("orderDate").toString();
			VehicleStyle vehicleStyle = null;
			String vehicleIdentificationNumber = info.get(
					"vehicleIdentificationNumber").toString();
			Float price = Float.valueOf(info.get("price").toString());
			Float invoicePrice = Float.valueOf(info.get("invoicePrice")
					.toString());
			Payment payment = new Payment(info.get("payment").toString());
			Discount discount = null;
			int purchaseQuantity = Integer.valueOf(info.get("purchaseQuantity")
					.toString());
			Employee employee = employeeService.getEmployee(info.get(
					"employeeTel").toString());
			Order order = new Order(customer, orderDate, vehicleStyle,
					vehicleIdentificationNumber, price, invoicePrice, payment,
					discount, purchaseQuantity, employee);
			list.add(order);
		}

		return list;

	}
}
