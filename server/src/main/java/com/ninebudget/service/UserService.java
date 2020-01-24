package com.ninebudget.service;

import com.ninebudget.model.ApplicationUser;
import com.ninebudget.model.EmailAlreadyUsedException;
import com.ninebudget.model.InvalidPasswordException;
import com.ninebudget.model.UsernameAlreadyUsedException;
import com.ninebudget.model.dto.ApplicationUserDto;
import com.ninebudget.repository.ApplicationUserRepository;
import com.ninebudget.util.RandomUtil;
import com.ninebudget.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(ApplicationUserRepository applicationUserRepository, PasswordEncoder passwordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<ApplicationUser> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return applicationUserRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<ApplicationUser> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return applicationUserRepository.findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                return user;
            });
    }

    public Optional<ApplicationUser> requestPasswordReset(String mail) {
        return applicationUserRepository.findOneByEmailIgnoreCase(mail)
            .filter(ApplicationUser::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                return user;
            });
    }

    public ApplicationUser registerUser(ApplicationUserDto applicationUserDTO, String password) {
        applicationUserRepository.findOneByLogin(applicationUserDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new UsernameAlreadyUsedException();
            }
        });
        applicationUserRepository.findOneByEmailIgnoreCase(applicationUserDTO.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new EmailAlreadyUsedException();
            }
        });
        ApplicationUser newApplicationUser = new ApplicationUser();
        String encryptedPassword = passwordEncoder.encode(password);
        newApplicationUser.setLogin(applicationUserDTO.getLogin().toLowerCase());
        // new user gets initially a generated password
        newApplicationUser.setPassword(encryptedPassword);
        newApplicationUser.setFirstName(applicationUserDTO.getFirstName());
        newApplicationUser.setLastName(applicationUserDTO.getLastName());
        if (applicationUserDTO.getEmail() != null) {
            newApplicationUser.setEmail(applicationUserDTO.getEmail().toLowerCase());
        }
        // new user is not active
        newApplicationUser.setActivated(false);
        // new user gets registration key
        newApplicationUser.setActivationKey(RandomUtil.generateActivationKey());
        applicationUserRepository.save(newApplicationUser);
        log.debug("Created Information for User: {}", newApplicationUser);
        return newApplicationUser;
    }

    private boolean removeNonActivatedUser(ApplicationUser existingApplicationUser){
        if (existingApplicationUser.getActivated()) {
             return false;
        }
        applicationUserRepository.delete(existingApplicationUser);
        applicationUserRepository.flush();
        return true;
    }

    public ApplicationUser createUser(ApplicationUserDto applicationUserDTO) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setLogin(applicationUserDTO.getLogin().toLowerCase());
        applicationUser.setFirstName(applicationUserDTO.getFirstName());
        applicationUser.setLastName(applicationUserDTO.getLastName());
        if (applicationUserDTO.getEmail() != null) {
            applicationUser.setEmail(applicationUserDTO.getEmail().toLowerCase());
        }

        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        applicationUser.setPassword(encryptedPassword);

        applicationUser.setResetKey(RandomUtil.generateResetKey());
        applicationUser.setResetDate(Instant.now());
        applicationUser.setActivated(true);

        applicationUserRepository.save(applicationUser);
        log.debug("Created Information for User: {}", applicationUser);

        return applicationUser;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user.
     * @param lastName  last name of user.
     * @param email     email id of user.
     * @param langKey   language key.
     * @param imageUrl  image URL of user.
     */
    public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(applicationUserRepository::findOneByLogin)
            .ifPresent(user -> {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                if (email != null) {
	                user.setEmail(email.toLowerCase());
                }

                log.debug("Changed Information for User: {}", user);
            });
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param applicationUserDTO user to update.
     * @return updated user.
     */
    public Optional<ApplicationUserDto> updateUser(ApplicationUserDto applicationUserDTO) {
        return Optional.of(applicationUserRepository
            .findById(applicationUserDTO.getId()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(user -> {
                user.setLogin(applicationUserDTO.getLogin().toLowerCase());
                user.setFirstName(applicationUserDTO.getFirstName());
                user.setLastName(applicationUserDTO.getLastName());

                if (applicationUserDTO.getEmail() != null) {
                    user.setEmail(applicationUserDTO.getEmail().toLowerCase());
                }

                user.setActivated(applicationUserDTO.isActivated());

                log.debug("Changed Information for User: {}", user);

                return user;
            })
            .map(ApplicationUserDto::new);
    }

    public void deleteUser(String login) {
        applicationUserRepository.findOneByLogin(login).ifPresent(user -> {
            applicationUserRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(applicationUserRepository::findOneByLogin)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }

                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);

                log.debug("Changed password for User: {}", user);
            });
    }
}
