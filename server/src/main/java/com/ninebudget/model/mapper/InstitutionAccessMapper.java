package com.ninebudget.model.mapper;

import com.ninebudget.model.InstitutionAccess;
import com.ninebudget.model.dto.InstitutionAccessDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {ApplicationUserMapper.class, InstitutionMapper.class})
public interface InstitutionAccessMapper extends EntityMapper<InstitutionAccessDto, InstitutionAccess>{

    @Mapping(source = "applicationUser.id", target = "applicationUserId")
    @Mapping(source = "institution.id", target = "institutionId")
    InstitutionAccessDto toDto(InstitutionAccess institutionAccess);

    @Mapping(source = "applicationUserId", target = "applicationUser")
    @Mapping(source = "institutionId", target = "institution")
    InstitutionAccess toEntity(InstitutionAccessDto institutionAccessDto);

    default InstitutionAccess fromId(Long id) {
        if (id == null) {
            return null;
        }
        InstitutionAccess institutionAccess = new InstitutionAccess();
        institutionAccess.setId(id);
        return institutionAccess;
    }
}
