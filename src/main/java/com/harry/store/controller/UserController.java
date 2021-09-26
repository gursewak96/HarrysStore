package com.harry.store.controller;

import com.harry.store.dto.UserDto;
import com.harry.store.exception.UsernameAlreadyExistException;
import com.harry.store.model.User;
import com.harry.store.repository.UserRepository;
import com.harry.store.service.DefaultUserService;
import com.harry.store.service.RoleService;
import com.harry.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private DefaultUserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("roles",roleService.getAllRoles());
        return "addUser";
    }

    @PostMapping("/add")
    public String saveUser(@ModelAttribute("user") UserDto userDto) throws UsernameAlreadyExistException {
        userService.register(userDto);

        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

}
