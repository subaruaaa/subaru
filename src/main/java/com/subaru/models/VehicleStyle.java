package com.subaru.models;

//车型
public class VehicleStyle {
	// 车型id
	public int id;
	// 车系
	public String carSeries;
	// 车型
	public String model;
	// 车辆颜色
	public String color;

	public VehicleStyle(String model) {
		this.id = 3;
		this.carSeries = "森林人";
		this.model = "2014款 2.0i 自动舒适版";
		this.color = "珠光宝石黑";
	}
}
