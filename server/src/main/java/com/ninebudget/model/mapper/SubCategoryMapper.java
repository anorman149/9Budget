package com.ninebudget.model.mapper;

import com.ninebudget.model.SubCategory;
import com.ninebudget.model.dto.SubCategoryDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper for the entity {@link SubCategory} and its DTO {@link SubCategoryDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {})
public interface SubCategoryMapper extends EntityMapper<SubCategoryDto, SubCategory> {

    default SubCategory fromId(UUID id) {
        if (id == null) {
            return null;
        }
        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);
        return subCategory;
    }
}
