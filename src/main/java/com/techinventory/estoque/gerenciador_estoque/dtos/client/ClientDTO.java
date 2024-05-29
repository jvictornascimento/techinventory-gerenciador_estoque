package com.techinventory.estoque.gerenciador_estoque.dtos.client;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(@NotBlank String name, @NotBlank String email) {
}
