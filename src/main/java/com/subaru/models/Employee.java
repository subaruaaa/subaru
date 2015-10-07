package com.subaru.models;

public class Employee {
	public int id;
	// 姓名
	public String name;
	// 电话
	public String tel;
	// 邮箱
	public String email;
	// 身份证
	public String identificationCard;
	// 生日
	public String birthday;
	// 在职情况
	public String status;
	// 地址
	public String add;
	// 职位
	public String position = "position";
	// 所在店面
	public String store = "store";
	// 累计战败次数
	public int totalLose;
	// 当月战败次数
	public int thisMonthLose;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentificationCard() {
		return identificationCard;
	}

	public void setIdentificationCard(String identificationCard) {
		this.identificationCard = identificationCard;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public int getTotalLose() {
		return totalLose;
	}

	public void setTotalLose(int totalLose) {
		this.totalLose = totalLose;
	}

	public int getThisMonthLose() {
		return thisMonthLose;
	}

	public void setThisMonthLose(int thisMonthLose) {
		this.thisMonthLose = thisMonthLose;
	}

	public Employee(Integer id, String name, String tel, String email,
			String identificationCard, String birthday, String status,
			String add, String position, String store) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.identificationCard = identificationCard;
		this.birthday = birthday;
		this.status = status;
		this.add = add;
		this.position = position;
		this.store = store;
	}
}
