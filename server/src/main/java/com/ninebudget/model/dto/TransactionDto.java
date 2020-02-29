package com.ninebudget.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class TransactionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String date;

    private String description;

    private CategoryDto category;

    private UUID budgetId;

    private UUID institutionAccountId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public UUID getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(UUID budgetId) {
        this.budgetId = budgetId;
    }

    public UUID getInstitutionAccountId() {
        return institutionAccountId;
    }

    public void setInstitutionAccountId(UUID institutionAccountId) {
        this.institutionAccountId = institutionAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TransactionDto transactionDto = (TransactionDto) o;
        if (transactionDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", budgetId=" + getBudgetId() +
            ", institutionAccountId=" + getInstitutionAccountId() +
            "}";
    }
}
