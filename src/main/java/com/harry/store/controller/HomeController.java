package com.harry.store.controller;

import com.harry.store.dto.ProductDto;
import com.harry.store.exception.ProductNotFoundException;
import com.harry.store.model.Product;
import com.harry.store.model.User;
import com.harry.store.service.CategoryService;
import com.harry.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String getHomePage(){
        return "index";
    }

    @GetMapping({"/shop","/"})
    public String getShop(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("products",productService.getAllProducts());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String getProductsByCategory(@PathVariable long id,Model model){
         model.addAttribute("products",productService.getAllProductsByCategory(id));
         model.addAttribute("categories",categoryService.getAllCategories());
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String getProductById(@PathVariable long id,Model model) throws ProductNotFoundException {
        Optional<Product> productOpt = productService.getProductById(id);
        Product product = productOpt.orElseThrow(()->new ProductNotFoundException("Product not found with id: "+id));
        model.addAttribute("product",product);
        return "viewProduct";
    }

}
