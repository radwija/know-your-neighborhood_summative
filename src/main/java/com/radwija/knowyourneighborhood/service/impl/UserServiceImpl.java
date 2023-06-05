package com.radwija.knowyourneighborhood.service.impl;

import com.radwija.knowyourneighborhood.config.SecurityConfig;
import com.radwija.knowyourneighborhood.exception.BadRequestException;
import com.radwija.knowyourneighborhood.exception.UserNotFoundException;
import com.radwija.knowyourneighborhood.model.AuthProvider;
import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.payload.SignUpRequest;
import com.radwija.knowyourneighborhood.repository.UserRepository;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import com.radwija.knowyourneighborhood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        } else if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException("Username address already in use.");
        }
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(SecurityConfig.USER);
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Optional<User> viewUserDetail(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> searchUserByName(String keyword) {
        return userRepository.searchUserByName(keyword);
    }

    private User updateProfile(Long id, User updatedUserRequest) {
        if (userRepository.existsByEmailAndIdNot(updatedUserRequest.getEmail(), id)) {
            return null;
        }
        if (userRepository.existsByUsernameAndIdNot(updatedUserRequest.getUsername(), id)) {
            return null;
        }

        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUserRequest.getName());
                    user.setUsername(updatedUserRequest.getUsername());
                    user.setEmail(updatedUserRequest.getEmail());
                    user.setPhone(updatedUserRequest.getPhone());
                    user.setCity(updatedUserRequest.getCity());
                    user.setCountry(updatedUserRequest.getCountry());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User updateOwnProfile(UserPrincipal userPrincipal, User updatedUserRequest) {
        return updateProfile(userPrincipal.getId(), updatedUserRequest);
    }

    @Override
    public User updateUserProfile(Long id, User updatedUserRequest) {
        System.out.println("email: " + updatedUserRequest.getEmail());
        return updateProfile(id, updatedUserRequest);
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
