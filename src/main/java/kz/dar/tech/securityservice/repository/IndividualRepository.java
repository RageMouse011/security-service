package kz.dar.tech.securityservice.repository;

import kz.dar.tech.securityservice.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IndividualRepository extends JpaRepository<Individual, Long> {
    Individual findByEmail(String email);
}
