package com.ninebudget.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * A Budget.
 */
@Entity
@Audited
@Table(name = "budget")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "budget")
public class Budget implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "budget_timing", nullable = false)
    private BudgetTiming budgetTiming;

    @NotNull
    @Column(name = "use_left_over", nullable = false)
    private Boolean useLeftOver;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne
    @NotNull
    @JoinColumn()
    private Account account;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transaction> transactions;

    @ManyToOne
    @JoinColumn()
    private Category category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Budget name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Budget amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BudgetTiming getBudgetTiming() {
        return budgetTiming;
    }

    public Budget budgetTiming(BudgetTiming budgetTiming) {
        this.budgetTiming = budgetTiming;
        return this;
    }

    public void setBudgetTiming(BudgetTiming budgetTiming) {
        this.budgetTiming = budgetTiming;
    }

    public Boolean isUseLeftOver() {
        return useLeftOver;
    }

    public Budget useLeftOver(Boolean useLeftOver) {
        this.useLeftOver = useLeftOver;
        return this;
    }

    public void setUseLeftOver(Boolean useLeftOver) {
        this.useLeftOver = useLeftOver;
    }

    public Boolean isActive() {
        return active;
    }

    public Budget active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Budget transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Budget addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setBudget(this);
        return this;
    }

    public Budget removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setBudget(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Category getCategory() {
        return category;
    }

    public Budget category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Budget)) {
            return false;
        }
        return id != null && id.equals(((Budget) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", budgetTiming=" + budgetTiming +
                ", useLeftOver=" + useLeftOver +
                ", active=" + active +
                ", account=" + account +
                ", transactions=" + transactions +
                ", category=" + category +
                '}';
    }
}
