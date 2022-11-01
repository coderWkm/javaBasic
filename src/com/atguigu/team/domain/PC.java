package com.atguigu.team.domain;

public class PC implements Equipment {
	private String model;
	private String display;

	public PC() {
		super();
	}

	public PC(String model, String display) {
		super();
		this.display = display;
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	// 实现接口中的抽象方法
	public String getDescription() {

		return this.model + "(" + this.display + ")";
	}
}
