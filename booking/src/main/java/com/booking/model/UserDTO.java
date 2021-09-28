package com.booking.model;

import java.io.Serializable;


public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstName ;
	
	private String lastName;
	
	private String address;
	
	private Long mobileNumber ;
	
	private String emailId ;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", mobileNumber="
				+ mobileNumber + ", emailId=" + emailId + "]";
	}
	
	

}
