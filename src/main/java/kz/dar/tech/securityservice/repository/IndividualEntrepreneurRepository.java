package kz.dar.tech.securityservice.repository;

import kz.dar.tech.securityservice.entity.IndividualEntrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IndividualEntrepreneurRepository extends JpaRepository<IndividualEntrepreneur, Long> {
    IndividualEntrepreneur findByEmail(String email);
}
