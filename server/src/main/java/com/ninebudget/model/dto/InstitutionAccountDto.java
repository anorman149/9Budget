package com.ninebudget.model.dto;

import com.ninebudget.model.InstitutionType;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Audited
public class InstitutionAccountDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private InstitutionType type;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private Boolean active;

    private UUID accountId;

    private UUID credentialId;

    private UUID institutionId;

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

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public UUID getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(UUID credentialId) {
        this.credentialId = credentialId;
    }

    public UUID getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(UUID institutionId) {
        this.institutionId = institutionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstitutionDto institutionDto = (InstitutionDto) o;
        if (institutionDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), institutionDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstitutionAccountDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                ", active=" + active +
                ", accountId=" + accountId +
                ", credentialId=" + credentialId +
                ", institutionId=" + institutionId +
                '}';
    }
}
