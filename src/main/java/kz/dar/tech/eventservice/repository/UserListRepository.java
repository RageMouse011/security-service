package kz.dar.tech.eventservice.repository;

import kz.dar.tech.eventservice.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {
    boolean existsByEmail(String email);
    UserList findByEmail(String email);
}
