package com.staff.bean;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class Staff implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field("id")
	private Integer id;
	@Field("staff_sname")
    private String sname;
	@Field("staff_department")
    private String department;
	@Field("staff_phot")
    private String phot;
	@Field("staff_address")
    private String address;
	
    private Department dtt;

    public Department getDtt() {
		return dtt;
	}

	public void setDtt(Department dtt) {
		this.dtt = dtt;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhot() {
        return phot;
    }

    public void setPhot(String phot) {
        this.phot = phot == null ? null : phot.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}