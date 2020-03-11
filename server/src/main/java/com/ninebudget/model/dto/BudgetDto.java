package com.ninebudget.model.dto;

import com.ninebudget.model.BudgetTiming;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BudgetDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Audited
    @NotNull
    private String name;

    @Audited
    @NotNull
    private BigDecimal amount;

    @NotNull
    private BudgetTiming budgetTiming;

    @NotNull
    private Boolean useLeftOver;

    @NotNull
    private Boolean active;

    @Audited
    @NotNull
    private UUID accountId;

    @NotAudited
    private CategoryDto category;

    @OneToMany
    @NotAudited
    private List<TransactionDto> transactions;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto categoryDto) {
        this.category = categoryDto;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", budgetTiming=" + budgetTiming +
                ", useLeftOver=" + useLeftOver +
                ", active=" + active +
                ", accountId=" + accountId +
                ", category=" + category +
                ", transactions=" + transactions +
                '}';
    }
}
