package com.subaru.models;

//客户基本信息，静态的，不会每次受到修改
public class Customer {
	private int customerId;
	private String name;
	private String customerTel;
	// 职业
	private String occupation;
	// 身份证
	private String identityCard;
	private String birthday;
	private String email;
	// 介绍人类型
	private int introducerTypeId;
	// 介绍人名字
	private String introducer;
	private Integer blacked;
	private String note;
	
	public int getCusomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return customerTel;
	}

	public void setTel(String tel) {
		this.customerTel = tel;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIntroducerTypeId() {
		return introducerTypeId;
	}

	public void setIntroducerTypeId(int getIntroducerTypeId) {
		this.introducerTypeId = getIntroducerTypeId;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Customer(Integer customerId, String name, String customerTel, String occupation,
			String identityCard, String birthday, String email, String note,
			int getIntroducerTypeId, String introducer, Integer blacked) {
		this.customerId = customerId;
		this.name = name;
		this.customerTel = customerTel;
		this.occupation = occupation;
		this.identityCard = identityCard;
		this.birthday = birthday;
		this.email = email;
		this.introducerTypeId = getIntroducerTypeId;
		this.introducer = introducer;
		this.blacked = blacked;
		this.note = note;
	}

	public boolean equals(Object other) { // 重写equals方法，后面最好重写hashCode方法

		if (this == other) // 先检查是否其自反性，后比较other是否为空。这样效率高
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Customer))
			return false;

		final Customer customer = (Customer) other;

		if (!this.customerTel.equals(customer.customerTel)) {
			return false;
		}

		return true;
	}

	public int hashCode() { // hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回
							// 一常数，或者不进行重写。
		int result = customerTel.hashCode();
		return result;
	}
}
