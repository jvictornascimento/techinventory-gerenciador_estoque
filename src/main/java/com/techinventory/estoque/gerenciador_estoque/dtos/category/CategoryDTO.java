package com.techinventory.estoque.gerenciador_estoque.dtos.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(@NotBlank String name) {
}
