package com.atguigu.team.junit;

import org.junit.Test;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;

/**
 * 
 * @Description: 测试NameListService
 * @ClassName: NameListServiceTest 
 * @version:
 * @author: 91578
 * @date: 2022年10月31日 下午10:17:35
 */
public class NameListServiceTest {
	
	@Test
	public void testGetAllEmployees(){
		NameListService service = new NameListService();
		Employee[] employees = service.getAllEmployees();
		for(int i = 0; i < employees.length; i++){
			System.out.println(employees[i]); //自动调用Employee的toString
		}
		
	}
	
	@Test
	public void testGetEmployee(){
		NameListService service = new NameListService();
		int id = 10;
		
		try {
			Employee employee = service.getEmployee(id);
			System.out.println(employee);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
}
