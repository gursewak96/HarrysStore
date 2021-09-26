package com.harry.store.controller;

import com.harry.store.dto.UserDto;
import com.harry.store.exception.UsernameAlreadyExistException;
import com.harry.store.model.User;
import com.harry.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegisterPage(Model model){
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user")UserDto userDto) throws UsernameAlreadyExistException {
          userService.register(userDto);

        return "redirect:/login";
    }
}
