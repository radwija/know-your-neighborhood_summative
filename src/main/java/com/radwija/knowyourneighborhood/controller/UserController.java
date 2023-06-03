package com.radwija.knowyourneighborhood.controller;

import com.radwija.knowyourneighborhood.exception.ResourceNotFoundException;
import com.radwija.knowyourneighborhood.model.Car;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.repository.UserRepository;
import com.radwija.knowyourneighborhood.security.CurrentUser;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import com.radwija.knowyourneighborhood.service.CarService;
import com.radwija.knowyourneighborhood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PutMapping("/update")
    public User updateProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody User user) {
        return userService.updateOwnProfile(userPrincipal, user);
    }

    @PutMapping("/update-car/{id}")
    public ResponseEntity<Car> updateOwnCar(@PathVariable Long id, @RequestBody Car updatedCar, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        System.out.println("controller/update-car/{" + id + "}");
        System.out.println("owner: " + userPrincipal.getId());
        return carService.updateOwnCar(id, updatedCar, userPrincipal);
    }
}
