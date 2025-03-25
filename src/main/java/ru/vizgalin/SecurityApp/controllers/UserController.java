package ru.vizgalin.SecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vizgalin.SecurityApp.models.User;
import ru.vizgalin.SecurityApp.services.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userServiceImp;

    @Autowired
    public UserController(UserServiceImpl userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    public String showUser(Principal principal, Model model) {
        User user = userServiceImp.findByUserName(principal.getName());
        model.addAttribute("users", user);
        return "user";
    }
}
