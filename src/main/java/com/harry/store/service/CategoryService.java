package com.harry.store.service;

import com.harry.store.model.Category;
import com.harry.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category){
        categoryRepository.save(category);
    }

    public void removeCategoryById(long id){
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }
}
