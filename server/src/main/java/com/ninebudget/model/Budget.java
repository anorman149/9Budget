package com.ninebudget.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Category category;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BudgetTiming timing;

    @NotNull
    private boolean useLeftOver;

    private Account account;

    @NotNull
    private boolean active;


    private List<Transaction> transactions;

    public Budget() {
    }

    public Budget(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BudgetTiming getTiming() {
        return timing;
    }

    public void setTiming(BudgetTiming timing) {
        this.timing = timing;
    }

    public boolean isUseLeftOver() {
        return useLeftOver;
    }

    public void setUseLeftOver(boolean useLeftOver) {
        this.useLeftOver = useLeftOver;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
