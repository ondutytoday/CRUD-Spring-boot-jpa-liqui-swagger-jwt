package org.vasileva.crud.service;

import org.vasileva.crud.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff getById(Long id);

    void save (Staff dishes);

    void delete(Long id);

    List<Staff> getAll();
}
