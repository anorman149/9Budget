package com.ninebudget.model.mapper;

import com.ninebudget.model.ApplicationUser;
import com.ninebudget.model.dto.ApplicationUserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link ApplicationUser} and its DTO called {@link ApplicationUserDto}.
 */
@Service
public class ApplicationUserMapper {

    public List<ApplicationUserDto> usersToUserDTOs(List<ApplicationUser> applicationUsers) {
        return applicationUsers.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public ApplicationUserDto userToUserDTO(ApplicationUser applicationUser) {
        return new ApplicationUserDto(applicationUser);
    }

    public List<ApplicationUser> userDTOsToUsers(List<ApplicationUserDto> applicationUserDTOS) {
        return applicationUserDTOS.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public ApplicationUser userDTOToUser(ApplicationUserDto applicationUserDTO) {
        if (applicationUserDTO == null) {
            return null;
        } else {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setId(applicationUserDTO.getId());
            applicationUser.setFirstName(applicationUserDTO.getFirstName());
            applicationUser.setLastName(applicationUserDTO.getLastName());
            applicationUser.setEmail(applicationUserDTO.getEmail());
            applicationUser.setActivated(applicationUserDTO.isActivated());
            applicationUser.setCredential(applicationUserDTO.getCredential());
            return applicationUser;
        }
    }

    public ApplicationUser userFromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId(id);
        return applicationUser;
    }
}
