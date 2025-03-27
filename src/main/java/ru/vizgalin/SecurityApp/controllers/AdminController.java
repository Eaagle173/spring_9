//package ru.vizgalin.SecurityApp.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.vizgalin.SecurityApp.models.User;
//import ru.vizgalin.SecurityApp.services.RoleServiceImpl;
//import ru.vizgalin.SecurityApp.services.UserServiceImpl;
//
//import java.security.Principal;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@PreAuthorize("hasRole('ADMIN')")
//public class AdminController {
//    private final UserServiceImpl userService;
//    private final RoleServiceImpl roleService;
//
//    @Autowired
//    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping
//    public String showUser(Principal principal, Model model) {
//        User user = userService.findByUserName(principal.getName());
//        model.addAttribute("users", user);
//        return "user";
//    }
//
//    @GetMapping
//    public String showAllUsers(Model model, Principal principal) {
//        User currentUser = userService.findByUserName(principal.getName());
//
//        String formattedRoles = currentUser.getRoles().stream()
//                .map(role -> role.toString().replace("ROLE_", ""))
//                .collect(Collectors.joining(" "));
//
//        List<User> users = userService.findAllUsers().stream()
//                .peek(user -> {
//                    String rolesFormatted = user.getRoles().stream()
//                            .map(role -> role.toString().replace("ROLE_", ""))
//                            .collect(Collectors.joining(" "));
//                    user.setFormattedRoles(rolesFormatted);
//                })
//                .collect(Collectors.toList());
//
//        model.addAttribute("formattedRoles", formattedRoles);
//        model.addAttribute("username", principal.getName());
//        model.addAttribute("allUsers", users);
//        return "admin";
//    }
//
//    @GetMapping("add-new-user")
//    public String addNewUser(Model model) {
//        model.addAttribute("save", new User());
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "user-info";
//    }
//
//    @PostMapping("/save-user")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/update-user")
//    public String updateUser(@RequestParam(value = "email") String email, Model model) {
//        model.addAttribute("updateUserId", userService.loadUserByUsername(email));
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "update";
//    }
//
//    @PostMapping("update-user-by-id")
//    public String updateUserById(@ModelAttribute("updateUserId") User user,
//                                 @RequestParam(value = "password", required = false) String newPassword,
//                                 @RequestParam(value = "firstName") String firstName,
//                                 @RequestParam(value = "lastName") String lastName,
//                                 @RequestParam(value = "age") int age, Principal principal) {
//        User updatedUser = userService.findById(user.getId());
//        userService.updateUser(user, newPassword);
//        if (principal.getName().equals(updatedUser.getUsername())) {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    updatedUser, updatedUser.getPassword(), updatedUser.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        if (userService.isAdmin(updatedUser) || !principal.getName().equals(updatedUser.getUsername())) {
//            return "redirect:/admin";
//        } else if (userService.hasRole(updatedUser, "ROLE_USER") && principal.getName().equals(updatedUser.getUsername())) {
//            return "redirect:/user";
//        } else {
//            return "redirect:/login";
//        }
//    }
//
//    @PostMapping("delete-user")
//    public String deleteUser(@RequestParam("id") Long id, Principal principal) {
//        if (principal.getName().equals(userService.findById(id).getUsername())) {
//            userService.deleteUser(id);
//            return "redirect:/login";
//        }
//        if (id == null || userService.findById(id) == null) {
//            throw new IllegalArgumentException("User not found");
//        }
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//}
//
//(@RequestParam(value = "firstName") String firstName,
//@RequestParam(value = "lastName") String lastName,
//@RequestParam(value = "age") int age,
//@RequestParam(value = "email") String email,
//        @RequestParam(value = "password") String password, Model model)
//
