package com.subaru.models;

public class Visit {
	//意向车型
	private VehicleStyle intentionVehicleStyle;
	//接待人
	private Employee employee;
	//本次报价
	private Double price;
	//优惠情况
	private Discount discount;
	//预期优惠
	private Discount expectedDiscount;
	//备注
	private String note;
	
	public Visit(){
		intentionVehicleStyle = new VehicleStyle("2014款 2.0i 自动舒适版");
		employee = new Employee();
	}
	
	
	
}
