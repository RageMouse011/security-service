package kz.dar.tech.securityservice.repository;

import kz.dar.tech.securityservice.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserListRepository extends JpaRepository<UserList, Long> {
    boolean existsByEmail(String email);
    UserList findByEmail(String email);
}
