package org.vasileva.crud.service;

import org.vasileva.crud.entity.Suppliers;

import java.util.List;

public interface SuppliersService {
    Suppliers getById(Long id);

    void save (Suppliers supplier);

    void delete(Long id);

    List<Suppliers> getAll();
}
