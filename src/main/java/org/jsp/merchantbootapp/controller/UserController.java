package org.jsp.merchantbootapp.controller;

import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.model.User;
import org.jsp.merchantbootapp.model.User;
import org.jsp.merchantbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController 
{
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseStructure<User> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseStructure<List<User>> findAll() {
        return userService.findAll();
    }

    @PostMapping("/verify-by-phone")
    public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam long phone,
                                                                      @RequestParam String password) {
        return userService.verifyUser(phone, password);
    }
}
