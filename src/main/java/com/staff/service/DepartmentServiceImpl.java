package com.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.bean.Department;
import com.staff.mapper.DepartmentMapper;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentMapper dm;
	
	public void setDm(DepartmentMapper dm) {
		this.dm = dm;
	}


	@Override
	public List<Department> selectDepartment() {
		// TODO Auto-generated method stub
		List<Department> list = dm.selectDepartment();
		
 		return list;
	}

}
