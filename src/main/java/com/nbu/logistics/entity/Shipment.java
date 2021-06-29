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

//    @Column(name = "employee", nullable = false)
//    private User deskGuy;

    @Column(name ="delivered", nullable = false)
    @ColumnDefault("false")
    private boolean deliveredStatus;

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

    public boolean isDeliveredStatus() {
        return deliveredStatus;
    }

    public void setDeliveredStatus(boolean deliveredStatus) {
        this.deliveredStatus = deliveredStatus;
    }
}
