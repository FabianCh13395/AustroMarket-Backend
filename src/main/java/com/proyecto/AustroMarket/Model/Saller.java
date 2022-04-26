package com.proyecto.AustroMarket.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Saller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sallerId;
    @Column(length = 200)
    private String address;
    @Column(length = 50)
    private String businessName;
    @Column
    private double transport;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Saller() {
    }

    public long getSallerId() {
        return sallerId;
    }

    public void setSallerId(long sallerId) {
        this.sallerId = sallerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public double getTransport() {
        return transport;
    }

    public void setTransport(double transport) {
        this.transport = transport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
