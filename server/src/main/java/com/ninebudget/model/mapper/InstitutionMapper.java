package com.ninebudget.model.mapper;

import com.ninebudget.model.Institution;
import com.ninebudget.model.dto.InstitutionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity {@link Institution} and its DTO {@link InstitutionDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {AccountMapper.class, CredentialMapper.class})
public interface InstitutionMapper extends EntityMapper<InstitutionDto, Institution> {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "credential.id", target = "credentialId")
    InstitutionDto toDto(Institution institution);

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "credentialId", target = "credential")
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "removeTransaction", ignore = true)
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
