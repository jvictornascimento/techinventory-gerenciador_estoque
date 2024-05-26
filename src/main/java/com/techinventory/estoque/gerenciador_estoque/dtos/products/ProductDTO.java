package com.techinventory.estoque.gerenciador_estoque.dtos.products;

import com.techinventory.estoque.gerenciador_estoque.model.Category;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public record ProductDTO (UUID id, String description,int quantity,Double price,Category category){
}
