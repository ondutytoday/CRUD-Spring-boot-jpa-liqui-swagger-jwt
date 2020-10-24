package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vasileva.crud.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
