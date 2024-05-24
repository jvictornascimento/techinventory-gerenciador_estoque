package com.techinventory.estoque.gerenciador_estoque.repositories;

import com.techinventory.estoque.gerenciador_estoque.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
