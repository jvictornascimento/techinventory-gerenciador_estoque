package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.model.Client;
import com.techinventory.estoque.gerenciador_estoque.repositories.ClientRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(UUID id){
        return clientRepository.findById(id).
                orElseThrow(ClientNotFoundException :: new);
    }


}
