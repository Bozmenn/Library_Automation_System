package com.berkozmen.library_automation_system.controller;


import com.berkozmen.library_automation_system.model.dto.UserDataDTO;
import com.berkozmen.library_automation_system.model.dto.UserLoginDTO;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
@Transactional
@Api(value = "User Api documentation")
public class UserController {

    @Autowired
    private UserService userService;

    /*@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")*/
    @GetMapping
    @ApiOperation(value = "User list method")
    public List<User> getAllUsers() {
        return userService.getAll();
    }
    @ApiOperation(value = "User Sign in  method")
    @PostMapping("/signin")
    public String login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return userService.signin(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
    @ApiOperation(value = "User Sign up  method")
    @PostMapping("/signup")
    public String signup(@RequestBody @Valid UserDataDTO userDataDTO) {
        User user = new User();
        user.setUsername(userDataDTO.getUsername());
        user.setEmail(userDataDTO.getEmail());
        user.setPassword(userDataDTO.getPassword());
        return userService.signup(user,false);
    }

    @ApiOperation(value = "User delete  method for admin")
    @DeleteMapping(value = "/delete/{username}")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }


}
