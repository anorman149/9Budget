package com.ninebudget.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "institution")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn()
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("transactions")
    private Budget budget;

    @ManyToOne
    @JsonIgnoreProperties("transactions")
    private InstitutionAccount institutionAccount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Transaction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public Transaction category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Budget getBudget() {
        return budget;
    }

    public Transaction budget(Budget budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public InstitutionAccount getInstitutionAccount() {
        return institutionAccount;
    }

    public void setInstitutionAccount(InstitutionAccount institutionAccount) {
        this.institutionAccount = institutionAccount;
    }

    public Transaction institutionAccount(InstitutionAccount institutionAccount) {
        this.institutionAccount = institutionAccount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", budget=" + budget +
                ", institutionAccount=" + institutionAccount +
                '}';
    }
}
