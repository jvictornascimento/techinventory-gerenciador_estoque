package com.techinventory.estoque.gerenciador_estoque.dtos.client;

import jakarta.validation.constraints.NotBlank;

public record ClientUpdateDTO( String name,  String email) {
}
