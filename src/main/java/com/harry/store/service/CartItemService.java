package com.harry.store.service;

import com.harry.store.model.CartItem;
import com.harry.store.model.Product;
import com.harry.store.model.User;
import com.harry.store.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> listCartItem(User user){
        return cartItemRepository.findByUser(user);
    }

    public void addProduct(Product product,User user) {
        //check if the product is already exist with given user
        Optional<CartItem> cartItem = cartItemRepository.findByUserAndProduct(user, product);

        CartItem item = cartItem.orElseGet(()-> new CartItem());

        item.setProduct(product);
        item.setUser(user);
        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
    }
}
