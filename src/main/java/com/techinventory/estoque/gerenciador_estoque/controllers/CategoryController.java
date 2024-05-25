package com.techinventory.estoque.gerenciador_estoque.controllers;

import com.techinventory.estoque.gerenciador_estoque.dtos.Category.CategoryEditDTO;
import com.techinventory.estoque.gerenciador_estoque.dtos.Category.CategoryDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Category;
import com.techinventory.estoque.gerenciador_estoque.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getOneCategory(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findById(id));

    }
    @PostMapping
    public ResponseEntity<Category>saveCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.save(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid CategoryDTO categoryDTO, @PathVariable UUID id){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoryService.edit(categoryDTO,id));
    }

}
