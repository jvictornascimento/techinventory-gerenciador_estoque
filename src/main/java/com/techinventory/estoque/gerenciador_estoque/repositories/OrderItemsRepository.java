package com.techinventory.estoque.gerenciador_estoque.repositories;

import com.techinventory.estoque.gerenciador_estoque.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemsRepository extends JpaRepository<OrderItems, UUID> {
}
