package com.aj.carserviceticketing.service;

import com.aj.carserviceticketing.domain.users.AppUser;
import com.aj.carserviceticketing.exception.ItemAlreadyExistsException;
import com.aj.carserviceticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public AppUser create(AppUser appUser) throws ItemAlreadyExistsException {
        if (userRepository.findByUsername(appUser.getUsername()).isPresent() || userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new ItemAlreadyExistsException("", "");
        }
        AppUser userToSave = AppUser.builder()
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .role(appUser.getRole())
                .name(appUser.getName())
                .build();
        return userRepository.save(userToSave);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser update(AppUser appUser) {
        //System.out.println(appUser);
        AppUser appUserOld = userRepository.findById(appUser.getId()).get();
        appUser.setId(appUserOld.getId());
        appUser.setCreated(appUserOld.getCreated());
        return userRepository.save(appUser);
    }

    public void delete(String id) {
        userRepository.delete(userRepository.findById(UUID.fromString(id)).get());
    }
}
