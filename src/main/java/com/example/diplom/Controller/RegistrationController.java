package com.example.diplom.Controller;
import com.example.diplom.domain.User;
import com.example.diplom.domain.Role;
import com.example.diplom.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.soap.SOAPBinding;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(Model model)
    {

        return "login";
    }


    @GetMapping("/registration")
    public String registration() {
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


        return "registration";
    }
}