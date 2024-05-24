package com.techinventory.estoque.gerenciador_estoque.repositories;

import com.techinventory.estoque.gerenciador_estoque.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
