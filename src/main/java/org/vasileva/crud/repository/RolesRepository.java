package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vasileva.crud.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Roles findByRoleName(String roleName);
}
