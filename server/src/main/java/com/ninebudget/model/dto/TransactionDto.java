package com.ninebudget.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class TransactionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String date;

    private String description;

    private CategoryDto category;

    private Long budgetId;

    private Long institutionAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Long getInstitutionAccountId() {
        return institutionAccountId;
    }

    public void setInstitutionAccountId(Long institutionAccountId) {
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
