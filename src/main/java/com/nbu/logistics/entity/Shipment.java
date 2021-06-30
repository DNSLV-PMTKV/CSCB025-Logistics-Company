package com.nbu.logistics.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "shipments")
public class Shipment extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User target;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "weight", nullable = false)
	private double weight;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private User employee;

	@Column(name = "registered_status", nullable = false)
	@ColumnDefault("false")
	private boolean registeredStatus;

	@Column(name = "delivered", nullable = false)
	@ColumnDefault("false")
	private boolean deliveredStatus;

	@Column
	private double price;

	@Column
	private Boolean toOffice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
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

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
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
