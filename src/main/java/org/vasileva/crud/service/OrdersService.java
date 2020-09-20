package org.vasileva.crud.service;

import org.vasileva.crud.entity.Orders;

import java.util.List;

public interface OrdersService {
    Orders getById(Long id);

    void save (Orders dishes);

    void delete(Long id);

    List<Orders> getAll();
}
