package com.atguigu.team.domain;

public class Employee {
	private int id;
	private String name;
	private int age;
	private double salary;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee() {
		super();
	}

	public Employee(int id, String name, int age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	public String getDetails(){
		return id + "\t" + name + "\t" + age + "\t" + salary;
	}
	/**
	 * 如果Employee类的toString方法直接像注释那样写
	 * 当子类，比如Designer想要显示自己的职业时，如果也使用toString，就显示的是直接父类Programmer类的职业
	 * （可以看后面的注释代码）
	 * 这有利于对继承的理解
	 * 这种情况：每个子类的toString想要实现的程度不一样（职业不一样），调用直接父类的方法会出现这种问题 
	 * 应对情况：在最顶层的父类（Employee）定义一个子父类通用的，在各自子类中调用这个通用的方法，就不影响
	 * 
	 */
	@Override
	public String toString() {
		//return id + "\t" + name + "\t" + age + "\t" + salary;
		return getDetails();
	}
}
