package com.subaru.controller;

import static com.subaru.utils.JsonHelper.jsonpEntity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.models.Customer;

@Controller
public class SearchController {
	
	@RequestMapping("/searchVisitor.php")
	public ResponseEntity<String> searchVisitor(Long telephone, String callback,
			HttpServletResponse response) {
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer("5201314");
		Customer customer2 = new Customer("5201314");
		Customer customer3 = new Customer("5201314");
		Customer customer4 = new Customer("5201314");
		Customer customer5 = new Customer("5201314");
		customers.add(customer);
		customers.add(customer2);
		customers.add(customer3);
		customers.add(customer4);
		customers.add(customer5);
		
		return jsonpEntity(customers, callback);
	}
}
