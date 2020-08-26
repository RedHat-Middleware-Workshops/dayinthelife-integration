package com.redhat.model;

public class ContactInfo {
	
	int id;
	String phone;
	String  owner;
	String  operating_hour;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOperating_hour() {
		return operating_hour;
	}
	public void setOperating_hour(String operating_hour) {
		this.operating_hour = operating_hour;
	}
	
}
