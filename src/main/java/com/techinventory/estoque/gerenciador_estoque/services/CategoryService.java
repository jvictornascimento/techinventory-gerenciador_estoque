package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.dtos.Category.CategoryDTO;
import com.techinventory.estoque.gerenciador_estoque.dtos.Category.CategoryInsertDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Category;
import com.techinventory.estoque.gerenciador_estoque.repositories.CategoryRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.CategoryNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(UUID id){
        return categoryRepository.findById(id).
                orElseThrow(CategoryNotFoundException :: new);
    }

    public Category save(CategoryInsertDTO categoryInsertDTO){
        var category = new Category();
        BeanUtils.copyProperties(categoryInsertDTO,category);
        return categoryRepository.save(category);
    }

}
