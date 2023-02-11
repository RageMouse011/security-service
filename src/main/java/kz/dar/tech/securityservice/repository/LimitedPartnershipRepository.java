package kz.dar.tech.securityservice.repository;

import kz.dar.tech.securityservice.entity.LimitedPartnership;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LimitedPartnershipRepository extends JpaRepository<LimitedPartnership, Long> {
    LimitedPartnership findByEmail(String email);
}
