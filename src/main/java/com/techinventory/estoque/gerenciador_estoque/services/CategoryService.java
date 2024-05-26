package com.techinventory.estoque.gerenciador_estoque.services;

import com.techinventory.estoque.gerenciador_estoque.controllers.CategoryController;
import com.techinventory.estoque.gerenciador_estoque.dtos.category.CategoryDTO;
import com.techinventory.estoque.gerenciador_estoque.model.Category;
import com.techinventory.estoque.gerenciador_estoque.repositories.CategoryRepository;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.CategoryNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        var categories = categoryRepository.findAll();
        for (Category category : categories){
            UUID id = category.getId();
            category.add(linkTo(methodOn(CategoryController.class).getOneCategory(id)).withSelfRel());
        }
        return categories;
    }

    public Category findById(UUID id){
        var category = categoryRepository.findById(id).
                orElseThrow(CategoryNotFoundException :: new);
        category.add((linkTo(methodOn(CategoryController.class).getAllCategories()).withRel("Categories List")));
        return category ;
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
