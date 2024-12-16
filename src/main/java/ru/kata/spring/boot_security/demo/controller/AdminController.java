package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String show(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("allUsers", users);
        return "admin";
    }


    @PostMapping("/add")
    public String add(
            @ModelAttribute("user") User user,
            @RequestParam("roleIds") List<Long> roleIds,
            Model model
    ) {
        List<Role> roles = roleService.getRolesByIds(roleIds); // Получаем роли по их ID
        user.setRoles(roles); // Устанавливаем роли в объект пользователя
        userService.add(user); // Сохраняем пользователя
        return "redirect:/admin"; // Перенаправление на страницу администратора
    }

//    @PostMapping("/add")
//    public String add(
//            @RequestParam String name,
//            @RequestParam String surname,
//            @RequestParam int age,
//            @RequestParam String password,
//            @RequestParam List<Long> roleIds
//    ) {
//        List<Role> roles = roleService.getRolesByIds(roleIds);
//        System.out.println(roles.size());
//        User user = new User(name, surname, age, password, roles);
//        userService.add(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("/update")
//    public String update(@RequestParam long id, Model model) {
//        User user = userService.findById(id);
//        model.addAttribute("user", user);
//        return "updateUser";
//    }
//
//    @PostMapping("/update")
//    public String saveUpdate(@RequestParam long id,
//                             @RequestParam String name,
//                             @RequestParam String surname,
//                             @RequestParam int age)
//    {
//        User user = userService.findById(id);
//        user.setUsername(name);
//        user.setSurname(surname);
//        user.setAge(age);
//        userService.update(user);
//        return "redirect:/users";
//    }
//
//    @PostMapping("/delete")
//    public String delete(@RequestParam long id) {
//        userService.delete(id);
//        return "redirect:/users";
//    }

}
