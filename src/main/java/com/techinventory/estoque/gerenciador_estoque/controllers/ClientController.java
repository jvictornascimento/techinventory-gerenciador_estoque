package com.techinventory.estoque.gerenciador_estoque.controllers;

import com.techinventory.estoque.gerenciador_estoque.dtos.client.ClientDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Client;
import com.techinventory.estoque.gerenciador_estoque.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/client")

public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClient(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOneClient(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientDTO clientDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.save(clientDTO));
    }

}
