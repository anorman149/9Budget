package com.ninebudget.model.mapper;

import com.ninebudget.model.Institution;
import com.ninebudget.model.dto.InstitutionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper for the entity {@link Institution} and its DTO {@link InstitutionDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface InstitutionMapper extends EntityMapper<InstitutionDto, Institution> {

    @Mapping(source = "account", target = "account")
    Institution toEntity(InstitutionDto institutionDto);

    @Mapping(source = "account", target = "account")
    InstitutionDto toDto(Institution institution);

    default Institution fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Institution institution = new Institution();
        institution.setId(id);
        return institution;
    }
}
