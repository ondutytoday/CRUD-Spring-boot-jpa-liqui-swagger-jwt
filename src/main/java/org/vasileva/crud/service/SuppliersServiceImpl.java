package org.vasileva.crud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vasileva.crud.entity.Suppliers;
import org.vasileva.crud.repository.SuppliersRepository;

import java.util.List;

@Slf4j
@Service
public class SuppliersServiceImpl implements SuppliersService{

    @Autowired
    SuppliersRepository suppliersRepository;

    @Override
    public Suppliers getById(Long id) {
        log.info("IN SuppliersServiceImpl getById {}", id);
        return suppliersRepository.getOne(id);
    }

    @Override
    public void save(Suppliers supplier) {
        log.info("IN SuppliersServiceImpl save {}", supplier);
        suppliersRepository.save(supplier);
    }

    @Override
    public void delete(Long id) {
        log.info("IN SuppliersServiceImpl delete {}", id);
        suppliersRepository.deleteById(id);
    }

    @Override
    public List<Suppliers> getAll() {
        log.info("IN SuppliersServiceImpl getAll");
        return suppliersRepository.findAll();
    }
}
