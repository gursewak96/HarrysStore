package com.harry.store.restController;

import com.harry.store.dto.CartItemDto;
import com.harry.store.model.CartItem;
import com.harry.store.model.StoreUserDetails;
import com.harry.store.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartItemRestController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/update/{id}/{qty}")
    public CartItemDto updateCartItem(@PathVariable("id") Long id,
                                      @PathVariable("qty") int qty,
                                      @AuthenticationPrincipal StoreUserDetails user)
    {
        CartItem cartItem = cartItemService.updateCartItemById(id,qty, user.getUser());
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setSubTotal(cartItem.getSubtotal());
        return cartItemDto;
    }

}
