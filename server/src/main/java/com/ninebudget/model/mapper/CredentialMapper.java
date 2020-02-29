package com.ninebudget.model.mapper;

import com.ninebudget.model.Credential;
import com.ninebudget.model.dto.CredentialDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper for the entity {@link Credential} and its DTO {@link CredentialDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {})
public interface CredentialMapper extends EntityMapper<CredentialDto, Credential> {

    @Mapping(target = "institutionAccount", ignore = true)
    @Mapping(target = "applicationUser", ignore = true)
    Credential toEntity(CredentialDto credentialDto);

    default Credential fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Credential credential = new Credential();
        credential.setId(id);
        return credential;
    }
}
