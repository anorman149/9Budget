package com.ninebudget.model.mapper;

import com.ninebudget.model.InstitutionAccount;
import com.ninebudget.model.dto.InstitutionAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {AccountMapper.class, CredentialMapper.class, InstitutionMapper.class})
public interface InstitutionAccountMapper extends EntityMapper<InstitutionAccountDto, InstitutionAccount>{

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "credential.id", target = "credentialId")
    @Mapping(source = "institution.id", target = "institutionId")
    InstitutionAccountDto toDto(InstitutionAccount institutionAccount);

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "credentialId", target = "credential")
    @Mapping(source = "institutionId", target = "institution")
    @Mapping(target = "transactions", ignore = true)
    InstitutionAccount toEntity(InstitutionAccountDto institutionAccountDto);

    default InstitutionAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        InstitutionAccount institutionAccount = new InstitutionAccount();
        institutionAccount.setId(id);
        return institutionAccount;
    }
}
