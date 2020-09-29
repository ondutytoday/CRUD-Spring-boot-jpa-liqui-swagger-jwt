package org.vasileva.crud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vasileva.crud.entity.Dishes;
import org.vasileva.crud.repository.DishesRepository;

import java.util.List;

@Slf4j
@Service
public class DishesServiceImpl implements DishesService {

    @Autowired
    DishesRepository dishesRepository;

    @Override
    public Dishes getById(Long id) {
        log.info("IN DishesServiceImpl getById {}", id);
        return dishesRepository.getOne(id);
    }

    @Override
    public void save(Dishes dish) {
        log.info("IN DishesServiceImpl save {}", dish);
        dishesRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        log.info("IN DishesServiceImpl delete {}", id);
        dishesRepository.deleteById(id);
    }

    @Override
    public List<Dishes> getAll() {
        log.info("IN DishesServiceImpl getAll");
        return dishesRepository.findAll();
    }
}
