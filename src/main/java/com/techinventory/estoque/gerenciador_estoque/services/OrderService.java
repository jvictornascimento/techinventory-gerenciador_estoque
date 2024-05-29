package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.controllers.ClientController;
import com.techinventory.estoque.gerenciador_estoque.controllers.OrderController;
import com.techinventory.estoque.gerenciador_estoque.dtos.order.OrderDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Order;
import com.techinventory.estoque.gerenciador_estoque.repositories.OrderRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.OrderNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        var orders =  orderRepository.findAll();
        for (Order order : orders){
            UUID id = order.getId();
            UUID idClient = order.getClient().getUuid();
            order.add(linkTo(methodOn(OrderController.class).getOneOrder(id)).withSelfRel());
            order.add(linkTo(methodOn(ClientController.class).getOneClient(idClient)).withRel("Client"));
            order.getClient().add(linkTo(methodOn(ClientController.class).getOneClient(idClient)).withSelfRel());

        }
        return orders;
    }

    public Order findById(UUID id){
        var order =  orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new)
                .add(linkTo(methodOn(OrderController.class).getAllOrder()).withRel("Order List"));
        order.add(linkTo(methodOn(ClientController.class).getOneClient(order.getClient().getUuid())).withRel("Client"));
        order.getClient().add(linkTo(methodOn(ClientController.class).getOneClient(order.getClient().getUuid())).withSelfRel());
        return order;
    }

    public Order save(OrderDTO orderDTO){
        var order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        var data = LocalDateTime.now();
        order.setDate(data);
        return orderRepository.save(order);
    }

}
