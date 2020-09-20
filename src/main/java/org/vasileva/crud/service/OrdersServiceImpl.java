package org.vasileva.crud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vasileva.crud.entity.Orders;
import org.vasileva.crud.repository.OrdersRepository;

import java.util.List;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public Orders getById(Long id) {
        log.info("IN OrdersServiceImpl getById {}", id);
        return ordersRepository.getOne(id);
    }

    @Override
    public void save(Orders order) {
        log.info("IN OrdersServiceImpl save {}", order);
       ordersRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        log.info("IN OrdersServiceImpl delete {}", id);
        ordersRepository.deleteById(id);
    }

    @Override
    public List<Orders> getAll() {
        log.info("IN OrdersServiceImpl getAll");
        return ordersRepository.findAll();
    }
}
