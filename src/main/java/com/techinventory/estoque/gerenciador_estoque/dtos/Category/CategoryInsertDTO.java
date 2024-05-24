package com.techinventory.estoque.gerenciador_estoque.dtos.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryInsertDTO(@NotBlank String name) {
}
