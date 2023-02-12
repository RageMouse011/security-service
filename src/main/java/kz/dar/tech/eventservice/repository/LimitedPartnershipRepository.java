package kz.dar.tech.eventservice.repository;

import kz.dar.tech.eventservice.entity.LimitedPartnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LimitedPartnershipRepository extends JpaRepository<LimitedPartnership, Long> {
    LimitedPartnership findByEmail(String email);
}
