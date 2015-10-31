package com.subaru.services;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subaru.dao.SubaruDao;
import com.subaru.models.Discount;

@Component
public class OaService {
	@Autowired
	SubaruDao subaruDao;

	public List<Map<String, Object>> getAllStores() {
		return subaruDao.getAllStores();
	}

	public List<Map<String, Object>> getAllPosition() {
		return subaruDao.getAllPosition();
	}

	public List<Map<String, Object>> getAllIntroducerType() {
		return subaruDao.getAllIntroducerType();
	}

	public List<Map<String, Object>> getAllStatus() {
		return subaruDao.selectAll("status");
	}

	public List<Map<String, Object>> getAllInstallation() {
		return subaruDao.selectAll("installation");
	}

	public List<Map<String, Object>> getVehicleList() {
		List<Map<String, Object>> list = subaruDao.selectAll("VehicleStyle");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> l : list) {
			Map<String, Object> info = new HashMap<String, Object>();
			Integer vehicleStyleId = Integer.valueOf(l.get("vehicleStyleId")
					.toString());
			String vehicleStyle = l.get("carSeries").toString() + "-"
					+ l.get("model").toString() + "-"
					+ l.get("color").toString();
			info.put("vehicleStyleId", vehicleStyleId);
			info.put("vehicleStyle", vehicleStyle);
			result.add(info);
		}

		return result;
	}

	public Map<String, Map<String, Map<String, Integer>>> getVehicleMap() {
		Map<String, Map<String, Map<String, Integer>>> infos = new HashMap<String, Map<String, Map<String, Integer>>>();
		List<Map<String, Object>> list = subaruDao.selectAll("VehicleStyle");
		for (Map<String, Object> l : list) {
			Integer vehicleStyleId = Integer.parseInt(l.get("vehicleStyleId")
					.toString());
			String carSeries = l.get("carSeries").toString();
			String model = l.get("model").toString();
			String color = l.get("color").toString();
			Map<String, Map<String, Integer>> model_color_id = infos
					.get(carSeries);
			if (model_color_id == null) {
				model_color_id = new HashMap<String, Map<String, Integer>>();
				infos.put(carSeries, model_color_id);
			}
			Map<String, Integer> color_id = model_color_id.get(model);
			if (color_id == null) {
				color_id = new HashMap<String, Integer>();
				model_color_id.put(model, color_id);
			}
			color_id.put(color, vehicleStyleId);
		}
		System.out.println("infos:" + infos);
		return infos;
	}

	public Map<String, Integer> getVehicle2IdMap() {
		Map<String, Integer> vehicle2IdMap = new HashMap<String, Integer>();
		List<Map<String, Object>> list = subaruDao.selectAll("VehicleStyle");
		for (Map<String, Object> l : list) {
			Integer vehicleStyleId = Integer.parseInt(l.get("vehicleStyleId")
					.toString());
			String str = l.get("carSeries").toString() + "-"
					+ l.get("model").toString() + "-" + l.get("color");
			vehicle2IdMap.put(str, vehicleStyleId);
		}
		return vehicle2IdMap;
	}

	public List<Map<String, String>> getDiscountTypeList() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Entry<Integer, String> discountType : Discount.map.entrySet()) {
			Map<String, String> d = new HashMap<String, String>();
			if (Integer.valueOf(discountType.getKey().toString()).equals(1)) {
				d.put("unit", "元");
			} else {
				d.put("unit", "个");
			}
			d.put("discountId", discountType.getKey().toString());
			d.put("discountValue", discountType.getValue());
			list.add(d);
		}
		return list;
	}

	public List<Map<String, Object>> getPaymentTypeList() {

		return subaruDao.selectAll("paymentType");
	}

	public List<Map<String, Object>> getMortgageBankList() {

		return subaruDao.selectAll("mortgageBank");
	}

}
