package com.subaru.models;

import java.util.Map;

//订单信息（交易完成）
public class Order {
	private Integer orderId;
	private Customer customer;
	private Employee employee;
	// 订单日期
	private String orderDate;
	// 车辆类型
	private Integer vehicleStyleId;
	// 车架号
	private String vehicleIdentificationNumber;
	// 车身价
	private Float price;
	// 开票价格
	private Float invoicePrice;
	// 购买数量
	private Integer purchaseQuantity;
	// 结单人员
	private Integer employeeId;
	// 优惠类型
	Integer discountId;
	// 优惠信息
	String quota;
	// 付款信息
	Integer paymentTypeId;
	// 贷款银行
	Integer mortgageBankId;
	// 贷款金额
	Float mortgageAmount;

	public Order(Integer orderId, Customer customer, String orderDate,
			Integer vehicleStyleId, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Integer purchaseQuantity,
			Employee employee, Integer discountId, String quota,
			Integer paymentTypeId, Integer mortgageBankId, Float mortgageAmount) {
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.vehicleStyleId = vehicleStyleId;
		this.vehicleIdentificationNumber = vehicleIdentificationNumber;
		this.price = price;
		this.invoicePrice = invoicePrice;
		this.purchaseQuantity = purchaseQuantity;
		this.employee = employee;
		this.discountId = discountId;
		this.quota = quota;
		this.paymentTypeId = paymentTypeId;
		this.mortgageBankId = mortgageBankId;
		this.mortgageAmount = mortgageAmount;
	}

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

	public Integer getVehicleStyleId() {
		return vehicleStyleId;
	}

	public void setVehicleStyleId(Integer vehicleStyleId) {
		this.vehicleStyleId = vehicleStyleId;
	}

	public String getVehicleIdentificationNumber() {
		return vehicleIdentificationNumber;
	}

	public void setVehicleIdentificationNumber(
			String vehicleIdentificationNumber) {
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

	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public Integer getMortgageBankId() {
		return mortgageBankId;
	}

	public void setMortgageBankId(Integer mortgageBankId) {
		this.mortgageBankId = mortgageBankId;
	}

	public Float getMortgageAmount() {
		return mortgageAmount;
	}

	public void setMortgageAmount(Float mortgageAmount) {
		this.mortgageAmount = mortgageAmount;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer
				+ ", employee=" + employee + ", orderDate=" + orderDate
				+ ", vehicleStyleId=" + vehicleStyleId
				+ ", vehicleIdentificationNumber="
				+ vehicleIdentificationNumber + ", price=" + price
				+ ", invoicePrice=" + invoicePrice + ", purchaseQuantity="
				+ purchaseQuantity + ", employeeId=" + employeeId
				+ ", discountId=" + discountId + ", quota=" + quota
				+ ", paymentTypeId=" + paymentTypeId + ", mortgageBankId="
				+ mortgageBankId + ", mortgageAmount=" + mortgageAmount + "]";
	}
}
