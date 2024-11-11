package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/")
//    public String allUsers(Model model) {
//        model.addAttribute("allUsersList", userService.getAll());
//        return "allUsers";
//    }

    @GetMapping("")
    public String userInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user";
    }

//    @GetMapping("/addUser")
//    public String addUser(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "addUser";
//    }
//
//    @PostMapping("/saveUser")
//    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "addUser";
//        } else {
//            userService.addUser(user);
//            return "redirect:/";
//        }
//    }
//
//    @GetMapping("/updateUser")
//    public String updateUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "updateUser";
//    }
//
//    @PostMapping("/saveUserAfterUpdate")
//    public String saveUserAfterUpdate(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "updateUser";
//        } else {
//            userService.updateUser(user);
//            return "redirect:/";
//        }
//    }
//
//    @GetMapping("/deleteUser")
//    public String deleteUser(@ModelAttribute("id") Integer id) {
//        userService.deleteUserById(id);
//        return "redirect:/";
//    }
}
