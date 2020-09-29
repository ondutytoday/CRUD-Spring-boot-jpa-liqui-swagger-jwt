package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vasileva.crud.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
