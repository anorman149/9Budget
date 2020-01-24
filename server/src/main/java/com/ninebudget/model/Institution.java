package com.ninebudget.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Institution.
 */
@Entity
@Table(name = "institution")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Institution implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private InstitutionType type;

    @NotNull
    @Column(name = "balance", precision = 21, scale = 2, nullable = false)
    private BigDecimal balance;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToOne
    @JoinColumn(unique = true)
    private Account account;

    @OneToOne
    @JoinColumn(unique = true)
    private Credential credential;

    @OneToMany(mappedBy = "institution")
    private Set<Transaction> transactions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Institution name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstitutionType getType() {
        return type;
    }

    public Institution type(InstitutionType type) {
        this.type = type;
        return this;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Institution balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean isActive() {
        return active;
    }

    public Institution active(Boolean active) {
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

    public Credential getCredential() {
        return credential;
    }

    public Institution credential(Credential credential) {
        this.credential = credential;
        return this;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Institution transactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Institution addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setInstitution(this);
        return this;
    }

    public Institution removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setInstitution(null);
        return this;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Institution)) {
            return false;
        }
        return id != null && id.equals(((Institution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Institution{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", balance=" + getBalance() +
            ", active='" + isActive() + "'" +
            "}";
    }
}
