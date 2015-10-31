package com.subaru.models;

public class Visit {
	int visitId;
	private Customer customer;
	// 意向车型,存id
	private int intentionVehicleStyleId;
	// 接待人,存id
	private Employee employee;
	// 本次报价
	private Float price;
	// 优惠情况,存具体的
	private String discountId;
	private String quota;
	// 预期优惠
	private String expectedDisCountId;
	private String expectedQuota;
	// 备注
	private String note;
	// 最后修改时间
	private String visitTime;
	private Integer installationId;

	public Visit(Integer visitId, Customer customer,
			int intentionVehicleStyleId, Employee employee, Float price,
			String discountId, String quota, String expectedDisCountId,
			String expectedQuota, String note, String time,
			Integer installationId) {
		this.visitId = visitId;
		this.customer = customer;
		this.intentionVehicleStyleId = intentionVehicleStyleId;
		this.employee = employee;
		this.price = price;
		this.discountId = discountId;
		this.quota = quota;
		this.expectedDisCountId = expectedDisCountId;
		this.expectedQuota = expectedQuota;
		this.note = note;
		this.visitTime = time;
		this.installationId = installationId;
	}

	@Override
	public String toString() {
		return "Visit [visitId=" + visitId + ", customer=" + customer
				+ ", intentionVehicleStyleId=" + intentionVehicleStyleId
				+ ", employee=" + employee + ", price=" + price
				+ ", discountId=" + discountId + ", quota=" + quota
				+ ", expectedDisCountId=" + expectedDisCountId
				+ ", expectedQuota=" + expectedQuota + ", note=" + note
				+ ", visitTime=" + visitTime + ", installationId="
				+ installationId + "]";
	}

}
