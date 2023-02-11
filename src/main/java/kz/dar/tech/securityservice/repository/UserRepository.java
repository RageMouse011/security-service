package kz.dar.tech.securityservice.repository;

import kz.dar.tech.securityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
