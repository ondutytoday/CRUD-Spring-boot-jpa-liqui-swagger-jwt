package org.vasileva.crud.rest;

import io.swagger.annotations.*;
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

@Api(value = "CRUD Application", tags = {"Orders RestController"})
@RestController
@RequestMapping("/orders/")
public class OrdersRestController {

    private final OrdersService ordersService;
    private final OrdersMapper ordersMapper;

    @Autowired
    public OrdersRestController(OrdersService ordersService, OrdersMapper ordersMapper) {
        this.ordersService = ordersService;
        this.ordersMapper = ordersMapper;
    }

    @ApiOperation(value = "View an order selected by id", response = OrdersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> getOrder(@ApiParam(value = "ID of an order")
                                              @PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Orders order = ordersService.getById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersMapper.toOrdersDto(order), HttpStatus.OK);
    }

    @ApiOperation(value = "Add an order", response = OrdersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to add the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to add is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> saveOrder(@ApiParam(value = "A JSON value representing an order.")
                                               @RequestBody @Valid OrdersDto ordersDto) {
        HttpHeaders headers = new HttpHeaders();
        if (ordersDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Orders order = ordersMapper.toOrders(ordersDto);
        ordersService.save(order);
        return new ResponseEntity<>(ordersMapper.toOrdersDto(order), headers, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Update an order", response = OrdersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The resource was successfully updated"),
            @ApiResponse(code = 401, message = "You are not authorized to update the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to update is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found")
    })
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> updateOrder(@ApiParam(value = "A JSON value representing an order.")
                                                     @RequestBody @Valid OrdersDto ordersDetailsDto,
                                                 @ApiParam(value = "ID of an order you want to update")
                                                 @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (ordersDetailsDto == null || id == null) {
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

    @ApiOperation(value = "Delete an order")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The resource was successfully deleted"),
            @ApiResponse(code = 401, message = "You are not authorized to delete the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to delete is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found")
    })
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersDto> deleteOrder(@ApiParam(value = "ID of an order you want to delete")
                                                     @PathVariable("id") Long id) {
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

    @ApiOperation(value = "View an list of available orders", response = OrdersDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The resource was successfully retrieved"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdersDto>> getAllOrders() {
        List<Orders> orders = this.ordersService.getAll();

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersMapper.toListOrdersDto(orders), HttpStatus.OK);
    }
}
