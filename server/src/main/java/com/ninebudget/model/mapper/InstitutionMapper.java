package com.ninebudget.model.mapper;

import com.ninebudget.model.Institution;
import com.ninebudget.model.dto.InstitutionDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity {@link Institution} and its DTO {@link InstitutionDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {})
public interface InstitutionMapper extends EntityMapper<InstitutionDto, Institution> {

    Institution toEntity(InstitutionDto institutionDto);

    default Institution fromId(Long id) {
        if (id == null) {
            return null;
        }
        Institution institution = new Institution();
        institution.setId(id);
        return institution;
    }
}
