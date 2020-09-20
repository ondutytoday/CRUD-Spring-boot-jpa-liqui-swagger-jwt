package org.vasileva.crud.service;

import org.vasileva.crud.entity.Supply;

import java.util.List;

public interface SupplyService {
    Supply getById(Long id);

    void save (Supply supply);

    void delete(Long id);

    List<Supply> getAll();
}
