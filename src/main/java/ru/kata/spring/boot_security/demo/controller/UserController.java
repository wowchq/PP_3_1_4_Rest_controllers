package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String show(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("allUsers", users);
        return "users";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam int age,
            Model model
    ) {
        User user = new User(name, surname, age);
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String update(@RequestParam long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/update")
    public String saveUpdate(@RequestParam long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam int age)
    {
        User user = userService.findById(id);
        user.setUsername(name);
        user.setSurname(surname);
        user.setAge(age);
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
