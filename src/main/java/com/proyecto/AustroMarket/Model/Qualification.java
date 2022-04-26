package com.proyecto.AustroMarket.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long qualificationId;
    private double qualification;

    @ManyToOne
    @JoinColumn(name="idClient")
    private Client client;

    @ManyToOne
    @JoinColumn(name="idSaller")
    private Saller saller;

    public Qualification() {
    }

    public long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public double getQualification() {
        return qualification;
    }

    public void setQualification(double qualification) {
        this.qualification = qualification;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Saller getSaller() {
        return saller;
    }

    public void setSaller(Saller saller) {
        this.saller = saller;
    }
}
