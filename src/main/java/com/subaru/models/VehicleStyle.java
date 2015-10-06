package com.subaru.models;

import java.util.HashMap;
import java.util.Map;

//车型
public class VehicleStyle {

	// TODO 这个要从数据库里加载
	public static Map<Integer, String> id2VehicleMap = new HashMap<Integer, String>() {
		{
			put(1, "森林人-2014款 2.0i 自动舒适版-珠光宝石黑");
		}
	};

	public static Map<String, Integer> vehicle2IdMap = new HashMap<String, Integer>() {
		{
			put("森林人-2014款 2.0i 自动舒适版-珠光宝石黑", 1);
		}
	};

	// 车型id
	public int vehicleStyleId;
	// 车系
	public String carSeries;
	// 车型
	public String model;
	// 车辆颜色
	public String color;

	public VehicleStyle(String verhicle) {
		this.vehicleStyleId = vehicle2IdMap.get(verhicle);
		String[] arrays = verhicle.split("-");
		this.carSeries = arrays[0];
		this.model = arrays[1];
		this.color = arrays[2];
	}

	public VehicleStyle(int vehicleStyleId) {
		this.vehicleStyleId = vehicleStyleId;
		String[] arrays = id2VehicleMap.get(vehicleStyleId).split("-");
		this.carSeries = arrays[0];
		this.model = arrays[1];
		this.color = arrays[2];
	}

	@Override
	public String toString() {
		return "" + this.carSeries + "-" + this.model + "-" + this.color;
	}

}
