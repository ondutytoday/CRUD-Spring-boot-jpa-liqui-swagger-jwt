package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vasileva.crud.entity.Dishes;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long>{



}
