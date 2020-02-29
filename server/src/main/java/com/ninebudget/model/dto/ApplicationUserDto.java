package com.ninebudget.model.dto;

import com.ninebudget.model.ApplicationUser;
import com.ninebudget.model.Credential;

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

    @Size(min = 11, max = 20)
    private String phone;

    private boolean activated = false;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Credential credential;

    public ApplicationUserDto() {
        // Empty constructor needed for Jackson.
    }

    public ApplicationUserDto(ApplicationUser applicationUser) {
        this.id = applicationUser.getId();
        this.firstName = applicationUser.getFirstName();
        this.lastName = applicationUser.getLastName();
        this.email = applicationUser.getEmail();
        this.activated = applicationUser.getActivated();
        this.createdBy = applicationUser.getCreatedBy();
        this.createdDate = applicationUser.getCreatedDate();
        this.lastModifiedBy = applicationUser.getLastModifiedBy();
        this.lastModifiedDate = applicationUser.getLastModifiedDate();
        this.credential = applicationUser.getCredential();
        this.phone = applicationUser.getPhone();
    }

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ApplicationUserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", activated=" + activated +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", credential=" + credential +
                '}';
    }
}
