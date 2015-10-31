package com.subaru.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.net.TelnetAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;
import com.subaru.models.Customer;
import com.subaru.models.Employee;
import com.subaru.types.Tuple3;

@Component
public class SearchService {

	@Autowired
	SubaruDao subaruDao;
	@Autowired
	CustomerService customerService;

	public List<Map<String, Object>> searchCustomerByCustomerTel(String customerTel) {
		return subaruDao.searchCustomerByCustomerTelList(customerTel);
	}

	public List<Map<String, Object>> customerVisits(Integer customerId) {
		List<Map<String, Object>> visits = subaruDao.getVisitByCustomerId(customerId);
		return visits;
	}

	public List<Map<String, Object>> customerOrders(Integer customerId) {
		List<Map<String, Object>> orders = subaruDao.getOrdersByCustomerId(customerId);
		return orders;
	}

	public List<Map<String, Object>> searchVisitCustomerByCustomerTel(String customerTel) {
		Map<Integer, Tuple3<Integer, Integer, String>> infos = new HashMap<Integer, Tuple3<Integer, Integer, String>>();

		List<Map<String, Object>> visits = searchCustomerByCustomerTel(customerTel);
		Set<Integer> visitsCustomerIds = new HashSet<Integer>();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> visit : visits) {
			Integer customerId = Integer.valueOf(visit.get("customerId").toString());
			visitsCustomerIds.add(customerId);
		}

		for (Integer customerId : visitsCustomerIds) {
			List<Map<String, Object>> vv = customerVisits(customerId);
			if (vv.size() == 0) {
				infos.put(customerId, new Tuple3<Integer, Integer, String>(0, 0, "0"));
			} else {
				for (Map<String, Object> v : vv) {
					String visitTime = v.get("visitTime").toString();

					Tuple3<Integer, Integer, String> t3 = infos.get(customerId);
					if (t3 == null) {
						t3 = new Tuple3<Integer, Integer, String>(0, 0, visitTime);
						infos.put(customerId, t3);
					}
					t3._1 += 1;
					if (visitTime.compareTo(t3._3) > 0) {
						t3._3 = visitTime;
					}
				}
			}
		}

		Set<Integer> orderCustomerIds = visitsCustomerIds;
		for (Integer customerId : orderCustomerIds) {
			List<Map<String, Object>> oo = customerOrders(customerId);
			if (oo.size() == 0) {
				infos.put(customerId, new Tuple3<Integer, Integer, String>(0, 0, "0"));
			} else {
				for (Map<String, Object> o : oo) {
					String orderDate = o.get("orderDate").toString();

					Tuple3<Integer, Integer, String> t3 = infos.get(customerId);
					if (t3 == null) {
						t3 = new Tuple3<Integer, Integer, String>(0, 0, orderDate);
						infos.put(customerId, t3);
					}
					t3._1 += 1;
					t3._2 = 1;
					if (orderDate.compareTo(t3._3) > 0) {
						t3._3 = orderDate;
					}
				}
			}
		}

		for (Entry<Integer, Tuple3<Integer, Integer, String>> entry : infos.entrySet()) {
			Map<String, Object> info = new HashMap<String, Object>();
			Customer customer = customerService.getCustomer(entry.getKey());
			if (customer == null) {
				continue;
			}
			int visitCount = entry.getValue()._1;
			int isDone = entry.getValue()._2;
			String visitTime = entry.getValue()._3;

			info.put("customer", customer);
			info.put("visitCount", visitCount);
			info.put("isDone", isDone);
			info.put("visitTime", visitTime);
			list.add(info);
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> b1, Map<String, Object> b2) {
				return b1.get("visitTime").toString().compareTo(b2.get("visitTime").toString());
			}

		});

		return list;
	}

	public List<Map<String, Object>> searchVisitCustomerByEmployee(Employee employee) {
		Map<Integer, Tuple3<Integer, Integer, String>> infos = new HashMap<Integer, Tuple3<Integer, Integer, String>>();

		List<Map<String, Object>> visits = subaruDao.getVisitByEmployeeId(employee.getEmployeeId());
		Set<Integer> visitsCustomerIds = new HashSet<Integer>();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> visit : visits) {
			Integer customerId = Integer.valueOf(visit.get("customerId").toString());
			visitsCustomerIds.add(customerId);
		}

		for (Integer customerId : visitsCustomerIds) {
			List<Map<String, Object>> vv = customerVisits(customerId);
			for (Map<String, Object> v : vv) {
				String visitTime = v.get("visitTime").toString();

				Tuple3<Integer, Integer, String> t3 = infos.get(customerId);
				if (t3 == null) {
					t3 = new Tuple3<Integer, Integer, String>(0, 0, visitTime);
					infos.put(customerId, t3);
				}
				t3._1 += 1;
				if (visitTime.compareTo(t3._3) > 0) {
					t3._3 = visitTime;
				}

			}
		}

		Set<Integer> orderCustomerIds = new HashSet<Integer>();
		List<Map<String, Object>> orders = subaruDao.getOrdersByEmployee(employee.getEmployeeId());
		for (Map<String, Object> order : orders) {
			Integer customerId = Integer.valueOf(order.get("customerId").toString());
			orderCustomerIds.add(customerId);
		}

		for (Integer customerId : orderCustomerIds) {
			List<Map<String, Object>> oo = customerOrders(customerId);
			for (Map<String, Object> o : oo) {
				String orderDate = o.get("orderDate").toString();

				Tuple3<Integer, Integer, String> t3 = infos.get(customerId);
				if (t3 == null) {
					t3 = new Tuple3<Integer, Integer, String>(0, 0, orderDate);
					infos.put(customerId, t3);
				}
				t3._1 += 1;
				t3._2 = 1;
				if (orderDate.compareTo(t3._3) > 0) {
					t3._3 = orderDate;
				}

			}
		}

		for (Entry<Integer, Tuple3<Integer, Integer, String>> entry : infos.entrySet()) {
			Map<String, Object> info = new HashMap<String, Object>();
			Customer customer = customerService.getCustomer(entry.getKey());
			if (customer == null) {
				continue;
			}
			int visitCount = entry.getValue()._1;
			int isDone = entry.getValue()._2;
			String visitTime = entry.getValue()._3;

			info.put("customer", customer);
			info.put("visitCount", visitCount);
			info.put("isDone", isDone);
			info.put("visitTime", visitTime);
			list.add(info);
		}

		Collections.sort(list, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> b1, Map<String, Object> b2) {
				return b1.get("visitTime").toString().compareTo(b2.get("visitTime").toString());
			}

		});

		return list;
	}
}
