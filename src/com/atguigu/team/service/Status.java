package com.atguigu.team.service;

/**
 * 
 * @ClassName: Status
 * @Description: 表示员工的工作状态：Busy、VOCATION、
 * @author: 91578
 * @date: 2022年10月31日 下午7:42:27
 */
public class Status {
	private final String NAME;

	public Status(String name) {
		super();
		NAME = name;
	}

	public static final Status FREE = new Status("FREE");
	public static final Status BUSY = new Status("BUSY");
	public static final Status VOCATION = new Status("VOCATION");

	public String getNAME() {
		return NAME;
	}
	
	@Override
	public String toString() {
		return NAME;
	}

}
