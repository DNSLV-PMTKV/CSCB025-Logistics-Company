package com.nbu.logistics.dto;

public class OfficeDto extends BaseDto {
	private Long id;
	private String city;
	private String address;
	private CompanyDetailsDto company;

	public OfficeDto() {
	}

	public OfficeDto(Long id, String city, String address, CompanyDetailsDto company) {
		this.id = id;	
		this.city = city;
		this.address = address;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CompanyDetailsDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDetailsDto company) {
		this.company = company;
	}

}
