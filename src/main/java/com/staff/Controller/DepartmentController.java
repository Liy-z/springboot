package com.staff.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.staff.bean.Department;
import com.staff.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService ds;

	public void setDs(DepartmentService ds) {
		this.ds = ds;
	}
	
	@RequestMapping("/selectDepartment.win")
	public String selectDepartment(Model model){
		List<Department> list = ds.selectDepartment();
		model.addAttribute("list",list);
		return "add";
	}
	@RequestMapping("/selectUpdateDepartment.win")
	public String selectDepartment(Model model,Integer a){
		List<Department> list = ds.selectDepartment();
		model.addAttribute("list",list);
		return "update";
	}

}
