package com.ninebudget.model.mapper;

import com.ninebudget.model.Category;
import com.ninebudget.model.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {AccountMapper.class, BudgetMapper.class, SubCategoryMapper.class})
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

    @Mapping(source = "subCategory", target = "subCategory")
    CategoryDto toDto(Category category);

    @Mapping(source = "subCategory", target = "subCategory")
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "budgets", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Category toEntity(CategoryDto categoryDto);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
