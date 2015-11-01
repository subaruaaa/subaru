package com.subaru.controller;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subaru.constants.FIELDS;
import com.subaru.models.Employee;
import com.subaru.services.CommonMessage;
import com.subaru.services.EmployeeService;
import com.subaru.services.LoginService;
import com.subaru.utils.Common;
import com.subaru.utils.LoginHelper;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@Autowired
	LoginService loginService;

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

	// 修改电话要修改密码
	@RequestMapping("/modifyEmployee.php")
	public ResponseEntity<String> modifyEmployee(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "identityCard", defaultValue = "") String identityCard,
			@RequestParam(value = "birthday", required = true, defaultValue = "") String birthday,
			@RequestParam(value = "employeeTel", required = true) String employeeTel,
			@RequestParam(value = "statusId", required = true, defaultValue = "0") Integer statusId,
			@RequestParam(value = "add", required = true, defaultValue = "") String add,
			@RequestParam(value = "positionId", required = true, defaultValue = "0") Integer positionId,
			@RequestParam(value = "storeId", required = true, defaultValue = "0") Integer storeId, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}

		if (!StringUtils.hasText(name)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NAME_EMPTY, FIELDS.MESSAGE,
							FIELDS.NAME_EMPTY), callback);
		}

		if (!StringUtils.hasText(employeeTel)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_TEL_EMPTY, FIELDS.MESSAGE,
							FIELDS.TEL_EMPTY), callback);
		}
		if (StringUtils.hasText(birthday)
				&& !Common.isDate("yyyy-MM-dd", birthday)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_DATE, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_DATE), callback);
		}

		if (!Common.isMobile(employeeTel)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_DATE, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_TEL), callback);
		}

		if (StringUtils.hasText(email) && !Common.isEmail(email)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ILLEGAL_EMAIL, FIELDS.MESSAGE,
							FIELDS.ILLEGAL_EMAIL), callback);
		}

		// 当前用户
		Employee employeeOld = employeeService.getEmployee(Common
				.getLoginTel(request));

		// 要修改的电话的用户
		Employee employeeTelExist = employeeService.getEmployee(employeeTel);

		// 如果对象不是同一个，那么电话会冲突，返回报错
		if (employeeTelExist != null
				&& (employeeTelExist.getEmployeeId() != employeeOld
						.getEmployeeId())) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_EMPLOYEE_EXIST, FIELDS.MESSAGE,
							FIELDS.EMPLOYEE_EXIST), callback);
		}

		// 之行到这里说明已经可以修改了
		Employee employee = new Employee(employeeOld.getEmployeeId(), name,
				employeeTel, email, identityCard, birthday, statusId,
				add, positionId, storeId);

		employeeService.saveEmployee(employee);

		employee = employeeService.getEmployee(employee.getEmployeeId());
		if (employeeOld != null
				&& !employeeOld.getEmployeeTel().equals(employeeTel)) {
			loginService.updatePasswdTel(employeeOld.getEmployeeTel(),
					employeeTel);
			Cookie cookie = new Cookie("pauth", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_SUCCESS, "employee", employee,
							FIELDS.MESSAGE, "请重新登陆"), callback);
		}

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "employee", employee), callback);
	}

	// TODO 删除
	@RequestMapping("/delEmployee.php")
	public ResponseEntity<String> delEmployee(
			@RequestParam(value = "employeeTel", required = true) String employeeTel,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
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
