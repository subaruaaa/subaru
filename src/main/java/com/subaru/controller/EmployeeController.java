package com.subaru.controller;

import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.models.Employee;

@Controller
public class EmployeeController {
	@RequestMapping("/getEmployee.php")
	public ResponseEntity<String> getEmployee(Long telephone,
			String callback, HttpServletResponse response) {
		Employee employee = new Employee();
		return jsonpEntity(employee, callback);
	}

	// TODO 修改
	public ResponseEntity<String> modifyEmployee(Long telephone,
			String callback, HttpServletResponse response) {
		Employee employee = new Employee();
		return jsonpEntity(employee, callback);
	}

	// TODO 删除
	public ResponseEntity<String> delEmployee(Long telephone,
			String callback, HttpServletResponse response) {
		Employee employee = new Employee();

		return jsonpEntity(employee, callback);
	}

}
