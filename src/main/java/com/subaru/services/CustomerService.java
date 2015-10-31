package com.subaru.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.subaru.dao.SubaruDao;
import com.subaru.models.Customer;

@Component
public class CustomerService {
	@Autowired
	SubaruDao subaruDao;

	public Customer getCustomer(String customerTel) {
		Map<String, Object> infos = subaruDao.getCustomerByTel(customerTel);
		if (!infos.isEmpty()) {
			String introducerTypeId = infos.get("introducerTypeId").toString();
			Customer customer = new Customer(Integer.valueOf(infos.get("customerId")
					.toString()), infos.get("name").toString(), infos
					.get("customerTel").toString(), infos.get("occupation").toString(),
					infos.get("identityCard").toString(), infos.get("birthday")
							.toString(), infos.get("email").toString(), infos
							.get("note").toString(),
					StringUtils.hasText(introducerTypeId) ? Integer
							.parseInt(introducerTypeId) : 0, infos.get(
							"introducer").toString(), 0);
			return customer;
		}
		return null;
	}

	public Customer getCustomer(Integer customerId) {
		Map<String, Object> infos = subaruDao.getCustomerById(customerId);
		if (!infos.isEmpty()) {
			String introducerTypeId = infos.get("introducerTypeId").toString();

			Customer customer = new Customer(Integer.valueOf(infos.get("customerId")
					.toString()), infos.get("name").toString(), infos
					.get("customerTel").toString(), infos.get("occupation").toString(),
					infos.get("identityCard").toString(), infos.get("birthday")
							.toString(), infos.get("email").toString(), infos
							.get("note").toString(),
					StringUtils.hasText(introducerTypeId) ? Integer
							.parseInt(introducerTypeId) : 0, infos.get(
							"introducer").toString(), 0);
			return customer;
		}
		return null;
	}

	public Customer modifyCustomer(Integer customerId, String name, String tel,
			String occupation, String identityCard, String birthday,
			String email, String note, Integer introducerTypeId, String introducer) {
		subaruDao.modifyCustomer(customerId, name, tel, occupation, identityCard,
				birthday, email, note, introducerTypeId, introducer);

		return null;
	}

	public Customer createCustomer(String name, String tel, String occupation,
			String identityCard, String birthday, String email, String note,
			Integer introducerType, String introducer) {
		subaruDao.createCustomer(name, tel, occupation, identityCard, birthday,
				email, note, introducerType, introducer);

		return null;
	}

	public void delCustomer(String customerTel) {
		subaruDao.delCustomer(customerTel);
	}

	public void delCustomer(Integer id) {
		subaruDao.delCustomer(id);
	}

}
