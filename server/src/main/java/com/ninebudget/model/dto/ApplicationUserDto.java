package com.ninebudget.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class ApplicationUserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(min = 10, max = 20)
    private String phone; //phone number have 10digit big guy :D

    @Size(max = 20)
    private String resetKey;

    @Size(max = 20)
    private String activationKey;

    private boolean locked;

    private Instant lastFailedLogin;

    private Instant lockedOutUntil;

    private boolean activated = false;

    private Integer failedLoginAttempts = 0;

    private CredentialDto credential;

    private AccountDto account;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public CredentialDto getCredential() {
        return credential;
    }

    public void setCredential(CredentialDto credential) {
        this.credential = credential;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Instant getLastFailedLogin() {
        return lastFailedLogin;
    }

    public void setLastFailedLogin(Instant lastFailedLogin) {
        this.lastFailedLogin = lastFailedLogin;
    }

    public Instant getLockedOutUntil() {
        return lockedOutUntil;
    }

    public void setLockedOutUntil(Instant lockedOutUntil) {
        this.lockedOutUntil = lockedOutUntil;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    @Override
    public String toString() {
        return "ApplicationUserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", resetKey='" + resetKey + '\'' +
                ", activationKey='" + activationKey + '\'' +
                ", locked=" + locked +
                ", lastFailedLogin=" + lastFailedLogin +
                ", lockedOutUntil=" + lockedOutUntil +
                ", activated=" + activated +
                ", failedLoginAttemptsSinceLockout=" + failedLoginAttempts +
                ", credential=" + credential +
                ", account=" + account +
                '}';
    }
}
