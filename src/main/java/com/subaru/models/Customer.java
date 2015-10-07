package com.subaru.models;

//客户基本信息，静态的，不会每次受到修改
public class Customer {
	private int id;
	private String name;
	private String tel;
	// 职业
	private String occupation;
	// 身份证
	private String identityCard;
	private String birthday;
	private String email;
	// 介绍人类型
	private int introducerType;
	// 介绍人名字
	private String introducer;
	private Boolean blacked;
	private String note;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public int getIntroducerType() {
		return introducerType;
	}

	public void setIntroducerType(int introducerType) {
		this.introducerType = introducerType;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public Boolean getBlacked() {
		return blacked;
	}

	public void setBlacked(Boolean blacked) {
		this.blacked = blacked;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Customer(Integer id, String name, String tel, String occupation,
			String identityCard, String birthday, String email, String note,
			int introducerType, String introducer, Boolean blacked) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.occupation = occupation;
		this.identityCard = identityCard;
		this.birthday = birthday;
		this.email = email;
		this.introducerType = introducerType;
		this.introducer = introducer;
		this.blacked = blacked;
		this.note = note;
	}

	// TODO 通过tel来查询顾客信息
	public Customer(String tel) {
		this.name = "王大锤";
		this.tel = "5201314";
		this.occupation = "非著名演员";
		this.identityCard = "35020419900428220X";
		this.birthday = "1990-04-28";
		this.email = "wangdachui@subaru.com";
		this.introducerType = 1;
		this.introducer = "习大大";
		blacked = false;
		this.note = "非常抠门";
	}

	public boolean equals(Object other) { // 重写equals方法，后面最好重写hashCode方法

		if (this == other) // 先检查是否其自反性，后比较other是否为空。这样效率高
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Customer))
			return false;

		final Customer customer = (Customer) other;

		if (!this.tel.equals(customer.tel)) {
			return false;
		}

		return true;
	}

	public int hashCode() { // hashCode主要是用来提高hash系统的查询效率。当hashCode中不进行任何操作时，可以直接让其返回
							// 一常数，或者不进行重写。
		int result = tel.hashCode();
		return result;
	}
}
