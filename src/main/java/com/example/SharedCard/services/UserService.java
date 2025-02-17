package com.example.SharedCard.services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.SharedCard.entities.UserEntity;
import com.example.SharedCard.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserService {
    private static UserRepository userRepository = null;


    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}