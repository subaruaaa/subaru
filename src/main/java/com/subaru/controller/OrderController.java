package com.subaru.controller;

import static com.subaru.utils.JsonHelper.jsonpEntity;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.subaru.models.Order;

@Controller
public class OrderController {
	@RequestMapping("/getOrder.php")
	public ResponseEntity<String> getCustomer(Long telephone, String callback,
			HttpServletResponse response) {
		Order order = new Order("18695690001");

		return jsonpEntity(order, callback);
	}

}
