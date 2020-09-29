package org.vasileva.crud.service;

import org.vasileva.crud.entity.Dishes;

import java.util.List;

public interface DishesService {

    Dishes getById(Long id);

    void save (Dishes dishes);

    void delete(Long id);

    List<Dishes> getAll();

}
