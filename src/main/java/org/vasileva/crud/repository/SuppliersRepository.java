package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vasileva.crud.entity.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {
}
