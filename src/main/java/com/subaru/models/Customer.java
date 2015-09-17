package com.subaru.models;

//客户基本信息，静态的，不会每次受到修改
public class Customer {
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

	public Customer(String name, String tel, String occupation,
			String identityCard, String birthday, String email, String note, int introducerType, String introducer, Boolean blacked) {
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
}
