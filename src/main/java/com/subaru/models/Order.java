package com.subaru.models;

import java.util.Map;

//订单信息（交易完成）
public class Order {
	private Integer orderId;
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
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public VehicleStyle getVehicleStyle() {
		return vehicleStyle;
	}

	public void setVehicleStyle(VehicleStyle vehicleStyle) {
		this.vehicleStyle = vehicleStyle;
	}

	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}



	public Float getInvoicePrice() {
		return invoicePrice;
	}



	public void setInvoicePrice(Float invoicePrice) {
		this.invoicePrice = invoicePrice;
	}



	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Order(Integer id, Customer customer, String orderDate,
			VehicleStyle vehicleStyle, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Payment payment,
			Discount discount, int purchaseQuantity, Employee employee) {
		this.orderId = id;
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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer
				+ ", orderDate=" + orderDate + ", vehicleStyle=" + vehicleStyle
				+ ", vehicleIdentificationNumber="
				+ vehicleIdentificationNumber + ", price=" + price
				+ ", invoicePrice=" + invoicePrice + ", payment=" + payment
				+ ", discount=" + discount + ", purchaseQuantity="
				+ purchaseQuantity + ", employee=" + employee + "]";
	}
}
