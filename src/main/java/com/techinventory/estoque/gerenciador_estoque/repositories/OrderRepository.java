package com.techinventory.estoque.gerenciador_estoque.repositories;

import com.techinventory.estoque.gerenciador_estoque.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
