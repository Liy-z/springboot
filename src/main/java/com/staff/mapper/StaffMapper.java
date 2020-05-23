package com.staff.mapper;

import java.util.List;

import com.staff.bean.Staff;

public interface StaffMapper {
	//查询全部员工信息
	List<Staff> selectStaff();
	//删除一条员工信息
    int deleteByPrimaryKey(Integer id);
    //添加员工信息
    int insert(Staff record);
    //查询一条员工信息
    Staff selectByPrimaryKeySelective(Integer sid);
    //更新员工信息
    int updateByPrimaryKey(Staff record);
}