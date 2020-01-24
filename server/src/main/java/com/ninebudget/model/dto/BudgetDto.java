package com.ninebudget.model.dto;

import com.ninebudget.model.BudgetTiming;
import com.ninebudget.model.Transaction;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class BudgetDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BudgetTiming budgetTiming;

    @NotNull
    private Boolean useLeftOver;

    @NotNull
    private Boolean active;

    @NotNull
    private Long accountId;

    private CategoryDto category;

    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BudgetTiming getBudgetTiming() {
        return budgetTiming;
    }

    public void setBudgetTiming(BudgetTiming budgetTiming) {
        this.budgetTiming = budgetTiming;
    }

    public Boolean isUseLeftOver() {
        return useLeftOver;
    }

    public void setUseLeftOver(Boolean useLeftOver) {
        this.useLeftOver = useLeftOver;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto categoryDto) {
        this.category = categoryDto;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BudgetDto budgetDto = (BudgetDto) o;
        if (budgetDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), budgetDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BudgetDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", amount=" + getAmount() +
            ", budgetTiming='" + getBudgetTiming() + "'" +
            ", useLeftOver='" + isUseLeftOver() + "'" +
            ", active='" + isActive() + "'" +
            ", systemAccountId=" + getAccountId() +
            "}";
    }
}
