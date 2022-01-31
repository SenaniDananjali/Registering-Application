package com.example.API.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param name
     * @param email
     * @param password
     * @return
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email, @RequestParam String password) {
        Optional<User> newUser = userRepository.findById(email);
        if (newUser.isEmpty()){
            User n = new User();
            n.setName(name);
            n.setEmail(email);
            n.setPassword(password);
            userRepository.save(n);
            return name+" Successfully registered with "+ email;
        }else{
            return "email is already in use";
        }
    }

    /**
     *
     * @return
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     *
     * @param email
     * @return
     */
    @GetMapping(path = "/getUser")
    @ResponseBody
    public Optional<User> getUser(@RequestParam String email) {
        return userRepository.findById(email);
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    @PostMapping(path = "/login")
    public @ResponseBody
    String login(@RequestParam String email
            , @RequestParam String password) {

        Optional<User> user =  userRepository.findById(email);
        if (user.isEmpty()) {
            return "Login Failed";
        }
        if (user.get().getPassword().equals(password)) {
            return "Login Success";
        } else {
            return "Login Failed";
        }
    }

}