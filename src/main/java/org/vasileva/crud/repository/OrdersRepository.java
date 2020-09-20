package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vasileva.crud.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
