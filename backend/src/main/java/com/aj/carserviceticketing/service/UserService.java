package com.aj.carserviceticketing.service;

import com.aj.carserviceticketing.domain.users.AppUser;
import com.aj.carserviceticketing.exception.ItemAlreadyExistsException;
import com.aj.carserviceticketing.exception.ItemNotFoundException;
import com.aj.carserviceticketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final EmailSenderService emailSenderService;

    public AppUser create(AppUser appUser) throws ItemAlreadyExistsException {
        if (userRepository.findByUsername(appUser.getUsername()).isPresent() || userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new ItemAlreadyExistsException("", "");
        }
        AppUser userToSave = AppUser.builder()
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .role(appUser.getRole())
                .name(appUser.getName())
                .verified(false)
                .build();
        AppUser savedUser = userRepository.save(userToSave);
        emailSenderService.sendVerifyRequest(savedUser.getEmail(), savedUser.getId().toString());
        return savedUser;
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser update(AppUser appUser) {
        AppUser appUserOld = userRepository.findById(appUser.getId()).get();
        appUser.setId(appUserOld.getId());
        appUser.setCreated(appUserOld.getCreated());
        return userRepository.save(appUser);
    }

    public void delete(String id) {
        userRepository.delete(userRepository.findById(UUID.fromString(id)).get());
    }

    public void verifyUser(String id) throws ItemNotFoundException, ItemAlreadyExistsException {
        if (userRepository.findById(UUID.fromString(id)).isPresent()) {
            AppUser appUser = userRepository.findById(UUID.fromString(id)).get();
            if(appUser.getVerified()) {
                throw new ItemAlreadyExistsException("", "");
            }
            appUser.setVerified(true);
            userRepository.save(appUser);
        } else {
            throw new ItemNotFoundException("", "");
        }
    }
}
