package com.nbu.logistics.dto;

public class CreateShipmentDto extends BaseDto {
	private String target;
	private Boolean toOffice;
	private String address;
	private double weight;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
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

	public Boolean getToOffice() {
		return toOffice;
	}

	public void setToOffice(Boolean toOffice) {
		this.toOffice = toOffice;
	}
}
