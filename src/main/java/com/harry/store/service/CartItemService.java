package com.harry.store.service;

import com.harry.store.model.CartItem;
import com.harry.store.model.Product;
import com.harry.store.model.User;
import com.harry.store.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public void removeCartItemById(Long id, User user) {
        //check if the item exists and belongs to current logged in user
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        CartItem cartItem = cartItemOptional
                .orElseThrow(()->new RuntimeException("CartItem not found with id: "+id));

        if(user.getEmail().equals(cartItem.getUser().getEmail()))
            cartItemRepository.delete(cartItem);
        else
            throw new RuntimeException("You dont seems to belong here");

    }
}
