package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.Status;
/**
 * 
 * @Description: 关于开发团队成员的管理：添加、删除
 * @ClassName: TeamService 
 * @version:
 * @author: 91578
 * @date: 2022年10月31日 下午11:28:34
 */
public class TeamService {
	private static int counter = 1;//用于给memberID赋值
	private final int MAX_MEMBER = 5;//限制团队的最大人数
	private Programmer[] team = new Programmer[MAX_MEMBER];//存储每个团队成员的数组
	private int total;//团队实际的成员数
	
	/**
	 * 
	* @Title: getTeam  
	* @Description: 不要像注释一样直接return 类中的team
	* 				因为total<5时直接返回，出现空指针异常  
	* @author Wkm
	* @return Programmer[]    返回类型  
	* @throws
	 */
	public Programmer[] getTeam(){
		//return team;
		Programmer[] team = new Programmer[total];
		for(int i = 0; i < this.total; i++){
			team[i] = this.team[i];
		}
		return team;
	}
	
	/*将指定的员工添加到团队中
	 * 难点在于什么情况下是不能添加的？
	 * 
	 */
	public void addMember(Employee e) throws TeamException{
		//情况1：团队已满
		if(this.total == 5){
			throw new TeamException("团队成员已达到上限5");
		}	
		
		//2：添加的成员不是开发者
		if(!(e instanceof Programmer)){
			throw new TeamException("该成员不是开发者，无法添加");
		}
		
		//3.添加的成员已经在本团队中（判断是否在本团队中，用方法isExist）
		if(isExist(e)){
			throw new TeamException("该成员已在当前开发团队中");
		}
		
		/**4.员工已经是某个团队的成员
		 * 通过busy属性查看，问题是怎么拿？
		 * 通过验证条件2的情况必是Programmer
		 * 所以此时可以向下转型，不会出现ClassCastException
		 */
		Programmer programmer = (Programmer)e;
		if("BUSY".equalsIgnoreCase(programmer.getStatus().getNAME())){
			//此种方法更好，通过常量“BUSY”调用equals会避免空指针
		//if(programmer.getStatus() == Status.BUSY){
			//这块的判断条件和视频不一样,应该是错的，此时比较的是两个status的地址，明显不一样
			throw new TeamException("该成员已在其他开发团队中");
		}
		
		//5.团队成员在度假。和4差不多
		if("VOCATION".equalsIgnoreCase(programmer.getStatus().getNAME())){
			throw new TeamException("该成员在度假");
		}
		
		//6.团队至多1名架构师
		//7.团队至多2名设计师
		//8.最多三个程序员
		/*
		 * 思路：先获取团队中已有的架构师、设计师、程序员人数
		 * 		再看传入的programmer（e向下转型后的）是三种中的哪种人
		 * */
		
		int numOfArch = 0, numOfDes = 0, numOfPro = 0;
		for(int i = 0; i < total; i++){
			if(team[i] instanceof Architect){
				numOfArch++;
			}
			else if(team[i] instanceof Designer){
				numOfDes++;
			}
			else
				numOfPro++;
		}
		
		//不能写成下面&&的情况
		/**if(programmer instanceof Architect && numOfArch == 1){
			throw new TeamException("团队中最多1名架构师");
		}
		else if(programmer instanceof Designer && numOfDes == 2){
			throw new TeamException("团队中最多2名设计师");
		}
		else if(numOfPro == 3){
			throw new TeamException("团队中最多3名程序员");
		}**/
		
		if(programmer instanceof Architect){
			if(numOfArch == 1){
				throw new TeamException("团队中最多1名架构师");
			}
		}
		else if(programmer instanceof Designer){
			if(numOfDes == 2){
				throw new TeamException("团队中最多3名程序员");
			}
		}
		else if(programmer instanceof Programmer){
			if(numOfPro == 3){
				throw new TeamException("团队中最多2名设计师");
			}
		}
		
		team[total++] = programmer;
		programmer.setMemberId(counter++);
		programmer.setStatus(Status.BUSY);
		
	}
	
	//判断成员e是否在当前开发团队中
	private boolean isExist(Employee e) {
		for(int i = 0; i < total; i++){
			if(e.getId() == team[i].getId()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @throws TeamException 
	 * 
	* @Title: removeMember  
	* @Description: 删除要做什么事？
	* 				1.删除该成员，后面覆盖前边的
	* 				2.还要把删除的成员状态置成free，至于memberid可以不改  
	* 				3.修改total的值,并且team数组的total - 1位置置空
	* @author Wkm
	* @return void    返回类型  
	* @throws
	 */
	public void removeMember(int memberID) throws TeamException{
		int i = 0;//第一步访问team的索引
		for(;i < total;i++){
			if(memberID == team[i].getMemberId()){
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		//如果形参非法
		if(total == i){
			throw new TeamException("找不到指定memberID员工，删除失败");
		}
		
		//不非法，后面元素向前挪动
		for(int j = i;j < total - 1;j++){
			team[j] = team[j + 1];
		}
		
		team[--total] = null;
		
		
	}
}
