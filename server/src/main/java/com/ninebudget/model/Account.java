package com.ninebudget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * A Account.
 */
@Entity
@Table(name = "account")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApplicationUser> applicationUsers;

    @ManyToMany
    @JsonIgnore
    private List<Category> category;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Budget> budgets;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private InstitutionAccount institutionAccount;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Account name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return active;
    }

    public Account active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<ApplicationUser> getApplicationUsers() {
        return applicationUsers;
    }

    public Account users(Set<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
        return this;
    }

    public Account addUser(ApplicationUser applicationUser) {
        this.applicationUsers.add(applicationUser);
        applicationUser.setAccount(this);
        return this;
    }

    public Account removeUser(ApplicationUser applicationUser) {
        this.applicationUsers.remove(applicationUser);
        applicationUser.setAccount(null);
        return this;
    }

    public void setApplicationUsers(Set<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    public InstitutionAccount getInstitutionAccount() {
        return institutionAccount;
    }

    public Account institutionAccount(InstitutionAccount institutionAccount) {
        this.institutionAccount = institutionAccount;
        return this;
    }

    public void setInstitutionAccount(InstitutionAccount institutionAccount) {
        this.institutionAccount = institutionAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        return id != null && id.equals(((Account) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", applicationUsers=" + applicationUsers +
                ", category=" + category +
                ", budgets=" + budgets +
                ", institutionAccount=" + institutionAccount +
                '}';
    }
}
