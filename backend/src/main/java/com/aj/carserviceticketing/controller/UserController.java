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

    @GetMapping("all")
    private List<AppUser> getAllUser() {
        return userService.findAll();
    }

    @PutMapping("update")
    private AppUser updateUser(@RequestBody AppUser appUser) {
        return userService.update(appUser);
    }

    @DeleteMapping("delete")
    private void deleteUser(@RequestParam String id) {
        userService.delete(id);
    }
}
