package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.constants.FIELDS;
import com.subaru.dao.SubaruDao;
import com.subaru.models.Employee;
import com.subaru.services.EmployeeService;
import com.subaru.utils.Common;
import com.subaru.utils.HashHelper;
import com.subaru.utils.LoginHelper;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/getEmployee.php")
	public ResponseEntity<String> getEmployee(String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		String employeeTel = Common.getLoginTel(request);
		Employee employee = employeeService.getEmployee(employeeTel);
		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "employee", employee), callback);
	}

	// TODO 修改,修改到电话很麻烦。。。这里个人信息还好一些
	@RequestMapping("/modifyEmployee.php")
	public ResponseEntity<String> modifyEmployee(String name,
			String email, String identificationCard, String birthday,
			String status, String add, String position, String store,
			String totalLose, String thisMonthLose, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		String employeeTel = Common.getLoginTel(request);
		Employee employee = new Employee(name, employeeTel, email, identificationCard, birthday, status, add, position, store);
		employeeService.saveEmployee(employee);
		employee = employeeService.getEmployee(employeeTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "employee", employee), callback);
	}

	// TODO 删除
	@RequestMapping("/delEmployee.php")
	public ResponseEntity<String> delEmployee(String employeeTel, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		employeeService.delEmployee(employeeTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "刪除成功"), callback);
	}

}
