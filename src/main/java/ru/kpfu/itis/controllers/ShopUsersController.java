package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({"current","thisUser"})
public class ShopUsersController {
    @Autowired
    UserService userService;

    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String user(
            UsernamePasswordAuthenticationToken currentUser,
            @RequestParam(value = "id",required = true, defaultValue = "1")
            Integer id,
            Model model) {
        
        User u = (User)currentUser.getPrincipal();
        User uId = userService.findByName(u.getName());
        model.addAttribute("thisUser", uId);
        
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("current", (User) currentUser.getPrincipal());
        
        return "user";
    }
       
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String userDelete(
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        userService.deleteUser(id);
        
        return "redirect:/user/show";
    }
    
    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
    public String userEdit(
            @PathVariable(value = "id")
            Integer id,
            ModelMap model) {
        User u = userService.find(id);
        u.setId(id);
        model.addAttribute("user", u);
        
        return "editUser";
    }
    
    @RequestMapping(value = "/userEditSave", method = RequestMethod.POST)
    public String userEditSave(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email, 
            @RequestParam(value = "password") String password,
            @RequestParam(value = "isAdmin", required = false, defaultValue = "false") Boolean isAdmin,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("country") String country,
            Model model) {
         User u = new User(name, email, password, isAdmin, address, phone, country);
         u.setId(id);

        userService.edit(u);
        return "redirect:/user/show";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model){
        return "addUser";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "isAdmin", required = false, defaultValue = "0") Boolean isAdmin,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("country") String country,
            Model model){
        User user=new User(name, email, password, isAdmin, address, phone, country);
        userService.Add(user);
        return "redirect:/user/show";
    }
    
    @RequestMapping(value = "/userSave", method = RequestMethod.GET)
    public String userSave(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email, 
            @RequestParam(value = "password") String password,
            @RequestParam(value = "isAdmin", required = false, defaultValue = "false") Boolean isAdmin,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("country") String country,
            Model model){
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("password", password);       
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("address", address);
        model.addAttribute("phone", phone);
        model.addAttribute("country", country);
        User u = new User(name, email, password, isAdmin, address, phone, country);
        userService.edit(u);
        return "redirect:/user/show";
    }
    
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        
        return "login";
    }
        
}
