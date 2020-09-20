package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vasileva.crud.entity.Supply;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
}
