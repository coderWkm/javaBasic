package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;

import static com.atguigu.team.service.Data.*;
/**
 * 
 * @Description: 将Data类中的数据封装到此类中的Employee数组中
 * @ClassName: NameListService
 * @version:
 * @author: 91578
 * @date: 2022年10月31日 下午8:19:10
 */
public class NameListService {
	private Employee[] employees;

	public NameListService() {
		//1.根据Data类大小构建employee数组
		//2.再根据Data类中的数据构建不同对象
		//3.对象存在数组中
		
		employees = new Employee[Data.EMPLOYEES.length];
		for(int i = 0; i < Data.EMPLOYEES.length;i++){
			
			//先取过来每个数组成员的员工类型,然后new相应的结构。注意类型转换
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			
			//由于继承，有一些共同信息可以提前取出来，不必每个case取一遍
			int id = Integer.parseInt(EMPLOYEES[i][1]);//类型转换
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			
			//为了解决Equipment等变量的重名问题，先声明但不获取，因为有些父类没有这些属性
			Equipment equipment;
			double bonus;
			int stock;
			
			switch(type){
				case EMPLOYEE:
					employees[i] = new Employee(id, name, age, salary);
					
					break;
				case PROGRAMMER://从programmer类开始，有设备接口，每个人的设备不一样
					//找设备的过程和创建employee类型很像，就新造一个方法
					equipment = createEquipment(i);
					employees[i] = new Programmer(id, name, age, salary, equipment);
					break;
				case DESIGNER:
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					equipment = createEquipment(i);
					employees[i] = new Designer(id, name, age, salary, equipment, bonus);
					break;
				case ARCHITECT:
					bonus = Double.parseDouble(EMPLOYEES[i][5]);
					stock = Integer.parseInt(EMPLOYEES[i][6]);
					equipment = createEquipment(i);
					employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
					break;
			}
			
		}
	}
	/**
	 * 
	* @Title: createEquipment
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param  i 上边创建数组中的对象时的索引，此索引和设备数组中的索引的对应的，所以作为形参传递
	* @param @return    设定文件  
	* @return Equipment    返回类型  
	* @throws
	 */
	private Equipment createEquipment(int i) {
		int type = Integer.parseInt(EQIPMENTS[i][0]);
		//PC和NOTEBOOK都有model，Printer有name，可以先取来；
		String modelOrName = EQIPMENTS[i][1];
		switch(type){
			case PC: 
				String display = EQIPMENTS[i][2];
				return new PC(modelOrName, display);
				//break;
			case NOTEBOOK:
				double price = Double.parseDouble(EQIPMENTS[i][2]); 
				return new NoteBook(modelOrName, price);
			case PRINTER:
				String printerType = EQIPMENTS[i][2];
				return new Printer(modelOrName, printerType);
		}
		return null;
		
	}
	/**
	 * 
	* @Title: getAllEmployees  
	* @Description: 获取当前所有员工  
	* @param @return    设定文件  
	* @return Employee[]    返回类型  
	* @throws
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	/**
	 * 
	* @Title: getEmployee  
	* @Description: 获得指定的员工  
	* @author Wkm
	* @return Employee    返回类型  
	* @throws 如果形参对应的员工不存在，抛出自定义异常
	 */
	public Employee getEmployee(int id) throws TeamException{
		for(int i = 0; i < employees.length; i++){
			if(employees[i].getId() == id)
				return employees[i];
		}
		throw new TeamException("找不到指定员工");
	}
}
