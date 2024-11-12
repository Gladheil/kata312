package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String usersInfo(Model model) {
        model.addAttribute("allUsersList", userService.getAll());
        return "allUsers";
    }

    @GetMapping("/formUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/addUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (userService.getUserByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "username.exists", "Username already exists");
            return "addUser";
        }
        if (bindingResult.hasErrors()) {
            return "addUser";
        } else {
            Role role = roleService.getRole("ROLE_USER");
            user.addRole(role);
            userService.addUser(user);
            return "redirect:/admin";
        }
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("id") Integer id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser")
    public String updateUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/saveUserAfterUpdate")
    public String saveUserAfterUpdate(@RequestParam("roles") String roles,
            @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (userService.getUserByUsername(user.getUsername()) != null &&
        userService.getIdByUsername(user.getUsername()) != user.getId()) {
            bindingResult.rejectValue("username", "username.exists", "Username already exists");
            return "updateUser";
        }
        if (bindingResult.hasErrors()) {
            return "updateUser";
        } else {
            System.out.println(roles);
            Set<Role> userRoles = new HashSet<>(roleService.getRolesByNames(Arrays.asList(roles.split(","))));
            user.setRoles(userRoles);
            userService.updateUser(user);
            return "redirect:/admin";
        }
    }

}
