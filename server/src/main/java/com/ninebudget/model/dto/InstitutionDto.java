package com.ninebudget.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class InstitutionDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "InstitutionDto{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
