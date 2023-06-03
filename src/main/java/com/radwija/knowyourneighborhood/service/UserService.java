package com.radwija.knowyourneighborhood.service;

import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.payload.SignUpRequest;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(SignUpRequest signUpRequest);

    User updateUser(UserPrincipal userPrincipal, User user);

    List<User> showAllUsers();

    void deleteUser(Long id);
}
