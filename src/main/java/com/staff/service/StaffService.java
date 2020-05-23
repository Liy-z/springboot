package com.staff.service;

import java.util.List;

import com.staff.bean.Staff;

public interface StaffService {
	//查询全部员工信息
	List<Staff> selectStaff();
	//添加员工信息
    boolean insert(Staff record);
    //删除一条员工信息
    boolean deleteByPrimaryKey(Integer id);
    //查询一条员工信息
    Staff selectByPrimaryKeySelective(Integer sid);
    //更新员工信息
    boolean updateByPrimaryKey(Staff record);
}
