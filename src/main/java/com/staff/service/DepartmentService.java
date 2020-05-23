package com.staff.service;

import java.util.List;

import com.staff.bean.Department;

public interface DepartmentService {
	//查询所有部门
	List<Department> selectDepartment();
}
