package com.ninebudget.model.mapper;

import com.ninebudget.model.ApplicationUser;
import com.ninebudget.model.dto.ApplicationUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Mapper for the entity {@link ApplicationUser} and its DTO called {@link ApplicationUserDto}.
 */
@Component
@Mapper(componentModel = "spring", uses = {AccountMapper.class, CredentialMapper.class})
public interface ApplicationUserMapper extends EntityMapper<ApplicationUserDto, ApplicationUser>{

    @Mapping(source = "account", target = "account")
    @Mapping(source = "credential", target = "credential")
    ApplicationUserDto toDto(ApplicationUser applicationUser);

    @Mapping(source = "account", target = "account")
    @Mapping(source = "credential", target = "credential")
    ApplicationUser toEntity(ApplicationUserDto applicationUserDto);

    default ApplicationUser fromId(UUID id) {
        if (id == null) {
            return null;
        }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(id);
        return applicationUser;
    }
}
