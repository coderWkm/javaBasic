package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class Programmer extends Employee {
	private int memberId; // 开发团队中的id
	private Status status = Status.FREE;
	private Equipment equipment;

	public Programmer() {
		super();
	}

	public Programmer(int id, String name, int age, double salary, Equipment equipment) {
		super(id, name, age, salary);
		this.equipment = equipment;
	}
	

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
	@Override
	//视频写法：调用父类的toString + 子类的独特属性,但是会出现问题。解决方法：父类定义getString
	public String toString() {
		//return toString() + "\t程序员\t" + status + "\t\t\t" + equipment.getDescription();
		return getDetails() + "\t程序员\t" + status + "\t\t\t" + equipment.getDescription(); 
	}
	
	protected String getMemberDetails() {
        return getMemberId() + "/" + getDetails();
    }

    public String getDetailsForTeam() {
        return getMemberDetails() + "\t程序员";
    }
}
