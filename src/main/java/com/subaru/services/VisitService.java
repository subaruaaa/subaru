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

	// 获取客户的访问列表
	public List<Visit> getVisitByCustomerId(Integer customerId) {
		List<Map<String, Object>> results = subaruDao
				.getVisitByCustomerId(customerId);
		List<Visit> list = new ArrayList<Visit>();
		for (Map<String, Object> result : results) {
			Integer visitId = Integer.valueOf(result.get("visitId").toString());
			Customer customer = customerService.getCustomer(customerId);
			Integer intentionVehicleStyleId = Integer.valueOf(result.get(
					"intentionVehicleStyleId").toString());
			Employee employee = employeeService.getEmployee(Integer
					.parseInt(result.get("employeeId").toString()));
			Float price = Float.valueOf(result.get("price").toString());
			String discountId = result.get("discountId").toString();
			String quota = result.get("quota").toString();
			String expectedDisCountId = result.get("expectedDisCountId")
					.toString();
			String expectedQuota = result.get("expectedQuota").toString();
			String note = result.get("note").toString();
			String visitTime = result.get("visitTime").toString();
			Integer installationId = Integer.valueOf(result.get(
					"installationId").toString());

			Visit visit = new Visit(visitId, customer, intentionVehicleStyleId,
					employee, price, discountId, quota, expectedDisCountId,
					expectedQuota, note, visitTime, installationId);
			list.add(visit);
		}

		return list;
	}

	// 增加一条访问记录
	public void addVisit(Integer intentionVehicleStyleId, Customer customer,
			Employee employee, Float price, String discountId, String quota,
			String expectedDisCountId, String expectedQuota, String note,
			String visitTime, Integer installationId) {
		subaruDao.addVisit(intentionVehicleStyleId, customer, employee, price,
				discountId, quota, expectedDisCountId, expectedQuota, note,
				visitTime, installationId);
	}

	// 修改某条访问列表
	public void modifyVisit(Integer visitId, Integer intentionVehicleStyleId,
			Customer customer, Employee employee, Float price,
			String discountId, String quota, String expectedDisCountId,
			String expectedQuota, String note, String visitTime,
			Integer installationId) {
		subaruDao.modifyVisit(visitId, intentionVehicleStyleId, customer,
				employee, price, discountId, quota, expectedDisCountId,
				expectedQuota, note, visitTime, installationId);
	}

	public Visit getVisitById(Integer visitId) {
		List<Map<String, Object>> results = subaruDao.getVisitById(visitId);
		if (results.size() == 1) {
			Map<String, Object> result = results.get(0);
			Customer customer = customerService.getCustomer(Integer
					.parseInt(result.get("customerId").toString()));
			Integer intentionVehicleStyleId = Integer.valueOf(result.get(
					"intentionVehicleStyleId").toString());
			Employee employee = employeeService.getEmployee(Integer
					.parseInt(result.get("employeeId").toString()));
			Float price = Float.valueOf(result.get("price").toString());
			String discountId = result.get("discountId").toString();
			String quota = result.get("quota").toString();
			String expectedDisCountId = result.get("expectedDisCountId")
					.toString();
			String expectedQuota = result.get("expectedQuota").toString();

			String note = result.get("note").toString();
			String visitTime = result.get("visitTime").toString();
			Integer installationId = Integer.valueOf(result.get(
					"installationId").toString());

			Visit visit = new Visit(visitId, customer, intentionVehicleStyleId,
					employee, price, discountId, quota, expectedDisCountId,
					expectedQuota, note, visitTime, installationId);
			return visit;
		}

		return null;
	}

	// 删除某条列表
	public void delVisit(Integer visitId) {
		subaruDao.delVisit(visitId);
	}

}
