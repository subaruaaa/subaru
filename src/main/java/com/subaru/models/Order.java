package com.subaru.models;

import java.awt.List;

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
	private int payType;
	// 按揭银行
	private String mortgageBank;
	// 按揭金额
	private Float mortgageAmount;
	// 优惠情况
	private Discount discount;
	// 购买数量
	private int purchaseQuantity;
	// 结单人员
	private Employee employee;

	public Order(String customerTel, String employeeTel) {
		this.customer = new Customer(customerTel);
		orderDate = "2015-09-01";
		// TODO 或者使用id传入
		this.vehicleStyle = new VehicleStyle("2014款 2.0i 自动舒适版");
		price = 25.3f;
		invoicePrice = 23.3f;
		this.vehicleIdentificationNumber = "JF1SG52N07H113703";
		payType = 1;
		mortgageBank = "建设银行松柏支行";
		mortgageAmount = 15.3f;
		this.discount = new Discount(1, 0.5f);
		this.purchaseQuantity = 1;
		this.employee = new Employee(employeeTel);

	}
}
