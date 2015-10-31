package com.subaru.models;

public class Employee {
	public Integer emplyeeId;
	// 姓名
	public String name;
	// 电话
	public String employeeTel;
	// 邮箱
	public String email;
	// 身份证
	public String identityCard;
	// 生日
	public String birthday;
	// 在职情况
	public Integer statusId;
	// 地址
	public String add;
	// 职位
	public Integer positionId;
	// 所在店面
	public Integer storeId;
	// 累计战败次数
	public int totalLose;
	// 当月战败次数
	public int thisMonthLose;

	public Integer getEmployeeId() {
		return emplyeeId;
	}

	public void setEmployeeId(int emplyeeId) {
		this.emplyeeId = emplyeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeTel() {
		return employeeTel;
	}

	public void setTel(String employeeTel) {
		this.employeeTel = employeeTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public Employee(Integer emplyeeId, String name, String employeeTel,
			String email, String identityCard, String birthday,
			Integer statusId, String add, Integer positionId, Integer storeId) {
		this.emplyeeId = emplyeeId;
		this.name = name;
		this.employeeTel = employeeTel;
		this.email = email;
		this.identityCard = identityCard;
		this.birthday = birthday;
		this.statusId = statusId;
		this.add = add;
		this.positionId = positionId;
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		return "Employee [emplyeeId=" + emplyeeId + ", name=" + name
				+ ", employeeTel=" + employeeTel + ", email=" + email
				+ ", identityCard=" + identityCard + ", birthday=" + birthday
				+ ", statusId=" + statusId + ", add=" + add + ", positionId="
				+ positionId + ", storeId=" + storeId + ", totalLose="
				+ totalLose + ", thisMonthLose=" + thisMonthLose + "]";
	}
}
