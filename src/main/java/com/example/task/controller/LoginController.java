package com.example.task.controller;
import com.example.task.Repository.UserRepository;
import com.example.task.request.UserLoginRequest;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute UserLoginRequest user) {
        model.addAttribute("userLogin", user);
        return "/login";
    }
    @GetMapping("/user")
    public String users(Model model, @Valid @ModelAttribute("userLogin") UserLoginRequest user) {
        model.addAttribute("userLogin", user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("userList", userRepository.findAll());
        return "/user";
    }

}
