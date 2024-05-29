package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.controllers.ClientController;
import com.techinventory.estoque.gerenciador_estoque.dtos.client.ClientDTO;
import com.techinventory.estoque.gerenciador_estoque.dtos.client.ClientUpdateDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Client;
import com.techinventory.estoque.gerenciador_estoque.repositories.ClientRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.ClientNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public List<Client> findAll(){
        var clients = clientRepository.findAll();
        for(Client client : clients){
            UUID id = client.getUuid();
            client.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
        }
        return clients;

    }

    public Client findById(UUID id){
        return clientRepository.findById(id).
                orElseThrow(ClientNotFoundException :: new)
                .add(linkTo(methodOn(ClientController.class).getAllClient()).withRel("Client list"));
    }

    public Client save (ClientDTO clientDTO){
        var client = new Client();
        BeanUtils.copyProperties(clientDTO,client);
        return clientRepository.save(client);
    }

    public Client edit(ClientUpdateDTO clientUpdateDTO, UUID id){
        return updateData(clientUpdateDTO,id);
    }

    public String delete(UUID id){
        if (!clientRepository.existsById(id)){
            throw new ClientNotFoundException();
        }
        clientRepository.deleteById(id);
        return "Client deleted sucessfully";
    }


    private Client updateData(ClientUpdateDTO clientUpdateDTO, UUID uuid){
        var data = clientRepository.findById(uuid)
                .orElseThrow(ClientNotFoundException :: new);
        data.setName((clientUpdateDTO.name()!=null)?clientUpdateDTO.name():data.getName());
        data.setEmail((clientUpdateDTO.email()!=null)?clientUpdateDTO.email(): data.getEmail());
        return clientRepository.save(data);
    }


}
