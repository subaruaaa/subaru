package com.subaru.models;

public class HomePage {
	private int id;
	private String name;
	
	public HomePage(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	public HomePage (int id){
		this.id = id;
		//TODO 通过id去获取name
	}
}
