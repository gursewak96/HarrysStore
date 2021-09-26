package com.harry.store.controller;

import com.harry.store.model.CartItem;
import com.harry.store.model.Product;
import com.harry.store.model.StoreUserDetails;
import com.harry.store.service.CartItemService;
import com.harry.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getCart(Model model, @AuthenticationPrincipal StoreUserDetails user){
        List<CartItem> cartItems = cartItemService.listCartItem(user.getUser());
        model.addAttribute("cart",cartItems);
        return "cart";
    }

    @GetMapping("/add/{productId}")
    public String addItemToCart(@PathVariable("productId") Long id,
                                @AuthenticationPrincipal StoreUserDetails user)
    {
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()){
            cartItemService.addProduct(product.get(),user.getUser());
        }

        return "redirect:/shop/viewproduct/"+id;
    }
}
