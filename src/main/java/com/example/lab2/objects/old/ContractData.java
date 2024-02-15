package com.example.lab2.objects.old;

import com.example.lab2.objects.Student;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name="contract_data")
public class ContractData {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id")
    private int id;
    @ManyToOne (fetch=FetchType.LAZY,
            cascade=CascadeType.MERGE)
    @JoinColumn (name="student_id")
    private Student student;
    @Column(name="c_date")
    private Date contractDate;
    @Column(name="payment_amount")
    private float paymentAmount;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @JoinColumn(name="current_payments_id")
    private CurrentPayment currentPayments;
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name="status_id")
    private Status statuses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public CurrentPayment getCurrentPayments() {
        return currentPayments;
    }

    public void setCurrentPayments(CurrentPayment currentPayments) {
        this.currentPayments = currentPayments;
    }

    public Status getStatuses() {
        return statuses;
    }

    public void setStatuses(Status statuses) {
        this.statuses = statuses;
    }

    public String toStringFields() {
        return getStudent().toStringFields() + " " + getContractDate() + " " + getPaymentAmount() + " " +
                getCurrentPayments().toStringFields() + " " + getStatuses().toStringFields();
    }
}
