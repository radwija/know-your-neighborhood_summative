package com.radwija.springsocial.service;

import com.radwija.springsocial.model.User;
import com.radwija.springsocial.payload.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User saveUser(SignUpRequest signUpRequest);
}
