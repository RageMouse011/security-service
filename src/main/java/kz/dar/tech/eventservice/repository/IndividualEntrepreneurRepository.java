package kz.dar.tech.eventservice.repository;

import kz.dar.tech.eventservice.entity.IndividualEntrepreneur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IndividualEntrepreneurRepository extends JpaRepository<IndividualEntrepreneur, Long> {
    IndividualEntrepreneur findByEmail(String email);
}
