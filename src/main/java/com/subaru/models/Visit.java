package com.subaru.models;

public class Visit {
	int id;
	private Customer customer;
	// 意向车型,存id
	private int intentionVehicleStyleId;
	// 接待人,存id
	private Employee employee;
	// 本次报价
	private Float price;
	// 优惠情况,存具体的
	private Discount discount;
	// 预期优惠
	private Discount expectedDiscount;
	// 备注
	private String note;
	// 最后修改时间
	private String visitTime;

	public Visit(Integer id, Customer customer, int intentionVehicleStyleId,
			Employee employee, Float price, Discount discount,
			Discount expectedDiscount, String note, String time) {
		this.id = id;
		this.customer = customer;
		this.intentionVehicleStyleId = intentionVehicleStyleId;
		this.employee = employee;
		this.price = price;
		this.discount = discount;
		this.expectedDiscount = expectedDiscount;
		this.note = note;
		this.visitTime = time;
	}

	@Override
	public String toString() {
		return "Visit [id=" + id + ", customer=" + customer
				+ ", intentionVehicleStyleId=" + intentionVehicleStyleId
				+ ", employee=" + employee + ", price=" + price + ", discount="
				+ discount + ", expectedDiscount=" + expectedDiscount
				+ ", note=" + note + ", visitTime=" + visitTime + "]";
	}
}
