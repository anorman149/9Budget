package com.ninebudget.model.dto;

import com.ninebudget.model.CategoryType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CategoryDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;

    @NotNull
    private CategoryType type;

    @NotNull
    private Boolean active;

    private List<SubCategoryDto> subCategories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<SubCategoryDto> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryDto> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryDto categoryDto = (CategoryDto) o;
        if (categoryDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", type=" + type +
                ", active=" + active +
                ", subCategories=" + subCategories +
                '}';
    }
}
