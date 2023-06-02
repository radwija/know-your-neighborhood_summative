package com.radwija.knowyourneighborhood.service;

import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.payload.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User saveUser(SignUpRequest signUpRequest);
}
