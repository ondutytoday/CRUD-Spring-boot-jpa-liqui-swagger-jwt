package org.vasileva.crud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vasileva.crud.entity.DishesSupply;
import org.vasileva.crud.repository.DishesSupplyRepository;

import java.util.List;

@Slf4j
@Service
public class DishesSupplyServiceImpl implements DishesSupplyService {

    @Autowired
    DishesSupplyRepository dishesSupplyRepository;

    @Override
    public DishesSupply getById(Long id) {
        log.info("IN DishesSupplyServiceImpl getById {}", id);
        return dishesSupplyRepository.getOne(id);
    }

    @Override
    public void save(DishesSupply dishesSupply) {
        log.info("IN DishesSupplyServiceImpl save {}", dishesSupply);
        dishesSupplyRepository.save(dishesSupply);
    }

    @Override
    public void delete(Long id) {
        log.info("IN DishesSupplyServiceImpl delete {}", id);
        dishesSupplyRepository.deleteById(id);
    }

    @Override
    public List<DishesSupply> getAll() {
        log.info("IN DishesSupplyServiceImpl getAll");
        return dishesSupplyRepository.findAll();
    }
}
