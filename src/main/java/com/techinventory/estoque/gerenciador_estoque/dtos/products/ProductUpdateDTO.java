package com.techinventory.estoque.gerenciador_estoque.dtos.products;

import com.techinventory.estoque.gerenciador_estoque.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductUpdateDTO(String description, int quantity, Double price, Category category){
}
