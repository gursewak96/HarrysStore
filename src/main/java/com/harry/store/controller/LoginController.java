package com.harry.store.controller;

import com.harry.store.model.StoreUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(@AuthenticationPrincipal StoreUserDetails user){
        if(user == null )
            return "login";
        else
            return "redirect:/";
    }

    @GetMapping("/error")
    public String getErrorPage(){return "error";}
}
