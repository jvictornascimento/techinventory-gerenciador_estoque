package com.techinventory.estoque.gerenciador_estoque.dtos.products;

import com.techinventory.estoque.gerenciador_estoque.model.Category;

public record ProductUpdateDTO(String description, Integer quantity, Double price, Category category){
}
