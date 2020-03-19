package com.ninebudget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A Credential.
 */
@Entity
@Audited
@Table(name = "credential")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "credential")
public class Credential implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "username", length = 50, unique = true, nullable = false)
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "custom")
    private String custom;

    @Column(name = "failedLoginCount")
    private int failedLoginCount;

    @Column(name = "lastFailedLogin")
    private LocalDateTime lastFailedLogin;

    @Column(name = "lockedOutUntil")
    private LocalDateTime lockedOutUntil;

    @OneToOne(mappedBy = "credential")
    @JsonIgnore
    private InstitutionAccount institutionAccount;

    @OneToOne(mappedBy = "credential")
    @JsonIgnore
    private ApplicationUser applicationUser;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Credential username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Credential password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustom() {
        return custom;
    }

    public Credential custom(String custom) {
        this.custom = custom;
        return this;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public InstitutionAccount getInstitutionAccount() {
        return institutionAccount;
    }

    public Credential institutionAccount(InstitutionAccount institutionAccount) {
        this.institutionAccount = institutionAccount;
        return this;
    }

    public void setInstitutionAccount(InstitutionAccount institutionAccount) {
        this.institutionAccount = institutionAccount;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public int getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(int failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public LocalDateTime getLastFailedLogin() {
        return lastFailedLogin;
    }

    public void setLastFailedLogin(LocalDateTime lastFailedLogin) {
        this.lastFailedLogin = lastFailedLogin;
    }

    public LocalDateTime getLockedOutUntil() {
        return lockedOutUntil;
    }

    public void setLockedOutUntil(LocalDateTime lockedOutUntil) {
        this.lockedOutUntil = lockedOutUntil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Credential)) {
            return false;
        }
        return id != null && id.equals(((Credential) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", custom='" + custom + '\'' +
                ", failedLoginCount=" + failedLoginCount +
                ", lastFailedLogin=" + lastFailedLogin +
                ", lockedOutUntil=" + lockedOutUntil +
                ", institutionAccount=" + institutionAccount +
                ", applicationUser=" + applicationUser +
                '}';
    }
}
