package com.techinventory.estoque.gerenciador_estoque.dtos.products;

import com.techinventory.estoque.gerenciador_estoque.model.Category;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductDTO (@NotBlank String description,
                          Integer quantity,
                          @NotNull Double price,
                          Category category){
}
