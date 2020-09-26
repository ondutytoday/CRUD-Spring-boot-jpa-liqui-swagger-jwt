package org.vasileva.crud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vasileva.crud.dto.OrdersDto;
import org.vasileva.crud.entity.Orders;
import org.vasileva.crud.mapper.OrdersMapper;
import org.vasileva.crud.service.OrdersService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders/")
public class OrdersRestController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersMapper ordersMapper;

    @GetMapping(value = "{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> getOrder (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Orders order = ordersService.getById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersMapper.toOrdersDto(order), HttpStatus.OK);
    }

    @PostMapping(value = "",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> saveOrder (@RequestBody @Valid OrdersDto ordersDto) {
        HttpHeaders headers = new HttpHeaders();
        if (ordersDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Orders order = ordersMapper.toOrders(ordersDto);
        ordersService.save(order);
        return new ResponseEntity<>(ordersMapper.toOrdersDto(order), headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> updateOrder (@RequestBody @Valid OrdersDto ordersDetailsDto, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (ordersDetailsDto == null|| id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Orders order = ordersService.getById(id);
        Orders orderDetails = ordersMapper.toOrders(ordersDetailsDto);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setDishesInOrder(orderDetails.getDishesInOrder());
        order.setPaymentMethod(orderDetails.getPaymentMethod());
        order.setStaff(orderDetails.getStaff());
        order.setTimestamp(orderDetails.getTimestamp());

        ordersService.save(order);
        return new ResponseEntity<>(ordersMapper.toOrdersDto(order), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> deleteOrder (@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Orders order = ordersService.getById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<Orders> orders = this.ordersService.getAll();

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersMapper.toListOrdersDto(orders), HttpStatus.OK);
    }
}
