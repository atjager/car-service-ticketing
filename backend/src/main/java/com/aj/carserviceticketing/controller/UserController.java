package com.aj.carserviceticketing.controller;

import com.aj.carserviceticketing.domain.users.AppUser;
import com.aj.carserviceticketing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/")
public class UserController {

    private final UserService userService;

    @PostMapping("create")
    private AppUser createUser(@RequestBody AppUser appUser) {
        System.out.println(appUser);
        return userService.create(appUser);
    }

    @GetMapping("read")
    private List<AppUser> findAllUser() {
        return userService.findAllUsers();
    }

    @PutMapping("update")
    private AppUser updateUser(@RequestBody AppUser appUser) {
        return userService.updateAppUser(appUser);
    }
}
