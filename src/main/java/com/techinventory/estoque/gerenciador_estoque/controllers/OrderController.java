package com.techinventory.estoque.gerenciador_estoque.controllers;

import com.techinventory.estoque.gerenciador_estoque.model.Order;
import com.techinventory.estoque.gerenciador_estoque.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOneOrder(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.findById(id));
    }
        }