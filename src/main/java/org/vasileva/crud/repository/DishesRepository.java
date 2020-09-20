package org.vasileva.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vasileva.crud.entity.Dishes;

/**
* Repo for {@link Dishes} class.
*/
public interface DishesRepository extends JpaRepository<Dishes, Long> {
}
