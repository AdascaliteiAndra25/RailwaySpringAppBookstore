package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.BookMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.RegistrationRequest;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("users")
    public String getUsers(Model model) {
        List<UserDto> userDtos = userService.getAllUsers();
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDtos);
        return "users";
    }

    @GetMapping("users/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }


    @GetMapping("users/delete")
    @Secured("ROLE_ADMIN")
    public String displayDeleteUsersForm(Model model) {
        model.addAttribute("title", "Delete Users");
        model.addAttribute("users", userService.getAllUsers());
        return "users/delete";
    }

    @PostMapping("users/delete")
    @Secured("ROLE_ADMIN")
    public String processDeleteUsersForm(@RequestParam(required = false) Integer[] userIds) {
        System.out.println("merge secundele");
        if (userIds != null) {
            for (Integer id : userIds) {

                userService.deleteUserById(id);
            }
        }
        return "redirect:/users"; }

    @GetMapping("users/create")
    @Secured("ROLE_ADMIN")
    public String displayCreateUserForm(Model model) {
        model.addAttribute("title", "Create User");
        model.addAttribute("user", new RegistrationRequest());
        return "users/create";
    }

    @PostMapping("users/create")
    @Secured("ROLE_ADMIN")
    public String processCreateUserForm(@ModelAttribute RegistrationRequest registrationRequest, Model model) {
        if (userService.checkEmail(registrationRequest.getEmailAddress())) {
            model.addAttribute("error", "Email already exists!");
            return "users/create";
        }

        userService.registerUser(registrationRequest);
        return "redirect:/users";
    }

}