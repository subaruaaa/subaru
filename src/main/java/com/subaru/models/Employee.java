package com.subaru.models;

public class Employee {
	// 姓名
	private String name;
	// 电话
	private String tel;
	// 邮箱
	private String email;
	// 身份证
	private String identificationCard;
	// 生日
	private String birthday;
	// 在职情况
	private String status;
	// 地址
	private String add;
	// 职位
	private String position = "position";
	// 所在店面
	private String store = "store";
	// 累计战败次数
	private int totalLose;
	// 当月战败次数
	private int thisMonthLose;

	public Employee(String name, String tel, String email,
			String identificationCard, String birthday, String status,
			String add, String position, String store) {
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

	public Employee() {
		this.name = "赖晓航";
		this.tel = "18695690001";
		this.email = "laixiaohang@sina.com";
		this.identificationCard = "35020419900427201X";
		this.birthday = "1990-04-27";
		this.status = "在职";
		this.add = "北京-西城区";
		this.position = "金牌销售";
		this.store = "大润发店";
		this.totalLose = 23;
		this.thisMonthLose = 10;
	}

}