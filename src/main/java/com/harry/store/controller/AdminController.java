package com.harry.store.controller;

import com.harry.store.dto.ProductDto;
import com.harry.store.model.Category;
import com.harry.store.model.Product;
import com.harry.store.service.CategoryService;
import com.harry.store.service.ProductService;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Value("${storage.image.product.path}")
    private String uploadDir ;

    @GetMapping
    public String getAdminHome(){
        return "adminHome";
    }

    @GetMapping("/categories")
    public String getCategories(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return "categories";
    }

    @GetMapping("/categories/add")
    public String addCategoryPage(Model model){
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping("/categories/add")
    public String saveCategory(@ModelAttribute("category") Category category){
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    //request for deleting a category
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);

        if(category.isPresent()) {
            model.addAttribute("category", category);
            return "categoriesAdd";
        }else
            return "404";
    }


    //product Section
    @GetMapping("/products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/add")
    public String getAddProductPage(Model model){
        model.addAttribute("productDTO",new ProductDto());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String saveProduct(@Valid @ModelAttribute("productDTO")ProductDto productDto, BindingResult br,
                              @RequestParam("productImage")MultipartFile file,
                              @RequestParam("imgName")String imgName) throws IOException {

        if(br.hasErrors()) return "productsAdd";
        Product product  = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        Optional<Category> category = categoryService.getCategoryById(productDto.getCategoryId());
        product.setCategory(category.get());
        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        product.setDescription(productDto.getDescription());
        String imgNameUUID;
        if(!file.isEmpty()){
            imgNameUUID = file.getOriginalFilename();
            Path pathAndFilename = Paths.get(uploadDir, imgNameUUID);
            System.out.println(pathAndFilename.toFile().getAbsolutePath());
            Files.write(pathAndFilename,file.getBytes());
        }else{
            imgNameUUID = imgName;
        }

        product.setImageName(imgNameUUID);

        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        Optional<Product> product = productService.getProductById(id);

        if(!product.isPresent()) return "404";

        Path path = Paths.get(uploadDir,product.get().getImageName());
        try{
            Files.deleteIfExists(path);
        }catch(Exception e){

        }

        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model){
        Optional<Product> product = productService.getProductById(id);
        if(!product.isPresent()) return "404";

        ProductDto productDto = new ProductDto();
        productDto.setId((int)product.get().getId());
        productDto.setDescription(product.get().getDescription());
        productDto.setName(product.get().getName());
        productDto.setPrice(product.get().getPrice());
        productDto.setWeight(product.get().getWeight());
        productDto.setImageName(product.get().getImageName());
        productDto.setCategoryId(product.get().getCategory().getId());
        productDto.setCategoryName(product.get().getCategory().getName());

        model.addAttribute("productDTO",productDto);
        model.addAttribute("categories",categoryService.getAllCategories());

        return "productsAdd";

    }


}
