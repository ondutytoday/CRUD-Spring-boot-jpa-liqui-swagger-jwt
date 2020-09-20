package org.vasileva.crud.service;

import org.vasileva.crud.entity.DishesSupply;

import java.util.List;

public interface DishesSupplyService {

    DishesSupply getById(Long id);

    void save (DishesSupply dishes);

    void delete(Long id);

    List<DishesSupply> getAll();
}
