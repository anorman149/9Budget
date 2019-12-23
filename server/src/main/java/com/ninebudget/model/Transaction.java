package com.ninebudget.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Clock;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Clock date;
    private String description;
    private Category category;
    private BigDecimal amount;
    private Institution institution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clock getDate() {
        return date;
    }

    public void setDate(Clock date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
}
