package com.example.task.controller;
import com.example.task.Entity.User;
import com.example.task.Repository.UserRepository;
import com.example.task.request.UserRequest;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView("user/editUser");
        modelAndView.addObject("userEdit", userService.findById(id));
        return modelAndView;
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("userEdit") UserRequest user) {
        User service = userService.findById(id);
        service.setUsername(user.getUserName());
        service.setPassword(passwordEncoder.encode(user.getPassword()));
        service.setEmail(user.getEmail());
        service.setConfirmPassword(user.getConfirmPassword());
        userRepository.save(service);
        return "redirect:/user/list";
    }
}
