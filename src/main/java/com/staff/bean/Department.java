package com.staff.bean;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class Department implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field("id")
	private Integer id;
	@Field("dpt_department")
    private String department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
}