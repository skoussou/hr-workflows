package org.jbpm.demo.itorders;


public class Applicant implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String firstName;
	private java.lang.String lastName;
	private java.util.Date dob;
	private java.lang.String address;

	public Applicant() {
	}

	public java.lang.String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	public java.lang.String getLastName() {
		return this.lastName;
	}

	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	public java.util.Date getDob() {
		return this.dob;
	}

	public void setDob(java.util.Date dob) {
		this.dob = dob;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public Applicant(java.lang.String firstName, java.lang.String lastName,
			java.util.Date dob, java.lang.String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.address = address;
	}

}