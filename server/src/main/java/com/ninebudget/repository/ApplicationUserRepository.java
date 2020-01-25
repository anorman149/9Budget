package com.ninebudget.repository;

import com.ninebudget.model.ApplicationUser;
import com.ninebudget.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link ApplicationUser} entity.
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findOneByActivationKey(String activationKey);

    Optional<ApplicationUser> findOneByResetKey(String resetKey);

    Optional<ApplicationUser> findOneByEmailIgnoreCase(String email);

    Optional<ApplicationUser> findOneByCredential(Credential credential);
}
