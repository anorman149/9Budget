package com.ninebudget.model.dto;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Audited
public class SubCategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @NotNull
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SubCategoryDto subCategoryDto = (SubCategoryDto) o;
        if (subCategoryDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subCategoryDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SubCategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
