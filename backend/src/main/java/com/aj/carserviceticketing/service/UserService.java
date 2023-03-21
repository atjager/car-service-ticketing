package com.aj.carserviceticketing.service;

import com.aj.carserviceticketing.domain.users.AppUser;
import com.aj.carserviceticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AppUser create(AppUser appUser) {
        AppUser userToSave = AppUser.builder()
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .password(new BCryptPasswordEncoder().encode(appUser.getPassword()))
                .role(appUser.getRole())
                .name(appUser.getName())
                .build();
        return userRepository.save(userToSave);
    }

    public List<AppUser> findAllUsers() {
        return userRepository.findAll();
    }

    public AppUser updateAppUser(AppUser appUser) {
        System.out.println(appUser);
        AppUser appUserOld = userRepository.findById(appUser.getId()).get();
        appUser.setId(appUserOld.getId());
        appUser.setCreated(appUserOld.getCreated());
        userRepository.save(appUser);
        return appUser;
    }
}
