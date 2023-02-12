package kz.dar.tech.eventservice.repository;

import kz.dar.tech.eventservice.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {
    Individual findByEmail(String email);
}
