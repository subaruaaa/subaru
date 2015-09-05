package com.subaru.controller;

import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.models.Customer;

@Controller
public class CustomerController {
	@RequestMapping("/getCustomer.php")
	public ResponseEntity<String> getCustomer(Long telephone, String callback,
			HttpServletResponse response) {
		Customer customer = new Customer("5201314");

		return jsonpEntity(customer, callback);
	}
	
	@RequestMapping("/modifyCustomer.php")
	public ResponseEntity<String> modifyCustomer(Long telephone, String callback,
			HttpServletResponse response) {
		Customer customer = new Customer("5201314");

		return jsonpEntity(customer, callback);
	}
	
	@RequestMapping("/delCustomer.php")
	public ResponseEntity<String> delCustomer(Long telephone, String callback,
			HttpServletResponse response) {
		Customer customer = new Customer("5201314");

		return jsonpEntity(customer, callback);
	}
	
	//TODO
	@RequestMapping("/visit.php")
	public ResponseEntity<String> getVisit(Long telephone, String callback,
			HttpServletResponse response) {

		return jsonpEntity("", callback);
	}

	// 进店信息
}
