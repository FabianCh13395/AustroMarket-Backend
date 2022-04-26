package com.proyecto.AustroMarket.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detailId;
    private int amount;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name="idBill")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name="idProduct")
    private Product product;

    public Detail() {
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
