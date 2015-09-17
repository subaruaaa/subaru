package com.subaru.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;
import com.subaru.models.Customer;

@Component
public class CustomerService {
	@Autowired
	SubaruDao subaruDao;

	public Customer getCustomer(String customerTel) {
		Map<String, Object> infos = subaruDao.getCustomerByTel(customerTel);
		if (!infos.isEmpty()) {
			Customer customer = new Customer(infos.get("name").toString(),
					infos.get("tel").toString(), infos.get("occupation")
							.toString(), infos.get("identityCard").toString(),
					infos.get("birthday").toString(), infos.get("email")
							.toString(), infos.get("note").toString(),
					Integer.parseInt(infos.get("introducerType").toString()),
					infos.get("introducer").toString(), false);
			return customer;
		}
		return null;
	}

	public Customer saveCustomer(String name, String tel, String occupation,
			String identityCard, String birthday, String email, String note,
			String introducerType, String introducer) {
		subaruDao.saveCustomer(name, tel, occupation, identityCard, birthday, email, note, introducerType, introducer);

		return null;
	}
	
	public void delCustomer(String customerTel){
		subaruDao.delCustomer(customerTel);
	}

}
