package com.ninebudget.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InstitutionAccess.
 */
@Entity
@Table(name = "institution_access")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "institution")
public class InstitutionAccess implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @NotNull
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @ManyToOne()
    @JoinColumn
    private Institution institution;

    @ManyToOne()
    @JoinColumn
    private ApplicationUser applicationUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstitutionAccess)) return false;
        InstitutionAccess that = (InstitutionAccess) o;
        return id.equals(that.id) &&
                accessToken.equals(that.accessToken) &&
                itemId.equals(that.itemId) &&
                institution.equals(that.institution) &&
                applicationUser.equals(that.applicationUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accessToken, itemId, institution, applicationUser);
    }

    @Override
    public String toString() {
        return "InstitutionAccess{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", itemId=" + itemId +
                ", institution=" + institution +
                ", applicationUser=" + applicationUser +
                '}';
    }
}
