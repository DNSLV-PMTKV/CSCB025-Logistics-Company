package com.nbu.logistics.dto;

public class ShipmentDto extends BaseDto {
	private Long id;
	private UserDto sender;
	private UserDto target;
	private String address;
	private double price;
	private Boolean toOffice;
	private double weight;
	private UserDto employee;
	private boolean registeredStatus;
	private boolean deliveredStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getSender() {
		return sender;
	}

	public void setSender(UserDto sender) {
		this.sender = sender;
	}

	public UserDto getTarget() {
		return target;
	}

	public void setTarget(UserDto target) {
		this.target = target;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public UserDto getEmployee() {
		return employee;
	}

	public void setEmployee(UserDto employee) {
		this.employee = employee;
	}

	public boolean isRegisteredStatus() {
		return registeredStatus;
	}

	public void setRegisteredStatus(boolean registeredStatus) {
		this.registeredStatus = registeredStatus;
	}

	public boolean isDeliveredStatus() {
		return deliveredStatus;
	}

	public void setDeliveredStatus(boolean deliveredStatus) {
		this.deliveredStatus = deliveredStatus;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Boolean getToOffice() {
		return toOffice;
	}

	public void setToOffice(Boolean toOffice) {
		this.toOffice = toOffice;
	}

}