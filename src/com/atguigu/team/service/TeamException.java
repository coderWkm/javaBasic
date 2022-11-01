package com.atguigu.team.service;

/**
 * 
 * @Description: 自定义异常类，用于寻找某个特定员工时，输入的员工号码下无真实员工
 * @ClassName: TeamException
 * @version:
 * @author: 91578
 * @date: 2022年10月31日 下午10:09:37
 */
public class TeamException extends Exception {
	static final long serialVersionUID = -33875164229948L;

	public TeamException() {
		super();
	}

	public TeamException(String msg) {
		super(msg);
	}
}
