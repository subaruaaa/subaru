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

	public Employee getEmployee(String employeeTel) {
		Map<String, Object> infos = subaruDao.getEmployeeByTel(employeeTel);
		if (!infos.isEmpty()) {
			Integer employeeId = Integer.valueOf(infos.get("employeeId").toString());
			String name = infos.get("name").toString();
			String email = infos.get("email").toString();
			String identityCard = infos.get("identityCard")
					.toString();
			String birthday = infos.get("birthday").toString();
			Integer status = Integer.valueOf(infos.get("statusId").toString());
			String add = infos.get("add").toString();
			Integer positionId = Integer.valueOf(infos.get("positionId").toString());
			Integer storeId = Integer.valueOf(infos.get("storeId").toString());
			// TODO 这里需要处理
			String totalLose = infos.get("totalLose").toString();
			String thisMonthLose = infos.get("thisMonthLose").toString();

			return new Employee(employeeId, name, employeeTel, email, identityCard,
					birthday, status, add, positionId, storeId);
		} else {
			return null;
		}
	}

	public Employee getEmployee(Integer employeeId) {
		Map<String, Object> infos = subaruDao.getEmployeeById(employeeId);
		if (!infos.isEmpty()) {
			String name = infos.get("name").toString();
			String email = infos.get("email").toString();
			String identityCard = infos.get("identityCard")
					.toString();
			String birthday = infos.get("birthday").toString();
			Integer statusId = Integer.valueOf(infos.get("statusId").toString());
			String add = infos.get("add").toString();
			Integer positionId = Integer.valueOf(infos.get("positionId").toString());
			Integer storeId = Integer.parseInt(infos.get("storeId").toString());
			// TODO 这里需要处理
			String totalLose = infos.get("totalLose").toString();
			String thisMonthLose = infos.get("thisMonthLose").toString();
			String employeeTel = infos.get("employeeTel").toString();

			return new Employee(employeeId, name, employeeTel, email, identityCard,
					birthday, statusId, add, positionId, storeId);
		} else {
			return null;
		}
	}

	public void saveEmployee(Employee employee) {
		subaruDao.saveEmployee(employee.emplyeeId, employee.name, employee.employeeTel,
				employee.email, employee.identityCard, employee.birthday,
				employee.statusId, employee.add, employee.positionId,
				employee.storeId, employee.totalLose + "", employee.thisMonthLose
						+ "");
	}

	public void delEmployee(String tel) {
		subaruDao.delEmployee(tel);
	}
}
