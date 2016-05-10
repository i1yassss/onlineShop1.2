package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public String addUser(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String addUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "isAdmin", required = false, defaultValue = "0") Boolean isAdmin,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("country") String country,
            Model model) {
        User user = new User(name, email, password, isAdmin, address, phone, country);
        userService.Add(user);
        return "redirect:/user/login";
    }
}
