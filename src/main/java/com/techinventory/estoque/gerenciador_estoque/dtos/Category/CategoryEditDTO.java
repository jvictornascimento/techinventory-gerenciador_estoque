package com.techinventory.estoque.gerenciador_estoque.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CategoryEditDTO(@NotNull UUID id, @NotBlank String name) {
}
