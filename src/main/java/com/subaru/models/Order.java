package com.subaru.models;

import java.util.Map;

//订单信息（交易完成）
public class Order {
	private Customer customer;
	// 订单日期
	private String orderDate;
	// 车辆类型
	private VehicleStyle vehicleStyle;
	// 车架号
	private String vehicleIdentificationNumber;
	// 车身价
	private Float price;
	// 开票价格
	private Float invoicePrice;
	// 付款方式，０全款，１按揭
	private Payment payment;
	// 优惠情况
	private Discount discount;
	// 购买数量
	private int purchaseQuantity;
	// 结单人员
	private Employee employee;

	public Order(Customer customer, String orderDate,
			VehicleStyle vehicleStyle, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Payment payment,
			Discount discount, int purchaseQuantity, Employee employee) {
		this.customer = customer;
		this.orderDate = orderDate;
		this.vehicleStyle = vehicleStyle;
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
		this.price = price;
		this.invoicePrice = invoicePrice;
		this.payment = payment;
		this.discount = discount;
		this.purchaseQuantity = purchaseQuantity;
		this.employee = employee;
	}
}
