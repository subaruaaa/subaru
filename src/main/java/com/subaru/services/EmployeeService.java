package com.subaru.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;
import com.subaru.models.Customer;
import com.subaru.models.Employee;

@Component
public class EmployeeService {

	@Autowired
	SubaruDao subaruDao;

	public Employee getEmployee(String tel) {
		Map<String, Object> infos = subaruDao.getEmployeeByTel(tel);
		if (!infos.isEmpty()) {
			String name = infos.get("name").toString();
			String email = infos.get("email").toString();
			String identificationCard = infos.get("identificationCard")
					.toString();
			String birthday = infos.get("birthday").toString();
			String status = infos.get("status").toString();
			String add = infos.get("add").toString();
			String position = infos.get("position").toString();
			String store = infos.get("store").toString();
			String totalLose = infos.get("totalLose").toString();
			String thisMonthLose = infos.get("thisMonthLose").toString();

			return new Employee(name, tel, email, identificationCard, birthday,
					status, add, position, store);
		} else {
			return null;
		}
	}

	public Customer saveEmployee(Employee employee) {
		subaruDao.saveEmployee(employee.name, employee.tel, employee.email,
				employee.identificationCard, employee.birthday,
				employee.status, employee.add, employee.position,
				employee.store, employee.totalLose + "", employee.thisMonthLose
						+ "");

		return null;
	}
	
	public void delEmployee(String tel){
		subaruDao.delEmployee(tel);
	}
}
