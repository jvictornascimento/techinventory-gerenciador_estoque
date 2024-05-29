package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.model.Order;
import com.techinventory.estoque.gerenciador_estoque.repositories.OrderRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(UUID id){
        return orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }
}
