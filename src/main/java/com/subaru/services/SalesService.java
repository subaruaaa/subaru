package com.subaru.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;

@Component
public class SalesService {
	@Autowired
	SubaruDao subaruDao;

	public void getVisitByCustomerTel(String customerTel) {
		List<Map<String, Object>> result = subaruDao
				.getVisitByCustomerTel(customerTel);
		System.out.println("result:" + result);
	}

}
