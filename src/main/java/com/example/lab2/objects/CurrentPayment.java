package com.example.lab2.objects;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name="current_payments")
public class CurrentPayment {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @Column(name="amount")
    private float amount;
    @Column(name="p_date")
    private Date paymentDate;
    @Column(name="doc_number")
    private int docNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(int docNumber) {
        this.docNumber = docNumber;
    }

    @Override
    public String toString() {
        return getDocNumber() + ") " + getPaymentDate() + " - " + getAmount() + " руб. ";
    }

    public String toStringFields() {
        return getDocNumber() + " " + getPaymentDate() + " " + getAmount();
    }
}
