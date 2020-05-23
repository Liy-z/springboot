package com.staff.mapper;

import java.util.List;

import com.staff.bean.Department;

public interface DepartmentMapper {
	//查询所有部门
	List<Department> selectDepartment();
	
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}