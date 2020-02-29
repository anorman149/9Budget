package com.ninebudget.repository;

import com.ninebudget.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


/**
 * Spring Data  repository for the Credential entity.
 */
@Repository
public interface CredentialRepository extends JpaRepository<Credential, UUID> {

}
