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

    private UUID budgetID;

    private String budgetName;

    private InstitutionAccountDto institutionAccount;

    private UUID accountID;

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

    public UUID getBudgetID() {
        return budgetID;
    }

    public void setBudgetID(UUID budgetID) {
        this.budgetID = budgetID;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public InstitutionAccountDto getInstitutionAccount() {
        return institutionAccount;
    }

    public void setInstitutionAccount(InstitutionAccountDto institutionAccount) {
        this.institutionAccount = institutionAccount;
    }

    public UUID getAccountID() {
        return accountID;
    }

    public void setAccountID(UUID accountID) {
        this.accountID = accountID;
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
                "id=" + id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", budgetID=" + budgetID +
                ", budgetName='" + budgetName + '\'' +
                ", institutionAccount=" + institutionAccount +
                ", accountID=" + accountID +
                '}';
    }
}
