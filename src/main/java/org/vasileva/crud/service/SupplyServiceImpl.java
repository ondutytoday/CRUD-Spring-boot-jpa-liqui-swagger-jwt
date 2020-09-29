package org.vasileva.crud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vasileva.crud.entity.Supply;
import org.vasileva.crud.repository.SupplyRepository;

import java.util.List;

@Slf4j
@Service
public class SupplyServiceImpl implements SupplyService{

    @Autowired
    SupplyRepository supplyRepository;

    @Override
    public Supply getById(Long id) {
        log.info("IN SupplyServiceImpl getById {}", id);
        return supplyRepository.getOne(id);
    }

    @Override
    public void save(Supply supply) {
        log.info("IN SupplyServiceImpl save {}", supply);
        supplyRepository.save(supply);
    }

    @Override
    public void delete(Long id) {
        log.info("IN SupplyServiceImpl delete {}", id);
        supplyRepository.deleteById(id);
    }

    @Override
    public List<Supply> getAll() {
        log.info("IN SupplyServiceImpl getAll");
        return supplyRepository.findAll();
    }
}
