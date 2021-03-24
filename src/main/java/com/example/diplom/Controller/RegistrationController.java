package com.example.diplom.Controller;
import com.example.diplom.domain.User;
import com.example.diplom.domain.Role;
import com.example.diplom.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login(Model model) {

        if(userRepo.findAll().isEmpty()){
            User user= new User();
            user.setUsername("kobi");
            user.setPassword("1");
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_USER);
            roles.add(Role.ROLE_ADMIN);
            roles.add(Role.ROLE_KOBI);

            user.setRoles(roles);
            user.setActive(true);
            userRepo.save(user);
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model)
    {
        return "login";
    }


    @GetMapping("/registration")
    public String registration( Model model){

    List<User> users = userRepo.findAll();
    model.addAttribute("users",users);

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(User user,String role, Model model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());
///       User userFromDb = new User(username,password,true,Collections.singleton(Role.USER));

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);

        if(role.equals("ROLE_USER"))
            user.setRoles(Collections.singleton(
                    Role.ROLE_USER));

        if(role.equals("ROLE_ADMIN")) {
            Set<Role> r = new HashSet<>();
            r.add(Role.ROLE_USER);
            r.add(Role.ROLE_ADMIN);
            user.setRoles(r);
        }

        if(role.equals("ROLE_KOBI")) {
            Set<Role> r = new HashSet<>();
            r.add(Role.ROLE_USER);
            r.add(Role.ROLE_ADMIN);
            r.add(Role.ROLE_KOBI);
            user.setRoles(r);
        }

//        user.setRoles(Collections.singleton(Role.ROLE_KOBI));
//        user.setRoles(Role.ROLE_USER);
        userRepo.save(user);

        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users",users);

        return "registration";
    }


    @PostMapping("/registration/delete")
    public String userDelete(@RequestParam String idd, Model model) {

            long id = Integer.parseInt(idd);
            User user = userRepo.findById(id);
            if (user != null)
                userRepo.delete(user);

        return "redirect:/registration";
    }
}