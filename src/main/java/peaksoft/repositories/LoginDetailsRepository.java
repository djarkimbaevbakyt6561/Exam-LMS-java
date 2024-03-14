package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entities.LoginDetails;

import java.util.Optional;

public interface LoginDetailsRepository extends JpaRepository<LoginDetails, Long> {
    Optional<LoginDetails> findByEmail(String email);
}