package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.dtos.category.CategoryDTO;
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

    public Category save(CategoryDTO categoryDTO){
        var category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        return categoryRepository.save(category);
    }

    public Category edit(CategoryDTO categoryDTO,UUID id){
        var data = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException :: new);
        BeanUtils.copyProperties(categoryDTO,data);
        return categoryRepository.save(data);
    }

    public String delete(UUID id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return "Category deleted sucessfully";
        }
       throw new CategoryNotFoundException();

    }

}
