package com.radwija.knowyourneighborhood.service;

import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.payload.SignUpRequest;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User saveUser(SignUpRequest signUpRequest);

    Boolean userExists(Long id);

    Optional<User> viewUserDetail(Long id);

    List<User> searchUserByName(String keyword);

    User updateOwnProfile(UserPrincipal userPrincipal, User user);

    User updateUserProfile(Long id, User user);

    List<User> showAllUsers();

    void deleteUser(Long id);
}
