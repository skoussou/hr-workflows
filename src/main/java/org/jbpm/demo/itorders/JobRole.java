package org.jbpm.demo.itorders;

/**
 * This class was automatically generated by the data modeler tool.
 */

public class JobRole implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String title;
	private java.lang.String department;
	private java.lang.String roleId;
	private java.lang.String location;
	private java.lang.Integer level;

	public JobRole() {
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getDepartment() {
		return this.department;
	}

	public void setDepartment(java.lang.String department) {
		this.department = department;
	}

	public java.lang.String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getLocation() {
		return this.location;
	}

	public void setLocation(java.lang.String location) {
		this.location = location;
	}

	public java.lang.Integer getLevel() {
		return this.level;
	}

	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}

	public JobRole(java.lang.String title, java.lang.String department,
			java.lang.String roleId, java.lang.String location,
			java.lang.Integer level) {
		this.title = title;
		this.department = department;
		this.roleId = roleId;
		this.location = location;
		this.level = level;
	}

}