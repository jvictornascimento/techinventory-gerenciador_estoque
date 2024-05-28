package com.techinventory.estoque.gerenciador_estoque.repositories;

import com.techinventory.estoque.gerenciador_estoque.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
