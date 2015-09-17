package com.subaru.models;

public class Visit {
	int id;
	// 意向车型,存id
	private VehicleStyle intentionVehicleStyle;
	// 接待人,存id
	private Employee employee;
	// 本次报价
	private Double price;
	// 优惠情况,存具体的
	private Discount discount;
	// 预期优惠
	private Discount expectedDiscount;
	// 备注
	private String note;
	//最后修改时间
	private String time;

	public Visit(Integer id, VehicleStyle intentionVehicleStyle, Employee employee,
			Double price, Discount discount, Discount expectedDiscount,
			String note, String time) {
		this.id = id;
		this.intentionVehicleStyle = intentionVehicleStyle;
		this.employee = employee;
		this.price = price;
		this.discount = discount;
		this.expectedDiscount = expectedDiscount;
		this.note = note;
		this.time = time;
	}

	public Visit(String employeeTel) {
		this.intentionVehicleStyle = new VehicleStyle("2014款 2.0i 自动舒适版");
		this.employee = new Employee("18695690001");
		this.price = 23.0d;
		this.discount = new Discount(1, 0.5f);
		this.expectedDiscount = new Discount(1, 0.7f);
	}
}
