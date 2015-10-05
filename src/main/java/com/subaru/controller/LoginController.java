package com.subaru.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloopen.rest.demo.QuerySMSTemplate;
import com.subaru.constants.FIELDS;
import com.subaru.dao.SubaruDao;
import com.subaru.models.Employee;
import com.subaru.models.HomePage;
import com.subaru.services.EmployeeService;
import com.subaru.services.LoginService;
import com.subaru.utils.Common;
import com.subaru.utils.HashHelper;
import com.subaru.utils.LoginHelper;

import static com.subaru.types.functions.map;
import static com.subaru.utils.JsonHelper.jsonpEntity;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/logout.php")
	public ResponseEntity<String> logout(String tel, String passwd,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		QuerySMSTemplate.test();

		Cookie cookie = new Cookie("pauth", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "成功退出"), callback);
	}

	@RequestMapping("/login.php")
	public ResponseEntity<String> login(String employeeTel, String passwd,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {

		if (loginService.login(employeeTel, passwd, response)) {
			Employee employee = employeeService.getEmployee(employeeTel);
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_SUCCESS, "employee", employee,
							FIELDS.MESSAGE, "登陆成功"), callback);
		} else {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_ERROR_PASSWD, FIELDS.MESSAGE, "账号或密码错误"),
					callback);
		}

	}

	// 重置密码
	@RequestMapping("/resetPasswd.php")
	public ResponseEntity<String> resetPasswd(String employeeTel,
			String newPasswd, String callback, HttpServletRequest request,
			HttpServletResponse response) {
		// 如果再输入验证码的5min中内重置，是可以的
		if (loginService.isChangePasswd(employeeTel)) {
			loginService.savePasswd(employeeTel, newPasswd);
			loginService.clearTelCodeMap(employeeTel);

			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "重置密码成功"), callback);
		}
		loginService.clearTelCodeMap(employeeTel);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_RESET_ERROR, FIELDS.MESSAGE, "未通过验证"),
				callback);
	}

	@RequestMapping("/getCode.php")
	public ResponseEntity<String> getCode(String employeeTel, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		Integer code = 1234;
		// TODO 发达验证码，存储验证码

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "验证码发送成功"),
				callback);
	}

	@RequestMapping("/checkCode.php")
	public ResponseEntity<String> getCode(String employeeTel, Integer code,
			String callback, HttpServletRequest request,
			HttpServletResponse response) {
		if (code == 1234) {
			loginService.setCodetime(employeeTel);
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_SUCCESS, FIELDS.MESSAGE, "验证成功"),
					callback);
		}
		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_ERROR, FIELDS.MESSAGE, "验证码错误"), callback);

	}

	@RequestMapping("/home.php")
	public ResponseEntity<String> home(String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (!LoginHelper.isLogin(request)) {
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_NOT_LOGIN, FIELDS.MESSAGE,
							FIELDS.NOT_LOGIN), callback);
		}
		String employeeTel = Common.getLoginTel(request);
		Employee employee = employeeService.getEmployee(employeeTel);
		// TODO List<HomePage> list = getListByTel();
		// TODO 增加店员的信息
		List<HomePage> list = new ArrayList<HomePage>();
		HomePage homePage = new HomePage(1, "审批");
		HomePage homePage2 = new HomePage(2, "报表");
		HomePage homePage3 = new HomePage(3, "邮箱");
		HomePage homePage4 = new HomePage(4, "库存");
		HomePage homePage5 = new HomePage(5, "销售情况");
		HomePage homePage6 = new HomePage(6, "反馈");
		HomePage homePage7 = new HomePage(7, "试驾车");
		list.add(homePage);
		list.add(homePage2);
		list.add(homePage3);
		list.add(homePage4);
		list.add(homePage5);
		list.add(homePage6);
		list.add(homePage7);

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.CODE_SUCCESS, "list", list, "employee",
						employee, "announcement", "这是一条公告"), callback);
	}

	// TODO 修改密码
	@RequestMapping("/modifyPasswd.php")
	public ResponseEntity<String> modifyPasswd(String employeeTel,
			String oldPasswd, String newPasswd, String callback,
			HttpServletRequest request, HttpServletResponse response) {
		if (loginService.login(employeeTel, oldPasswd, response)) {
			loginService.savePasswd(employeeTel, newPasswd);
			loginService.login(employeeTel, newPasswd, response);
			return jsonpEntity(
					map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
							FIELDS.CODE_SUCCESS,FIELDS.MESSAGE, "密码修改成功"), callback);
		}

		return jsonpEntity(
				map(FIELDS.STATUS, FIELDS.SUCCESS, FIELDS.CODE,
						FIELDS.OLD_PASSWD_ERROR, FIELDS.MESSAGE, "旧密码错误"),
				callback);
	}

	// TODO 找回密码
}
