package com.radwija.knowyourneighborhood.service.impl;

import com.radwija.knowyourneighborhood.exception.BadRequestException;
import com.radwija.knowyourneighborhood.model.AuthProvider;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.payload.SignUpRequest;
import com.radwija.knowyourneighborhood.repository.UserRepository;
import com.radwija.knowyourneighborhood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        else if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Username address already in use.");
        }
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
