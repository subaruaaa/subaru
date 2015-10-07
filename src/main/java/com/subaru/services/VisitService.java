package com.subaru.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;
import com.subaru.models.Customer;
import com.subaru.models.Discount;
import com.subaru.models.Employee;
import com.subaru.models.Visit;

@Component
public class VisitService {
	@Autowired
	SubaruDao subaruDao;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	CustomerService customerService;

	// 获取某人的访问列表
	public List<Visit> getVisitByCustomerTel(String customerTel) {
		List<Map<String, Object>> results = subaruDao
				.getVisitByCustomerTel(customerTel);
		List<Visit> list = new ArrayList<Visit>();
		for (Map<String, Object> result : results) {
			Integer id = Integer.valueOf(result.get("id").toString());
			Customer customer = customerService.getCustomer(customerTel);
			Integer intentionVehicleStyleId = Integer.valueOf(result.get(
					"intentionVehicleStyleId").toString());
			Employee employee = employeeService.getEmployee(result.get(
					"employeeTel").toString());
			Float price = Float.valueOf(result.get("price").toString());
			Discount discount = new Discount(result.get("discount").toString());
			Discount expectedDiscount = new Discount(result.get(
					"expectedDiscount").toString());
			String note = result.get("note").toString();
			String visitTime = result.get("visitTime").toString();

			Visit visit = new Visit(id, customer, intentionVehicleStyleId,
					employee, price, discount, expectedDiscount, note,
					visitTime);
			list.add(visit);
		}

		return list;
	}

	// 增加一条访问记录
	public void addVisit(Integer intentionVehicleStyleId, Customer customer,
			Employee employee, Float price, Discount discount,
			Discount expectedDiscount, String note, String visitTime) {
		subaruDao.addVisit(intentionVehicleStyleId, customer, employee, price,
				discount, expectedDiscount, note, visitTime);
	}

	// 修改某条访问列表
	public void modifyVisit(Integer id, Integer intentionVehicleStyleId,
			Customer customer, Employee employee, Float price,
			Discount discount, Discount expectedDiscount, String note,
			String visitTime) {
		subaruDao.modifyVisit(id, intentionVehicleStyleId, customer, employee,
				price, discount, expectedDiscount, note, visitTime);
	}

	public Visit getVisitById(Integer id) {
		List<Map<String, Object>> results = subaruDao.getVisitById(id);
		if (results.size() == 1) {
			Map<String, Object> result = results.get(0);
			Customer customer = customerService.getCustomer(result.get(
					"customerTel").toString());
			Integer intentionVehicleStyleId = Integer.valueOf(result.get(
					"intentionVehicleStyleId").toString());
			Employee employee = employeeService.getEmployee(result.get(
					"employeeTel").toString());
			Float price = Float.valueOf(result.get("price").toString());
			Discount discount = new Discount(result.get("discount").toString());
			Discount expectedDiscount = new Discount(result.get(
					"expectedDiscount").toString());
			String note = result.get("note").toString();
			String visitTime = result.get("visitTime").toString();

			Visit visit = new Visit(id, customer, intentionVehicleStyleId,
					employee, price, discount, expectedDiscount, note,
					visitTime);
			return visit;
		}

		return null;
	}

	// 删除某条列表
	public void delVisit(Integer id) {
		subaruDao.delVisit(id);
	}

}
