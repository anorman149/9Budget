package com.ninebudget.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class InstitutionAccessDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String accessToken;

    @NotNull
    private Long itemId;

    @NotNull
    private Long institutionId;

    @NotNull
    private Long applicationUserId;

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

    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstitutionAccessDto)) return false;
        InstitutionAccessDto that = (InstitutionAccessDto) o;
        return id.equals(that.id) &&
                accessToken.equals(that.accessToken) &&
                itemId.equals(that.itemId) &&
                institutionId.equals(that.institutionId) &&
                applicationUserId.equals(that.applicationUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accessToken, itemId, institutionId, applicationUserId);
    }

    @Override
    public String toString() {
        return "InstitutionAccessDto{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", itemId=" + itemId +
                ", institutionId=" + institutionId +
                ", applicationUserId=" + applicationUserId +
                '}';
    }
}
